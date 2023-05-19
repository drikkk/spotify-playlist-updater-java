package fix.my.playlist.data;

import fix.my.playlist.api.PlaylistUpdater;
import fix.my.playlist.model.Playlist;

import java.util.Objects;

public class MyPlaylists {

    public static final Playlist JUNGLE_DNB = new Playlist(
        "Jungle Drum & Bass",
        "JUNGLE IS MASSIVE!!!! Not on Spotify: Mask & Gang Related - Dictation ( Michael Caine remix ) jungle dnb",
        Objects.requireNonNull(PlaylistUpdater.class.getClassLoader().getResource("jungle_dnb_image.jpg")).getPath(),
        "4EAgTGzJpLvtDxQhqNgd0z");
}
