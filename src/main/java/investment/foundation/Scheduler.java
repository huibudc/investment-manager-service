package investment.foundation;

import investment.config.PropertiesConfigLoader;
import investment.foundation.modal.Foundation;
import investment.foundation.service.CrawlerService;
import investment.foundation.service.FoundationService;
import investment.service.MailService;
import investment.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static investment.cache.cacheStore.foundationMap;
import static investment.config.Config.FOUNDATIONS;
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
        FOUNDATIONS.keySet().forEach(code -> {
            try {
                LOGGER.info("Start to get info of foundation={}", code);
                Foundation foundationInfo = crawlerService.getFoundationInfo(code);
                foundationMap.put(code, foundationInfo);
                LOGGER.info("Success to get info of foundation={}", code);
                Thread.sleep(20000);
            } catch (Exception e) {
                LOGGER.warn("Failed to get info of foundation={}, due to {}", code, e.getMessage());
                e.printStackTrace();
            }
        });
        String content = MailUtils.renderFoundationEmailContent(foundationService.rankingFoundations());
        String subject = getDateYYYY_MM_DD() + " Foundation Infos";
        mailService.sendMimeMessage(PropertiesConfigLoader.fromUser(), PropertiesConfigLoader.toUser(), subject, content);
        mailService.sendMimeMessage(PropertiesConfigLoader.fromUser(), PropertiesConfigLoader.ChaoQqEmail(), subject, content);
        mailService.sendMimeMessage(PropertiesConfigLoader.fromUser(), PropertiesConfigLoader.LumenQqEmail(), subject, content);
    }
}
