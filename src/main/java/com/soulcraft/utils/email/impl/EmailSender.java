package com.soulcraft.utils.email.impl;


import com.soulcraft.utils.email.IEmailSender;
import com.soulcraft.utils.email.authenticator.EmailAuthenticator;
import com.soulcraft.utils.email.bean.EmailInfo;
import com.soulcraft.utils.email.exception.MailSendException;
import lombok.extern.apachecommons.CommonsLog;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送API
 *
 * @author Scott
 * @since 2021年11月15日
 */
@CommonsLog
public class EmailSender implements IEmailSender {

    @Override
    public void send(String host, int port, boolean needsValidate,
                     String senderEmail, String senderName, String senderPassword,
                     EmailInfo mailInfo) throws MailSendException {
        log.info(String.format("trying to send email %s", mailInfo));
        // 判断是否需要身份认证
        EmailAuthenticator authenticator = null;
        //如果需要身份认证，则创建一个密码验证器
        if (needsValidate) {
            authenticator = new EmailAuthenticator(senderEmail, senderPassword);
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Properties p = new Properties();
        p.put("mail.smtp.host", host);
        p.put("mail.smtp.port", String.valueOf(port));
        p.put("mail.smtp.auth", Boolean.toString(needsValidate));
        Session sendMailSession = Session.getDefaultInstance(p, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(senderEmail, senderName, StandardCharsets.UTF_8.name());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            List<Address> addresses = new ArrayList<>();
            for (String address : mailInfo.getReceivers()) {
                Address to = new InternetAddress(address);
                addresses.add(to);
            }
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.addRecipients(Message.RecipientType.TO, addresses.toArray(new Address[0]));
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mailInfo.getHtmlContent(), "text/html; charset=" + StandardCharsets.UTF_8.name());
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            log.info(String.format("email sent to %s", mailInfo.getReceivers()));
        } catch (UnsupportedEncodingException e) {
            log.error("sender name is incorrect", e);
            throw new MailSendException(e);
        } catch (MessagingException e) {
            log.error("failed to send email", e);
            throw new MailSendException(e);
        }
    }
}
