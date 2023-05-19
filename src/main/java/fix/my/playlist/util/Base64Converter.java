package fix.my.playlist.util;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public class Base64Converter {

    public static String getBase64Image(String imagePath) {
        try {
            File file = new File(imagePath);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
