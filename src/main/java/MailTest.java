//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.security.Security;
//import java.util.Properties;
//
//public class MailTest {
//    public static void main(String[] args) throws MessagingException {
//        int port = 465;
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.163.com");//链接服务器
//        mailSender.setPort(port);
//        mailSender.setUsername("huibudc_auto_test@163.com");//账号
//        mailSender.setPassword("XIANG900427");//密码
//        mailSender.setDefaultEncoding("UTF-8");
//
////        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//        // Get a Properties object
//        Properties props = new Properties();
//        props.setProperty("mail.smtp.host", "smtp.163.com");
//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.port", "465");
//        props.setProperty("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.auth", "true");
//        mailSender.setJavaMailProperties(props);
//
//        System.out.println("Sending email to " + "xiang1990_ok@126.com");
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom("huibudc_auto_test@163.com");
//        helper.setTo("xiang1990_ok@126.com");
//        helper.setSubject("Test");
//        helper.setText("test", true);
//        mailSender.send(message);
//        System.out.println("Sent email to " + "xiang1990_ok@126.com");
//    }
//}
