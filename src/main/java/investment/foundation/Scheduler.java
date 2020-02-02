package investment.foundation;

import investment.foundation.dao.FoundationMapper;
import investment.foundation.modal.Foundation;
import investment.service.MailService;
import investment.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static investment.config.MailSenderConfig.FROM_USER;
import static investment.config.MailSenderConfig.TO_USER;
import static investment.foundation.Config.FOUNDATIONS;

@Component
public class Scheduler {
    private final static Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
    private final CrawlerService crawlerService;
    private final FoundationMapper foundationMapper;
    private final MailService mailService;

    @Autowired
    public Scheduler(CrawlerService crawlerService, FoundationMapper foundationMapper, MailService mailService) {
        this.crawlerService = crawlerService;
        this.foundationMapper = foundationMapper;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 */20 09-23 ? * *")
    public void scheduleJob() {
        FOUNDATIONS.keySet().forEach( code -> {
            try {
                LOGGER.info("Start to get info of foundation={}", code);
                Foundation foundationInfo = crawlerService.getFoundationInfo(code);
                foundationMapper.addFoundation(foundationInfo);
                LOGGER.info("Success to get info of foundation={}", code);
            } catch (Exception e) {
                LOGGER.warn("Failed to get info of foundation={}, due to {}", code, e.getMessage());
                e.printStackTrace();
            }
        });
        mailService.sendMimeMessage(FROM_USER, TO_USER, "Foundation Infos", MailUtils.renderFoundationEmailContent(foundationMapper.allFoundations()));
    }
}
