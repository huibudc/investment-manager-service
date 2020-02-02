package investment.config;

import investment.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import static investment.utils.SystemInfoUtils.isWindows;

public class ConfigLoader {
    private static final String windowsConfigPath = "H:\\config.properties";
    private static final String linuxConfigPath = "/security/investment-manager-service.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);

    private static Properties PROPERTIES = null;

    public static void loadProperties() {
        String configPath = propertyFilePath();
        LOGGER.info("Start to load config from path={}", configPath);
        try(InputStream inputStream = new FileInputStream(configPath)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            PROPERTIES = properties;
            LOGGER.info("Success to load config from path={}", configPath);
        } catch (Exception e) {
            LOGGER.warn("Failed to load config from path={}", configPath);
            e.printStackTrace();
        }
    }

    private static String propertyFilePath() {
        return isWindows() ? windowsConfigPath : linuxConfigPath;
    }

    public static String mysqlPassword() {
        return EncryptUtils.decrypt(getProperty("mysqlPassword"));
    }

    public static String emailPassword() {
        return EncryptUtils.decrypt(getProperty("emailPassword"));
    }

    public static String keyStore() {
        return getProperty("keyStore");
    }

    private static String getProperty(String property) {
        if (PROPERTIES == null) {
            loadProperties();
        }
        return PROPERTIES != null ? PROPERTIES.getProperty(property) : "";
    }

}
