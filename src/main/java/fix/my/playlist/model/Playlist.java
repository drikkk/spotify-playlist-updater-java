package fix.my.playlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    private String name;
    private String description;
    private String image;
    private String spotifyPlaylistId;
}
