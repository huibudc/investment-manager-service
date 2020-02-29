package investment.foundation.service;

import investment.foundation.modal.Foundation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static investment.cache.cacheStore.getFoundationMapCache;
import static investment.utils.Utils.GSON;

@Service
public class FoundationService {
    private final static Logger log = LoggerFactory.getLogger(CrawlerService.class);

    public List<Foundation> rankingFoundations() {
        List<Foundation> foundations = new ArrayList<>(getFoundationMapCache().values());
        for (Foundation foundation : foundations) {
            try {
                foundation.setRankTop20WithinWeek(isTop20(foundation.getRankWithinWeek()));
                foundation.setRankWithinWeek(foundation.getRankWithinWeek() + ", top " + rankingInPercentage(foundation.getRankWithinWeek()));
                foundation.setRankTop20WithinMonth(isTop20(foundation.getRankWithinMonth()));
                foundation.setRankWithinMonth(foundation.getRankWithinMonth() + ", top " + rankingInPercentage(foundation.getRankWithinMonth()));
                foundation.setRankTop20ThreeMonth(isTop20(foundation.getRankWithinThreeMonth()));
                foundation.setRankWithinThreeMonth(foundation.getRankWithinThreeMonth() + ", top " + rankingInPercentage(foundation.getRankWithinThreeMonth()));
                foundation.setRankTop20SixMonth(isTop20(foundation.getRankWithinSixMonth()));
                foundation.setRankWithinSixMonth(foundation.getRankWithinSixMonth() + ", top " + rankingInPercentage(foundation.getRankWithinSixMonth()));
                foundation.setShouldWarn(!foundation.getRankTop20WithinWeek() && !foundation.getRankTop20SixMonth() && !foundation.getRankTop20ThreeMonth() && !foundation.getRankTop20SixMonth());
            } catch (Exception e) {
                e.printStackTrace();
                log.info("Failed to handle all foundations from cache {}", GSON.toJson(foundation));
            }
        }
        return foundations;
    }

    private static Boolean isTop20(String ranking) {
        return getRankInDouble(ranking) < 0.2;
    }

    private static String rankingInPercentage(String ranking) {
        return String.format("%.2f", getRankInDouble(ranking) * 100) + "%";
    }

    private static double getRankInDouble(String ranking) {
        String[] rankingArr = ranking.split("\\|");
        return (Double.parseDouble(rankingArr[0]) / Double.parseDouble(rankingArr[1]));
    }
}
