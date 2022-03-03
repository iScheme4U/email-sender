package com.soulcraft.utils.email.authenticator;

import lombok.AllArgsConstructor;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮箱账户认证
 *
 * @author Scott
 * @since 2021年11月15日
 */
@AllArgsConstructor
public class EmailAuthenticator extends Authenticator {
    private final String username;
    private final String password;

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
