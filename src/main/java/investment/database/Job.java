package investment.database;

import com.google.gson.Gson;
import investment.database.model.InvestFoundation;
import investment.database.service.CrawlerService;
import investment.foundation.modal.Foundation;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Job {
    private final CrawlerService crawlerService;
    private final Dao dao;

    public Job(CrawlerService crawlerService, Dao dao) {
        this.crawlerService = crawlerService;
        this.dao = dao;
    }

    public int foundationData() throws Exception {
        List<InvestFoundation> investFoundations = dao.investFoundations();
        if (investFoundations == null || investFoundations.isEmpty()) return 0;
        log.info("start to getting data for investFoundations={}", new Gson().toJson(investFoundations));
        List<Foundation> foundations = new ArrayList<>();
        for (InvestFoundation investFoundation : investFoundations) {
            Foundation foundationInfo = crawlerService.getFoundationInfo(investFoundation.code(), investFoundation.name());
            foundations.add(foundationInfo);
        }
        return dao.addFoundationsInfo(foundations);
    }

    public static void main(String[] args) throws Exception {
        new Job(new CrawlerService(), new Dao()).foundationData();
    }
}
