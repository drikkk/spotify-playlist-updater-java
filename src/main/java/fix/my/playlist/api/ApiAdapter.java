package fix.my.playlist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fix.my.playlist.Config;
import fix.my.playlist.data.SpotifyEndpoints;
import fix.my.playlist.model.Playlist;
import fix.my.playlist.util.Base64Converter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.http.ContentType.JSON;

public class ApiAdapter {

    private static final String GRANT_TYPE_STRING = "grant_type";
    private static final String AUTHORIZATION_STRING = "Authorization";
    private static final String URL_ENCODED_STRING = "application/x-www-form-urlencoded";
    private static final String REFRESH_TOKEN_STRING = "refresh_token";
    private static final String ACCESS_TOKEN_STRING = "access_token";
    private static final String BEARER_STRING;
    private static final Logger log = LogManager.getLogger(ApiAdapter.class);

    static {
        BEARER_STRING = "Bearer " + getRefreshedBearerToken();
    }

    public static void updatePlaylists(List<Playlist> playlists) {
        for (var playlist : playlists) {
            if (isPlaylistHealthy(playlist)) {
                if (playlist.getName() != null) updateName(playlist);
                if (playlist.getImage() != null) updateImage(playlist);
                if (playlist.getDescription() != null) updateDescription(playlist);
            } else {
                log.info("{}: Nothing to update, playlist seems healthy!", playlist.getName());
            }
        }
    }

    public static Boolean isPlaylistHealthy(Playlist playlist) {
        var response = RestAssured.given()
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(AUTHORIZATION_STRING, BEARER_STRING)
            .get(playlist.getSpotifyPlaylistId());

        return !response.jsonPath().getString("name").equals(playlist.getName());
    }

    private static void updateName(Playlist playlist) {
        var jsonObject = new ObjectMapper().createObjectNode();
        jsonObject.put("name", playlist.getName());

        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(AUTHORIZATION_STRING, BEARER_STRING)
            .contentType(JSON)
            .body(jsonObject)
            .put(playlist.getSpotifyPlaylistId());

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            log.info("{}: Name updated!", playlist.getName());
        } else {
            log.error("{}: Failed to update playlist name. Status code: {}", playlist.getName(), statusCode);
            response.getBody().prettyPrint();
        }
    }

    private static void updateDescription(Playlist playlist) {
        var jsonObject = new ObjectMapper().createObjectNode();
        jsonObject.put("description", playlist.getDescription());

        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(AUTHORIZATION_STRING, BEARER_STRING)
            .contentType(JSON)
            .body(jsonObject)
            .put(playlist.getSpotifyPlaylistId());

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            log.info("{}: Description updated!", playlist.getName());
        } else {
            log.error("{}: Failed to update playlist description. Status code: {}", playlist.getName(), statusCode);
            response.getBody().prettyPrint();
        }
    }

    private static void updateImage(Playlist playlist) {
        Response response = RestAssured.given()
            .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("image/jpeg", ContentType.TEXT)))
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(AUTHORIZATION_STRING, BEARER_STRING)
            .contentType("image/jpeg")
            .body(Base64Converter.getBase64Image(playlist.getImage()))
            .put(playlist.getSpotifyPlaylistId() + "/images");

        int statusCode = response.getStatusCode();
        if (statusCode == 202) {
            log.info("{}: Image updated!", playlist.getName());
        } else {
            log.error("{}: Failed to update playlist image. Status code: {}", playlist.getName(), statusCode);
            response.getBody().prettyPrint();
        }
    }

    private static String getRefreshedBearerToken() {
        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.TOKEN.getValue())
            .contentType(URL_ENCODED_STRING)
            .formParam(GRANT_TYPE_STRING, REFRESH_TOKEN_STRING)
            .formParam(REFRESH_TOKEN_STRING, Config.getRefresherToken())
            .auth()
            .preemptive()
            .basic(Config.getClient(), Config.getSecret())
            .post();

        return response.jsonPath().getString(ACCESS_TOKEN_STRING);
    }

    private static String getNewBearerToken() {
        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.TOKEN.getValue())
            .contentType(URL_ENCODED_STRING)
            .formParam(GRANT_TYPE_STRING, "client_credentials")
            .formParam("code", Config.getCode())
            .auth()
            .preemptive()
            .basic(Config.getClient(), Config.getSecret())
            .post();

        return response.jsonPath().getString(ACCESS_TOKEN_STRING);
    }
}
