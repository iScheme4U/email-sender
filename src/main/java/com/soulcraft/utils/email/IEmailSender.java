package com.soulcraft.utils.email;


import com.soulcraft.utils.email.bean.EmailInfo;
import com.soulcraft.utils.email.exception.MailSendException;

/**
 * 邮件发送API
 *
 * @author Scott
 * @since 2021年11月15日
 */
public interface IEmailSender {
    /**
     * <pre>
     * 以HTML格式发送邮件
     * </pre>
     *
     * @param host           邮件服务器地址
     * @param port           端口
     * @param needsValidate  是否需要校验
     * @param senderEmail    发送人邮箱地址
     * @param senderName     发送人名称
     * @param senderPassword 发送人账号密码
     * @param mailInfo       待发送的邮件信息
     * @throws MailSendException 邮件发送失败
     */
    void send(String host, int port, boolean needsValidate,
              String senderEmail, String senderName, String senderPassword,
              EmailInfo mailInfo) throws MailSendException;
}
