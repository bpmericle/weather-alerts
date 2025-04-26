package com.mericle.weather.util;

import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final String from;
    private final List<String> to;
    private final String smtpUser;
    private final String smtpPassword;
    private final String smtpHost;
    private final String smtpPort;

    public Email(String from, String toAddresses, String smtpUser, String smtpPassword, String smtpHost,
            String smtpPort) {
        this.from = Objects.requireNonNull(from, "From address cannot be null");
        this.to = Stream.of(Objects.requireNonNull(toAddresses, "To addresses cannot be null").split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        this.smtpUser = Objects.requireNonNull(smtpUser, "SMTP user cannot be null");
        this.smtpPassword = Objects.requireNonNull(smtpPassword, "SMTP password cannot be null");
        this.smtpHost = Objects.requireNonNull(smtpHost, "SMTP host cannot be null");
        this.smtpPort = Objects.requireNonNull(smtpPort, "SMTP port cannot be null");
    }

    public void send(String subject, String content) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.user", smtpUser);
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", smtpPort);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPassword);
            }
        });

        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        for (String toEmail : to) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        }

        message.setSubject(subject);

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(content, "text/html; charset=utf-8");

        Multipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);

        Transport.send(message);
    }
}
