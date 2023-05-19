package fix.my.playlist;

import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final Properties properties;

    static {
        properties = new Properties();

        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getRefresherToken() {
        return properties.getProperty("refresher");
    }

    public static String getClient() {
        return properties.getProperty("client");
    }

    public static String getSecret() {
        return properties.getProperty("secret");
    }

    public static String getCode() {
        return properties.getProperty("code");
    }
}
