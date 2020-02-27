package investment.config;

import investment.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static investment.utils.SystemInfoUtils.isWindows;
import static java.util.Arrays.asList;

public class PropertiesConfigLoader {
    private static final String windowsConfigPath = "G:\\config.properties";
    private static final String linuxConfigPath = "/security/investment-manager-service.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfigLoader.class);
    private static Properties PROPERTIES = null;

    public static void loadProperties() {
        String configPath = propertyFilePath();
        LOGGER.info("Start to load config from path={}", configPath);
        try (InputStream inputStream = new FileInputStream(configPath)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            PROPERTIES = properties;
            LOGGER.info("Success to load config from path={}", configPath);
        } catch (Exception e) {
            LOGGER.warn("Failed to load config from path={}", configPath);
            e.printStackTrace();
        }
    }

    public static List<String> emailList() {
        try {
            return Arrays.stream(getProperty("EMAILS").split(",")).map(String::trim).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public static String mysqlPassword() {
        return EncryptUtils.decrypt(getProperty("mysqlPassword"));
    }

    public static String fromUser() {
        return getProperty("FROM_USER");
    }

    public static String toUser() {
        return getProperty("TO_USER");
    }

    public static String ChaoQqEmail() {
        return getProperty("CHAO_QQ_COM");
    }

    public static String LumenQqEmail() {
        return getProperty("LUMEN_QQ_COM");
    }

    public static String emailPassword() {
        return EncryptUtils.decrypt(getProperty("emailPassword"));
    }

    public static String keyStore() {
        return getProperty("keyStore");
    }

    private static String propertyFilePath() {
        return isWindows() ? windowsConfigPath : linuxConfigPath;
    }

    private static String getProperty(String property) {
        if (PROPERTIES == null) {
            loadProperties();
        }
        return PROPERTIES != null ? PROPERTIES.getProperty(property) : "";
    }
}
