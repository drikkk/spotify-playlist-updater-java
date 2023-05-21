package fix.my.playlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
public class Playlist {

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final File image;
    @Getter
    private final String spotifyPlaylistId;
}
