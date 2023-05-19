package fix.my.playlist.api;

import fix.my.playlist.Config;
import fix.my.playlist.data.SpotifyEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BearerTokenRetriever {

    private static final String CONTENT_TYPE_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String GRANT_TYPE_FIELD = "grant_type";
    private static final String REFRESH_TOKEN_STRING = "refresh_token";
    private static final String ACCESS_TOKEN_STRING = "access_token";

    public static String getFreshToken() {
        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.TOKEN.getValue())
            .contentType(CONTENT_TYPE_URL_ENCODED)
            .formParam(GRANT_TYPE_FIELD, REFRESH_TOKEN_STRING)
            .formParam(REFRESH_TOKEN_STRING, Config.getRefresherToken())
            .auth()
            .preemptive()
            .basic(Config.getClient(), Config.getSecret())
            .post();

        return response.jsonPath().getString(ACCESS_TOKEN_STRING);
    }

    public static String getNewAccessToken() {
        Response response = RestAssured.given()
            .baseUri(SpotifyEndpoints.TOKEN.getValue())
            .contentType(CONTENT_TYPE_URL_ENCODED)
            .formParam(GRANT_TYPE_FIELD, "client_credentials")
            .formParam("code", Config.getCode())
            .auth()
            .preemptive()
            .basic(Config.getClient(), Config.getSecret())
            .post();

        return response.jsonPath().getString(ACCESS_TOKEN_STRING);
    }
}

