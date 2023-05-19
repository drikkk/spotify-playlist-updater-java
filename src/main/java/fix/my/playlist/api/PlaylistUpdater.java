package fix.my.playlist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fix.my.playlist.data.SpotifyEndpoints;
import fix.my.playlist.model.Playlist;
import fix.my.playlist.util.Base64Converter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.http.ContentType.JSON;

public class PlaylistUpdater {

    private static final String HEADER_AUTH_FIELD = "Authorization";
    private static String HEADER_AUTH_VALUE;

    public static void fixMyPlaylist(Playlist playlist) {
        HEADER_AUTH_VALUE = "Bearer " + BearerTokenRetriever.getFreshToken();

        updateTitleAndDescription(playlist);
        updateImage(playlist);
    }

    private static void updateTitleAndDescription(Playlist playlist) {
        var jsonObject = new ObjectMapper().createObjectNode();
        jsonObject.put("name", playlist.getTitle());
        jsonObject.put("description", playlist.getDescription());

        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(HEADER_AUTH_FIELD, HEADER_AUTH_VALUE)
            .contentType(JSON)
            .body(jsonObject)
            .put(playlist.getSpotifyPlaylistId());

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Playlist Title & Description updated successfully");
        } else {
            System.out.println("Failed to update playlist description. Status code: " + statusCode);
            response.getBody().prettyPrint();
        }
    }

    private static void updateImage(Playlist playlist) {
        Response response = RestAssured.given()
            .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("image/jpeg", ContentType.TEXT)))
            .baseUri(SpotifyEndpoints.PLAYLISTS.getValue())
            .header(HEADER_AUTH_FIELD, HEADER_AUTH_VALUE)
            .contentType("image/jpeg")
            .body(Base64Converter.getBase64Image(playlist.getImage()))
            .put(playlist.getSpotifyPlaylistId() + "/images");

        int statusCode = response.getStatusCode();
        if (statusCode == 202) {
            System.out.println("Playlist Image updated successfully");
        } else {
            System.out.println("Failed to update playlist image. Status code: " + statusCode);
            response.getBody().prettyPrint();
        }
    }
}