package investment.foundation;

import investment.config.DBConfigLoader;
import investment.config.PropertiesConfigLoader;
import investment.foundation.dao.HealthMapper;
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

import static investment.config.MailSenderConfig.*;

@Component
public class Scheduler {
    private final static Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
    private final CrawlerService crawlerService;
    private final FoundationService foundationService;
    private final MailService mailService;
    private final DBConfigLoader dbConfigLoader;
    private final HealthMapper healthMapper;

    @Autowired
    public Scheduler(CrawlerService crawlerService, FoundationService foundationService, MailService mailService, DBConfigLoader dbConfigLoader, HealthMapper healthMapper) {
        this.crawlerService = crawlerService;
        this.foundationService = foundationService;
        this.mailService = mailService;
        this.dbConfigLoader = dbConfigLoader;
        this.healthMapper = healthMapper;
    }

    @Scheduled(fixedDelay = 300000)
    public void heartBeat() {
        if (healthMapper.health() == 1) {
            LOGGER.info("I'm running well");
        } else {
            LOGGER.info("I'm running not well, can't connect to DB");
            mailService.sendMimeMessage(PropertiesConfigLoader.fromUser(), PropertiesConfigLoader.toUser(), "Exception Alert", "Failed to connect to DB, please check.", "");
        }
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
        dbConfigLoader.investFoundations().keySet().forEach(code -> {
            try {
                LOGGER.info("Start to get info of foundation={}", code);
                Foundation foundationInfo = crawlerService.getFoundationInfo(code);
                foundationService.addFoundation(foundationInfo);
                LOGGER.info("Success to get info of foundation={}", code);
                Thread.sleep(20000);
            } catch (Exception e) {
                LOGGER.warn("Failed to get info of foundation={}, due to {}", code, e.getMessage());
                e.printStackTrace();
            }
        });
        String content = MailUtils.renderFoundationEmailContent(foundationService.rankingFoundations());
        mailService.sendMimeMessage(PropertiesConfigLoader.fromUser(), PropertiesConfigLoader.toUser(), "Foundation Infos", content, PropertiesConfigLoader.ccUser());
    }
}
