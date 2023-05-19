package fix.my.playlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Playlist {

    @Getter
    private final String title;
    @Getter
    private final String description;
    @Getter
    private final String image;
    @Getter
    private final String spotifyPlaylistId;
}
