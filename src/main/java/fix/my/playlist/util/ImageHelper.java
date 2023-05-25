package fix.my.playlist.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageHelper {

    public static String getBase64Image(String imageName) {
        var filePath = Paths.get(System.getProperty("user.dir"), "playlists", imageName);
        var imagePath = filePath.toAbsolutePath().toString();

        try {
            var imageByteArray = Files.readAllBytes(new File(imagePath).toPath());
            return Base64.getEncoder().encodeToString(imageByteArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
