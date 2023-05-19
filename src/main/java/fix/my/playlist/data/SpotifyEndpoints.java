package fix.my.playlist.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SpotifyEndpoints {

    TOKEN("https://accounts.spotify.com/api/token"),
    PLAYLISTS("https://api.spotify.com/v1/playlists/");

    @Getter
    private final String value;
}
