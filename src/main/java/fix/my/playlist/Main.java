package fix.my.playlist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fix.my.playlist.api.ApiAdapter;
import fix.my.playlist.model.Playlist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        var objectMapper = new ObjectMapper();
        var inputStream = Main.class.getClassLoader().getResourceAsStream("playlists.json");

        TypeReference<List<Playlist>> typeReference = new TypeReference<>() {};
        List<Playlist> playlists;
        try {
            playlists = objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            log.fatal("Error loading playlists.json");
            throw new RuntimeException(e);
        }

        ApiAdapter.updatePlaylists(playlists);
    }
}
