package investment.foundation;

import investment.config.FoundationConfigLoader;
import investment.config.PropertiesConfigLoader;
import investment.foundation.modal.Foundation;
import investment.foundation.modal.InvestFoundation;
import investment.foundation.service.CrawlerService;
import investment.foundation.service.FoundationService;
import investment.service.MailService;
import investment.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static investment.cache.cacheStore.foundationMap;
import static investment.config.PropertiesConfigLoader.*;
import static investment.utils.MailUtils.renderFoundationEmailContent;
import static investment.utils.Utils.getDateYYYY_MM_DD;

@Component
public class Scheduler {
    private final static Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
    private final CrawlerService crawlerService;
    private final FoundationService foundationService;
    private final MailService mailService;

    @Autowired
    public Scheduler(CrawlerService crawlerService, FoundationService foundationService, MailService mailService) {
        this.crawlerService = crawlerService;
        this.foundationService = foundationService;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 40 14 ? * MON-FRI")
    public void scheduleJobInTradingTime() {
        getFoundationDataAndSendMail();
    }

    @Scheduled(cron = "0 0 23 ? * MON-FRI")
    public void scheduleJobOutOfTradingTime() {
        getFoundationDataAndSendMail();
    }

    private void getFoundationDataAndSendMail() {
        List<InvestFoundation> investFoundations = FoundationConfigLoader.investFoundations();
        investFoundations.forEach(investFoundation -> {
            try {
                LOGGER.info("Start to get info of foundation={}", investFoundation.getCode());
                Foundation foundationInfo = crawlerService.getFoundationInfo(investFoundation.getCode(), investFoundation.getName());
                foundationMap.put(investFoundation.getCode(), foundationInfo);
                LOGGER.info("Success to get info of foundation={}", investFoundation.getCode());
                Thread.sleep(20000);
            } catch (Exception e) {
                LOGGER.warn("Failed to get info of foundation={}, due to {}", investFoundation.getCode(), e.getMessage());
                e.printStackTrace();
            }
        });

        loadProperties();
        emailList().forEach(email -> {
            mailService.sendMimeMessage(fromUser(), email, getDateYYYY_MM_DD() + " Foundation Infos", renderFoundationEmailContent(foundationService.rankingFoundations()));
        });
    }
}
