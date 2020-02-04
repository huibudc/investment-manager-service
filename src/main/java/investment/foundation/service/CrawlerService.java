package investment.foundation.service;

import investment.config.DBConfigLoader;
import investment.foundation.modal.Foundation;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static investment.config.Config.foundationUrl;
import static investment.utils.Utils.convertToYYYY_MM_DD;

@Slf4j
@Service
public class CrawlerService {
    private final DBConfigLoader dbConfigLoader;

    @Autowired
    public CrawlerService(DBConfigLoader dbConfigLoader) {
        this.dbConfigLoader = dbConfigLoader;
    }

    public Foundation getFoundationInfo(String code) throws Exception {
        String url = foundationUrl(code);
        log.info("Start to craw data from foundation code={} ,from url={}", code, url);
        try {
            Document doc = Jsoup.connect(url).get();
            String estimatedValueTime = doc.select("#gz_gztime").text().substring(1, 9);
            String estimatedValue = doc.select("#gz_gsz").text();  // 估值
            String estimatedGain = doc.select("#gz_gszzl").text();  //  估值涨幅
            Elements todayActualUnit = doc.select(".fundInfoItem .dataItem02 .dataNums span");
            String actualValue = todayActualUnit.first().text();
            String actualGain = todayActualUnit.last().text();
            String accumulativeValue = doc.select(".fundInfoItem .dataItem03 .dataNums span").first().text();
            Elements increaseAmountStages = doc.select("#increaseAmount_stage table tbody tr");
            Element stageIncreaseInfo = increaseAmountStages.get(1);
            Element rankingWithinSameFoundation = increaseAmountStages.get(4);

            Foundation foundation = new Foundation();
            foundation.setCode(code);
            foundation.setName(dbConfigLoader.investFoundations().get(code));
            foundation.setDate(convertToYYYY_MM_DD(estimatedValueTime));
            foundation.setEstimatedValue(estimatedValue);
            foundation.setEstimatedGain(estimatedGain);
            foundation.setActualValue(actualValue);
            foundation.setActualGain(actualGain);
            foundation.setAccumulativeValue(accumulativeValue);
            foundation.setGainWithinWeek(stageIncreaseInfo.select("td").get(1).text());
            foundation.setGainWithinMonth(stageIncreaseInfo.select("td").get(2).text());
            foundation.setGainWithinThreeMonth(stageIncreaseInfo.select("td").get(3).text());
            foundation.setGainWithinSixMonth(stageIncreaseInfo.select("td").get(4).text());
            foundation.setRankWithinWeek(rankingWithinSameFoundation.select("td").get(1).text());
            foundation.setRankWithinMonth(rankingWithinSameFoundation.select("td").get(2).text());
            foundation.setRankWithinThreeMonth(rankingWithinSameFoundation.select("td").get(3).text());
            foundation.setRankWithinSixMonth(rankingWithinSameFoundation.select("td").get(4).text());
            return foundation;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Failed to craw data from foundation code={} ,from url={}", code, url);
            throw e;
        }
    }
}