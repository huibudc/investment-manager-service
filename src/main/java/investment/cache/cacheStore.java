package investment.cache;

import investment.foundation.modal.Foundation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static investment.utils.Utils.getDateYYYY_MM_DD;

public class cacheStore {
    private static final Map<String, Foundation> foundationMapCache = new ConcurrentHashMap<>();
    private static final Map<String, List<Foundation>> foundationMapStorage = new ConcurrentHashMap<>();

    public static Map<String, Foundation> getFoundationMapCache() {
        return foundationMapCache;
    }

    public static void  updateFoundationMapStorage(String date, List<Foundation> foundations) {
        foundationMapStorage.put(date, foundations);
    }

    public static Map<String, List<Foundation>> getFoundationMapStorage() {
        return foundationMapStorage;
    }

    public static void setFoundationMapCache(String code, Foundation foundation) {
        addToStorage(foundation);
        foundationMapCache.put(code, foundation);
    }

    private static Boolean isFoundationDataUpdateToDate(Foundation foundation) {
        return foundation != null && foundation.getActualValue().contains(foundation.getDate());
    }

    private static void addToStorage(Foundation foundation) {
        if (!isFoundationDataUpdateToDate(foundation)) return;
        String date = getDateYYYY_MM_DD();
        foundationMapStorage.computeIfAbsent(date, k -> new ArrayList<>());
        foundationMapStorage.get(date).add(foundation);
    }
}
