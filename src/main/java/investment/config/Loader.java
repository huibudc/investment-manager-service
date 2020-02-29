package investment.config;

import static investment.cache.FileStorage.loadStorageFile;
import static investment.config.PropertiesConfigLoader.loadProperties;

public class Loader {
    public static void load() {
        loadProperties();
        loadStorageFile();
    }
}
