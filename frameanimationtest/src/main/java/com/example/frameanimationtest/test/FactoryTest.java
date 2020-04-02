package com.example.frameanimationtest.test;

/**
 * @author: Administrator
 * @date: 2020-04-01
 */
public class FactoryTest {
    public static void main(String[] args) {
        SendFactory sendFactory = new SendFactory();
        Sender sender = sendFactory.produce("sms");
        sender.Send();

        Sender sender1 = SendFactory.product1Mail();
    }
}
