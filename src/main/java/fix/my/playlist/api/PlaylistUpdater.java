package fix.my.playlist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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


public class PlaylistUpdater {
    private static final String HEADER_AUTH_FIELD = "Authorization";
    private static String BEARER_STRING;
    private static final Logger log = LogManager.getLogger(PlaylistUpdater.class);

    public static void fixMyPlaylists(List<Playlist> playlists) {
        BEARER_STRING = "Bearer " + BearerTokenRetriever.getFreshToken();

        for (var playlist : playlists) {
            if (playlist.getTitle() != null) updateTitle(playlist);
            if (playlist.getDescription() != null) updateDescription(playlist);
            if (playlist.getImage() != null) updateImage(playlist);
        }
    }

    private static void updateTitle(Playlist playlist) {
        var jsonObject = new ObjectMapper().createObjectNode();
        jsonObject.put("description", playlist.getDescription());

        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(HEADER_AUTH_FIELD, BEARER_STRING)
            .contentType(JSON)
            .body(jsonObject)
            .put(playlist.getSpotifyPlaylistId());

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            log.info(String.format("%s: Name updated!", playlist.getTitle()));
        } else {
            log.error(String.format("%s: Failed to update playlist name. Status code: %s", playlist.getTitle(), statusCode));
            response.getBody().prettyPrint();
        }
    }

    private static void updateDescription(Playlist playlist) {
        var jsonObject = new ObjectMapper().createObjectNode();
        jsonObject.put("description", playlist.getDescription());

        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(HEADER_AUTH_FIELD, BEARER_STRING)
            .contentType(JSON)
            .body(jsonObject)
            .put(playlist.getSpotifyPlaylistId());

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            log.info(String.format("%s: Description updated!", playlist.getTitle()));
        } else {
            log.error(String.format("%s: Failed to update playlist description. Status code: %s", playlist.getTitle(), statusCode));
            response.getBody().prettyPrint();
        }
    }

    private static void updateImage(Playlist playlist) {
        Response response = RestAssured.given()
            .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("image/jpeg", ContentType.TEXT)))
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(HEADER_AUTH_FIELD, BEARER_STRING)
            .contentType("image/jpeg")
            .body(Base64Converter.getBase64Image(playlist.getImage()))
            .put(playlist.getSpotifyPlaylistId() + "/images");

        int statusCode = response.getStatusCode();
        if (statusCode == 202) {
            log.info(String.format("%s: Image updated!", playlist.getTitle()));
        } else {
            log.error(String.format("%s: Failed to update playlist image. Status code: %s", playlist.getTitle(), statusCode));
            response.getBody().prettyPrint();
        }
    }
}