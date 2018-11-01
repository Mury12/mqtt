/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.subscribe;

import com.mqtt.app.Config;
import com.mqtt.app.models.Operation;
import com.mqtt.app.services.ReplierService;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * This class is responsible for managing the callback sent to the client by the
 * server.
 *
 * @author andremury
 */
public class SubscriberCallback implements MqttCallback {

    /**
     * This function is responsible to set a message when the server can't be
     * reached and the connection isn't successful.
     *
     * @param thrwbl Exception generated by the MQTT exception class.
     */
    public void connectionLost(Throwable thrwbl) {
        System.out.println("Connection lost. Trying to reconnect..");
        try {
            reconnect();
        } catch (MqttException ex) {
            System.out.println("Connection has dropped. Please restart the subscriber module.");
        }
    }

    /**
     * This function is responsible for return a message to the client when its
     * message arrives successfully.
     *
     * @param string
     * @param mm
     * @throws java.io.IOException
     */
    public void messageArrived(String string, MqttMessage mm) throws IOException, InterruptedException {
        string = new String(mm.getPayload());
        String action = string.split("::")[1];
        String param = string.split("::")[2];
        System.out.println(string);
        Operation op = new Operation(action, param);
        op.doOperation();

    }

    /**
     * This function is responsible for showing a message to the client when
     * everything went right and the message was processed completely.
     *
     * @param imdt Message token generated by the server.
     */
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This function is responsible for the reconnect trying.
     *
     * @return Boolean success state.
     * @throws MqttException
     */
    private boolean reconnect() throws MqttException {
        try {
            Subscriber.connect("server/" + Config.getLocation() + "/" + Config.getMachineName());
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }
}
