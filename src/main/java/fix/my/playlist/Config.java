package fix.my.playlist;

import fix.my.playlist.api.ApiAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Properties;

public class Config {

    private static final Properties properties;
    private static final Logger log = LogManager.getLogger(ApiAdapter.class);
    public static String refresherToken;
    public static String client;
    public static String secret;
    public static String code;

    static {
        properties = new Properties();

        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception ex) {
            log.fatal("Could not find config file!");
            throw new RuntimeException();
        }

        refresherToken = getRefresherTokenValueFromPropertiesFile();
        client = getClientValueFromPropertiesFile();
        secret = getSecretValueFromPropertiesFile();
        code = getCodeValueFromPropertiesFile();

        if (List.of(refresherToken, client, secret).contains("")) {
            log.fatal("Check config file values!");
            throw new RuntimeException();
        }
    }

    private static String getRefresherTokenValueFromPropertiesFile() {
        return properties.getProperty("refresher_token");
    }

    private static String getClientValueFromPropertiesFile() {
        return properties.getProperty("client");
    }

    private static String getSecretValueFromPropertiesFile() {
        return properties.getProperty("secret");
    }

    private static String getCodeValueFromPropertiesFile() {
        return properties.getProperty("code");
    }
}
