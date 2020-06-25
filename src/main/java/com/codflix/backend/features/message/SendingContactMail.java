package com.codflix.backend.features.message;

import com.codflix.backend.models.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendingContactMail {
        private String sender;
        private String subject;
        private String message;

        public SendingContactMail(String sender, String subject, String message) {
            this.sender = sender;
            this.subject = subject;
            this.message = message;
        }

        public void sendMail() {
            Email email = new Email(sender, "contact@codflix.com", subject, message);
            try {
            HtmlEmail mail = new HtmlEmail();
            mail.setFrom(email.getSender());
            mail.addTo(email.getRecipient());
            mail.addBcc(email.getSender());
            mail.setSubject(email.getTitle());
            mail.setTextMsg(email.getMessage());
            mail.setBounceAddress(email.getSender());
            mail.setSSL(true);
            mail.setSmtpPort(465);
            mail.send();
            } catch ( EmailException e) {
                System.out.println("Error at SendingEmail.java: " + e);
                e.printStackTrace();
            }
        }
}
