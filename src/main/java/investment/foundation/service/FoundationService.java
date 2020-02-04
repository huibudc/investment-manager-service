package investment.foundation.service;

import investment.foundation.dao.FoundationMapper;
import investment.foundation.modal.Foundation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static investment.utils.Utils.GSON;

@Slf4j
@Service
public class FoundationService {
    private final FoundationMapper foundationMapper;

    @Autowired
    public FoundationService(FoundationMapper foundationMapper) {
        this.foundationMapper = foundationMapper;
    }

    public void addFoundation(Foundation foundation) {
        foundationMapper.saveOrUpdateFoundation(foundation);
    }

    public List<Foundation> rankingFoundations() {
        log.info("Start to get all foundations from DB");
        try {
            List<Foundation> foundations = foundationMapper.allFoundations();
            foundations.forEach(foundation -> {
                foundation.setRankTop20WithinWeek(isTop20(foundation.getRankWithinWeek()));
                foundation.setRankWithinWeek(foundation.getRankWithinWeek() + ", top " + rankingInPercentage(foundation.getRankWithinWeek()));
                foundation.setRankTop20WithinMonth(isTop20(foundation.getRankWithinMonth()));
                foundation.setRankWithinMonth(foundation.getRankWithinMonth() + ", top " + rankingInPercentage(foundation.getRankWithinMonth()));
                foundation.setRankTop20ThreeMonth(isTop20(foundation.getRankWithinThreeMonth()));
                foundation.setRankWithinThreeMonth(foundation.getRankWithinThreeMonth() + ", top " + rankingInPercentage(foundation.getRankWithinThreeMonth()));
                foundation.setRankTop20SixMonth(isTop20(foundation.getRankWithinSixMonth()));
                foundation.setRankWithinSixMonth(foundation.getRankWithinSixMonth() + ", top " + rankingInPercentage(foundation.getRankWithinSixMonth()));
                foundation.setShouldWarn(!foundation.getRankTop20WithinWeek() && !foundation.getRankTop20SixMonth() && !foundation.getRankTop20ThreeMonth() && !foundation.getRankTop20SixMonth());
            });
            log.info("Success to get all foundations from DB, they are {}", GSON.toJson(foundations));
            return foundations;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Failed to get all foundations from DB");
            return Collections.emptyList();
        }
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

    public static void main(String[] args) {
        Foundation foundation = GSON.fromJson("{\"code\":\"519674\",\"name\":\"银河创新成长混合\",\"date\":\"2020-02-04\",\"estimatedValue\":\"4.8385\",\"estimatedGain\":\"+3.94%\",\"actualValue\":\"4.6553\",\"actualGain\":\"-7.15%\",\"accumulativeValue\":\"4.6553\",\"gainWithinWeek\":\"-7.15%\",\"gainWithinMonth\":\"9.83%\",\"gainWithinThreeMonth\":\"33.36%\",\"gainWithinSixMonth\":\"62.02%\",\"rankWithinWeek\":\"2242 | 3134\",\"rankWithinMonth\":\"6 | 3148\",\"rankWithinThreeMonth\":\"3 | 3100\",\"rankWithinSixMonth\":\"3 | 3013\"}", Foundation.class);
        foundation.setRankTop20WithinWeek(isTop20(foundation.getRankWithinWeek()));
        foundation.setRankWithinWeek(foundation.getRankWithinWeek() + ", top " + rankingInPercentage(foundation.getRankWithinWeek()));
        foundation.setRankTop20WithinMonth(isTop20(foundation.getRankWithinMonth()));
        foundation.setRankWithinMonth(foundation.getRankWithinMonth() + ", top " + rankingInPercentage(foundation.getRankWithinMonth()));
        foundation.setRankTop20ThreeMonth(isTop20(foundation.getRankWithinThreeMonth()));
        foundation.setRankWithinThreeMonth(foundation.getRankWithinThreeMonth() + ", top " + rankingInPercentage(foundation.getRankWithinThreeMonth()));
        foundation.setRankTop20SixMonth(isTop20(foundation.getRankWithinSixMonth()));
        foundation.setRankWithinSixMonth(foundation.getRankWithinSixMonth() + ", top " + rankingInPercentage(foundation.getRankWithinSixMonth()));
        foundation.setShouldWarn(!foundation.getRankTop20WithinWeek() && !foundation.getRankTop20SixMonth() && !foundation.getRankTop20ThreeMonth() && !foundation.getRankTop20SixMonth());
        System.out.println(GSON.toJson(foundation));
    }
}
