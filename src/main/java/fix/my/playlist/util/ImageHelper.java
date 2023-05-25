package fix.my.playlist.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageHelper {

    public static String getBase64Image(String imageName) {
        var imageAbsolutePath = Paths.get(System.getProperty("user.dir"), "playlists", imageName).toAbsolutePath();

        try {
            var imageByteArray = Files.readAllBytes(imageAbsolutePath);
            return Base64.getEncoder().encodeToString(imageByteArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
