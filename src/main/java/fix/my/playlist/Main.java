package fix.my.playlist;

import fix.my.playlist.api.PlaylistUpdater;
import fix.my.playlist.data.MyPlaylists;
import fix.my.playlist.model.Playlist;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var listOfPlaylists = getPlaylists();

        PlaylistUpdater.fixMyPlaylists(listOfPlaylists);
    }

    private static List<Playlist> getPlaylists() {
        List<Playlist> playlistList = new ArrayList<>();
        Class<?> containingClass = MyPlaylists.class;

        Field[] fields = containingClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == Playlist.class) {
                try {
                    field.setAccessible(true);
                    Playlist playlist = (Playlist) field.get(null);
                    playlistList.add(playlist);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return playlistList;
    }
}
