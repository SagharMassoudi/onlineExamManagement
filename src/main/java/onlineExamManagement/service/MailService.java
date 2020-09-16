package onlineExamManagement.service;

import onlineExamManagement.model.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {
    public void sendVerificationMail(UserDto userDto) {

        Properties props =System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("saghar.massoudi384@gmail.com",
                                "0022715241");
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            String token = userDto.getToken();
            message.addRecipient(Message.RecipientType.
                    TO, new InternetAddress(userDto.getEmailAddress()));
            message.setSubject("Account Verification");
            message.setText("Click on link below inorder " +
                    "to verify your account." + "\n" + "http://localhost:8080/" +
                    "verifyAccount?token=" + token);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
