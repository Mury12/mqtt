package com.mqtt.app;

import com.mqtt.app.Publish.ReplyGetter;
import com.mqtt.app.Subscribe.Subscriber;
import com.mqtt.app.states.Publisher;
import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.MqttException;

public class App {
    public static Publisher pub;
    public static Subscriber sub;
    public static String topic = Config.getTopic();

    public static void main(String[] args) throws MqttException, InterruptedException {
        String op;
        Scanner s = new Scanner(System.in);
        System.out.println("Welcolme to the MQTT Server-client Project!");
        System.out.println("What do you want to do?");
        System.out.println("Publish or Subscribe: ");
        op = s.next();

        if (op.equalsIgnoreCase("publisher") || op.equalsIgnoreCase("publish") || op.equalsIgnoreCase("pub")) {
            pub = new Publisher();
            s.nextLine();
            if (pub.connect()) {
                while (true) {
                    String str = new String();
                    Thread.sleep(Config.getTimeout());

                    System.out.println("What do you want to send?");
                    str = s.nextLine();
                    Publisher.getReply();

                    if (str.equalsIgnoreCase("exit") || str.equalsIgnoreCase("quit")) {
                        System.out.println("Program exiting. Farewell!");
                        pub.disconnect();
                        return;
                    }

                    if (pub.publish(str, topic)) {
                        System.out.println("Message sent.");
                    } else {
                        System.out.println("Something went wrong.. Try again.");
                    }
                }
            }

        }
        if (op.equalsIgnoreCase("subscriber") || op.equalsIgnoreCase("subscribe") || op.equalsIgnoreCase("sub")) {
            sub = new Subscriber();
            if (sub.connect(topic)) {
                System.out.println("Messages arrived: ");
            }
        }

    }
}
