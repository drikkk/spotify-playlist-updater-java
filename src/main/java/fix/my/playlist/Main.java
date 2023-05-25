package fix.my.playlist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fix.my.playlist.api.ApiAdapter;
import fix.my.playlist.model.Playlist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        var playlistJsonPathString = Paths.get(System.getProperty("user.dir"), "playlists", "playlists.json").toAbsolutePath().toString();
        var objectMapper = new ObjectMapper();

        TypeReference<List<Playlist>> typeReference = new TypeReference<>() {};
        List<Playlist> playlists;
        try {
            playlists = objectMapper.readValue(new File(playlistJsonPathString), typeReference);
        } catch (IOException e) {
            log.fatal("Error loading playlists.json");
            throw new RuntimeException(e);
        }

        ApiAdapter.updatePlaylists(playlists);
    }
}
