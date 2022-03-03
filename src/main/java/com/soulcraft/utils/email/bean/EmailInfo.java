package com.soulcraft.utils.email.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class EmailInfo {

    // 邮件主题
    @Getter
    @Setter
    private String subject;

    // 邮件的文本内容
    @Getter
    @Setter
    private String htmlContent;

    // 邮件接收者的地址
    @Getter
    private final List<String> receivers;

    public EmailInfo() {
        receivers = new ArrayList<>();
    }

    public void setReceivers(String receivers) {
        if (receivers == null || "".equals(receivers.trim())) {
            return;
        }
        String[] strings = receivers.split(",");
        for (String receiver : strings) {
            addReceiver(receiver);
        }
    }

    public void addReceiver(String receiver) {
        this.receivers.add(receiver);
    }

    @Override
    public String toString() {
        return "EmailInfo{" +
                "subject='" + subject + '\'' +
                ", receivers=" + receivers +
                '}';
    }
}
