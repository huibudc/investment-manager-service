package investment.cache;

import investment.foundation.modal.Foundation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class cacheStore {
    public static final Map<String, Foundation> foundationMap = new ConcurrentHashMap<>();
}
