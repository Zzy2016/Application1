package com.example.frameanimationtest.test;

/**
 * @author: Administrator
 * @date: 2020-04-01  简单工厂
 */
public class SendFactory {

//    普通模式
    public Sender produce(String type) {
        if (type.equals("mail")) {
            return new MailSender();
        } else if (type.equals("sms")) {
            return new SmsSender();
        } else {
            return null;
        }
    }

//多个方法
    public Sender productMail() {
        return new MailSender();
    }

    public Sender productSms() {
        return new SmsSender();
    }


//    静态方法  不需要创建实例，直接调用即可。

    public static Sender product1Mail() {
        return new MailSender();
    }

    public static Sender product1Sms() {
        return new SmsSender();
    }
}
