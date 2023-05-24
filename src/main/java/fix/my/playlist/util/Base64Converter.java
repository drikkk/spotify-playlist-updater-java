package fix.my.playlist.util;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Objects;

public class Base64Converter {

    public static String getBase64Image(String imageName) {
        try {
            var imageByteArray = Files.readAllBytes(new File(Objects.requireNonNull(Base64Converter.class.getClassLoader().getResource(imageName)).getPath()).toPath());
            return Base64.getEncoder().encodeToString(imageByteArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
