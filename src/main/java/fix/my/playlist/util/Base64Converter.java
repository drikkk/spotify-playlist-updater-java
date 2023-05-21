package fix.my.playlist.util;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public class Base64Converter {

    public static String getBase64Image(File image) {
        try {
            var imageByteArray = Files.readAllBytes(image.toPath());
            return Base64.getEncoder().encodeToString(imageByteArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
