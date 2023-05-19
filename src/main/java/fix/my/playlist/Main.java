package fix.my.playlist;

import fix.my.playlist.api.PlaylistUpdater;

import static fix.my.playlist.data.MyPlaylists.JUNGLE_DNB;

public class Main {

    public static void main(String[] args) {
        PlaylistUpdater.fixMyPlaylist(JUNGLE_DNB);
    }
}
