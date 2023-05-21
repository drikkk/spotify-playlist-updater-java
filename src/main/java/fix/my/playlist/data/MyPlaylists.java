package fix.my.playlist.data;

import fix.my.playlist.api.ApiAdapter;
import fix.my.playlist.model.Playlist;

import java.io.File;
import java.util.Objects;

public class MyPlaylists {

    /**
     * Set field value null to ignore updating it. (e.g. If your playlist does not have a description)
     */
    public static final Playlist JUNGLE_DNB = new Playlist(
        "Jungle Drum & Bass",
        "JUNGLE IS MASSIVE!!!! Not on Spotify: Mask & Gang Related - Dictation ( Michael Caine remix ) jungle dnb",
        new File(Objects.requireNonNull(ApiAdapter.class.getClassLoader().getResource("jungle_dnb_image.jpg")).getPath()),
        "4EAgTGzJpLvtDxQhqNgd0z");
}
