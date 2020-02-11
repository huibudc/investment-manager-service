package investment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMimeMessage(String from, String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            LOGGER.info("Sending email to={}", to);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            LOGGER.info("Sent email to={}", to);
        } catch (Exception e) {
            LOGGER.error("Failed to send email to {}, due to {}", to, e.getMessage());
            e.printStackTrace();
        }
    }


}
