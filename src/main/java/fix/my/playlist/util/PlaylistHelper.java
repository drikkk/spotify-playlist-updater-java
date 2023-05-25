package fix.my.playlist.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fix.my.playlist.Main;
import fix.my.playlist.model.Playlist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class PlaylistHelper {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static List<Playlist> getPlaylistsFromJson() {
        var objectMapper = new ObjectMapper();
        var collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Playlist.class);
        var playlistJsonPathString = Paths.get(System.getProperty("user.dir"), "playlists", "playlists.json").toAbsolutePath().toString();
        var jsonFile = new File(playlistJsonPathString);

        try {
            return objectMapper.readValue(jsonFile, collectionType);
        } catch (IOException e) {
            log.fatal("Error loading playlists.json");
            throw new RuntimeException(e);
        }
    }
}
