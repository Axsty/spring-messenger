package se.iths.axel.springmessenger.messaging;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;
import se.iths.axel.springmessenger.model.Email;
import se.iths.axel.springmessenger.model.Message;

import java.util.Properties;

// får en get metod som returnerar type, dvs email
@Component("email")
public class EmailSender implements Messenger {

    private static final String FROM = "axelstaaffriborg@gmail.com";
    private static final String APP_PASSWORD = "khiy viao dlbt cbkg";

    @Override
    public void send(Message message) {
        if (!(message instanceof Email email)) {
            throw new IllegalArgumentException("Wrong type of message");
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, APP_PASSWORD);
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(FROM));
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email.getRecipient()));
            mimeMessage.setSubject(email.getSubject());
            mimeMessage.setText(email.getMessage());
            Transport.send(mimeMessage);

            System.out.println("Mail sent");
        } catch (Exception e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }
}
