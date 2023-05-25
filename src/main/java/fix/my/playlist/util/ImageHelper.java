package fix.my.playlist.util;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public class ImageHelper {

    public static String getBase64Image(String imageName) {
        var imagePath = System.getProperty("user.dir").concat("/playlists/").concat(imageName);

        try {
            var imageByteArray = Files.readAllBytes(new File(imagePath).toPath());
            return Base64.getEncoder().encodeToString(imageByteArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
