package fix.my.playlist.data;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SpotifyEndpoints {

    TOKEN("https://accounts.spotify.com/api/token"),
    PLAYLISTS("https://api.spotify.com/v1/playlists/");

    private final String string;

    @Override
    public String toString() {
        return string;
    }
}
