package investment.utils;

public class SystemInfoUtils {
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
