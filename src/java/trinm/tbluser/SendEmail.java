/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tbluser;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author trinm
 */
public class SendEmail {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public String sendEmail(String username) {
        String random = getRandom();
        String from = "tringuyen11122000";
        String pass = "Minhtri11122000";
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session sesionMail = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(sesionMail);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));

            message.setSubject("User Email Verification");
            message.setText("Registered successfully.Please verify your account using this code: " + random);
            Transport transport = sesionMail.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ex) {
            LOGGER.error("CreateRecordServlet_AddressException: " + ex.getMessage());
        } catch (MessagingException ex) {
            LOGGER.error("CreateRecordServlet_MessagingException: " + ex.getMessage());
        }
        return random;
    }
}
