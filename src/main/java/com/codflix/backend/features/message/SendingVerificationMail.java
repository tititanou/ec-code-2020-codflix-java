package com.codflix.backend.features.message;
import com.codflix.backend.models.Email;
import com.codflix.backend.models.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.PasswordAuthentication;

public class SendingVerificationMail {

    private String myHash;
    private User user;

    public SendingVerificationMail(User user, String myHash) {
        this.user = user;
        this.myHash = myHash;
    }

    public void sendMail() {
        String link ="http://localhost:8080/ValidationController?key1=" + user.getEmail() + "&key2=" + myHash;
        System.out.println(link);
        String msg= "Bienvenue sur Cod'Flix,\n" +
                " \n" +
                "Pour activer votre compte, veuillez cliquer sur le lien ci-dessous\n" +
                "ou copier/coller dans votre navigateur Internet.\n" +
                " \n" +
                link + " \n" +
                " \n" +
                "---------------\n" +
                "Ceci est un mail automatique, Merci de ne pas y répondre.";
        Email email = new Email("contact@codflix.com", user.getEmail(), "Vérification de votre compte", msg);

        // A properties to store mail server smtp information such as the host
        // name and the port number. With this properties we create a Session
        // object from which we'll create the Message object.
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user.getEmail(), user.getPassword());
                    }
        });

        try {
            // Message is a mail msg to be send through the Transport object.
            // In the Message object we set the sender address and the
            // recipient address. Both of this address is a type of
            // InternetAddress. For the recipient address we can also set the
            // type of recipient, the value can be TO, CC or BCC. In the next
            // two lines we set the email subject and the content text.
            Message mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress(email.getSender()));
            mail.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
            mail.setSubject(email.getTitle());
            mail.setText(email.getMessage());

            // Send the msg to the recipient.
            Transport.send(mail);
        } catch (MessagingException e) {
            System.out.println("Error at SendingEmail.java: " + e);
            e.printStackTrace();
        }
    }


}
/*
package com.emailverfication;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingEmail {

	private String userEmail;
	private String myHash;

	public SendingEmail(String userEmail, String myHash) {
		this.userEmail = userEmail;
		this.myHash = myHash;
	}

	public void sendMail() {
		// Enter the email address and password for the account from which verification link will be send
		String email = "*****";
		String password = "*****";

		Properties theProperties = new Properties();

		theProperties.put("mail.smtp.auth", "true");
		theProperties.put("mail.smtp.starttls.enable", "true");
		theProperties.put("mail.smtp.host", "smtp.gmail.com");
		theProperties.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(theProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			message.setSubject("Email Verification Link");
			message.setText("Click this link to confirm your email address and complete setup for your account."
					+ "\n\nVerification Link: " + "http://localhost:8080/EmailVerification/ActivateAccount?key1=" + userEmail + "&key2=" + myHash);

			Transport.send(message);

			System.out.println("Successfully sent Verification Link");

		} catch (Exception e) {
			System.out.println("Error at SendingEmail.java: " + e);
		}

	}

}
*/