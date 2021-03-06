package investment.foundation;

import investment.cache.FileStorage;
import investment.foundation.modal.Foundation;
import investment.foundation.service.CrawlerService;
import investment.foundation.service.FoundationService;
import investment.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

import static investment.cache.FileStorage.updateStorage;
import static investment.cache.cacheStore.setFoundationMapCache;
import static investment.config.FoundationConfig.investFoundations;
import static investment.config.PropertiesConfigLoader.*;
import static investment.utils.MailUtils.email;
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

//    @Scheduled(fixedDelay = 100000)
    public void test() {
        getFoundationUpdate();
        mailService.sendMimeMessage(fromUser(), "xiang1990_ok@126.com", getDateYYYY_MM_DD() + " Foundation Infos", email());
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
        getFoundationUpdate();
        loadProperties();
        sendEmail();
    }

    private void getFoundationUpdate() {
        investFoundations().forEach(investFoundation -> {
            try {
                LOGGER.info("Start to get info of foundation={}", investFoundation.getCode());
                Foundation foundationInfo = crawlerService.getFoundationInfo(investFoundation.getCode(), investFoundation.getName());
                setFoundationMapCache(investFoundation.getCode(), foundationInfo);
                LOGGER.info("Success to get info of foundation={}", investFoundation.getCode());
                Thread.sleep(20000);
            } catch (Exception e) {
                LOGGER.warn("Failed to get info of foundation={}, due to {}", investFoundation.getCode(), e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void sendEmail() {
        String content = renderFoundationEmailContent(foundationService.rankingFoundations());
        List<String> emails = emailList();
        String subject = getDateYYYY_MM_DD() + " Foundation Infos";
        emails.forEach(email -> mailService.sendMimeMessage(fromUser(), email, subject, content));
    }
}
