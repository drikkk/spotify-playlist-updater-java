package fix.my.playlist;

import fix.my.playlist.api.ApiAdapter;
import fix.my.playlist.util.PlaylistHelper;

public class Main {
    public static void main(String[] args) {
        var myPlaylists = PlaylistHelper.getPlaylistsFromJson();
        ApiAdapter.checkPlaylists(myPlaylists);
    }
}
