package investment.cache;

import investment.foundation.modal.Foundation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static investment.cache.FileStorage.updateStorage;
import static investment.utils.Utils.getDateYYYY_MM_DD;

public class cacheStore {
    private static final Map<String, Foundation> foundationMapCache = new ConcurrentHashMap<>();

    public static Map<String, Foundation> getFoundationMapCache() {
        return foundationMapCache;
    }

    public static void setFoundationMapCache(String code, Foundation foundation) {
        foundationMapCache.put(code, foundation);
        updateStorage(foundation);
    }
}
