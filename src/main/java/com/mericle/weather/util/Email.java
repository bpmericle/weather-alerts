package com.mericle.weather.util;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
    private static final String FROM_EMAIL_KEY = "from";
    private static final String TO_EMAIL_KEY = "to";
    private static final String SMTP_PASSWORD_KEY = "smtp.password";
    private static final String DEFAULT_FROM = "brian.mericle@gmail.com";

    public void send(String subject, String content) throws MessagingException {
        String from = System.getProperty(FROM_EMAIL_KEY, DEFAULT_FROM);
        String password = System.getProperty(SMTP_PASSWORD_KEY);
        Objects.requireNonNull(password,
                String.format("%s is required to be set on the command line. (i.e. -D%s=<value>)", SMTP_PASSWORD_KEY,
                        SMTP_PASSWORD_KEY));

        // emily.mericle@argyleisd.com
        String to = System.getProperty(TO_EMAIL_KEY, DEFAULT_FROM);

        Properties properties = new Properties();
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        for (String toEmail : to.split(",")) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        }
        message.setSubject(subject);

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(content, "text/html; charset=utf-8");

        Multipart multiPart = new MimeMultipart(htmlPart);
        message.setContent(multiPart);

        Transport.send(message);
    }
}
