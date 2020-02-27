package investment.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import investment.foundation.modal.InvestFoundation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import static investment.utils.SystemInfoUtils.isWindows;

public class FoundationConfigLoader {
    private static final String windowsConfigPath = "G:\\foundation.json";
    private static final String linuxConfigPath = "/security/foundation.json";
    private static final Logger LOGGER = LoggerFactory.getLogger(FoundationConfigLoader.class);
    private static List<InvestFoundation> investFoundations;
    private static Gson gson = new GsonBuilder().create();

    public static void load() {
        String configPath = propertyFilePath();
        LOGGER.info("Start to load config from path={}", configPath);
        try (BufferedReader reader = new BufferedReader(new FileReader(configPath))) {
            JsonObject jsonObject = (JsonObject) JsonParser.parseReader(reader);
            investFoundations = jsonObject.entrySet().stream().map(json -> new InvestFoundation(json.getKey(), json.getValue().getAsString())).collect(Collectors.toList());
            LOGGER.info("Success to load config from path={} of data: {}", configPath, gson.toJson(investFoundations));
        } catch (Exception e) {
            LOGGER.warn("Failed to load config from path={}", gson.toJson(configPath));
            e.printStackTrace();
        }
    }

    public static List<InvestFoundation> investFoundations() {
        load();
        return investFoundations;
    }


    private static String propertyFilePath() {
        return isWindows() ? windowsConfigPath : linuxConfigPath;
    }
}
