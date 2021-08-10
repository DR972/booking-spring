package by.rozmysl.booking.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
/**
 * The class is responsible for sending mail
 */
@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;

    /**
     * The method sends an email message
     * @param emailTo  recipient's postal address
     * @param subject  message description
     * @param message  message
     */
    public void sendMail(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    /**
     * The method sends a message and a file by email
     * @param emailTo  recipient's postal address
     * @param subject  message description
     * @param message  message
     * @param attachment  file path
     * @throws MessagingException if the message cannot be created
     */
    public void sendMailWithAttachment(String emailTo, String subject, String message, String attachment) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(emailTo);
        helper.setText(message);
        FileSystemResource file = new FileSystemResource(new File(attachment));
        helper.addAttachment(subject, file);
        mailSender.send(mimeMessage);
    }
}
