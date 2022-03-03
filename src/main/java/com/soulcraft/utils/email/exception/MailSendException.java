package com.soulcraft.utils.email.exception;


/**
 * 邮件发送错误
 *
 * @author Scott
 * @since 2021年11月15日
 */
public class MailSendException extends Exception {
    public MailSendException() {
        super();
    }

    public MailSendException(String message) {
        super(message);
    }

    public MailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailSendException(Throwable cause) {
        super(cause);
    }

    protected MailSendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
