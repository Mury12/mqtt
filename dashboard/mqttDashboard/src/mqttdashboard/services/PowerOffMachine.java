/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttdashboard.services;

import com.mqtt.app.Config;
import com.mqtt.app.states.Publisher;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

public class PowerOffMachine {

    Publisher pc;
    String addr;
    int seconds;

    public PowerOffMachine(String addr, int seconds) throws MqttException {
        this.pc = new Publisher();
        this.addr = addr;
        this.seconds = seconds;
    }

    public boolean shutDownMachine(){
        Config.setTopic(addr);
        
        try {
            this.pc.connect();
            this.pc.publish("action::shutdown:"+seconds, addr);
            this.pc.disconnect();
            return true;
        } catch (MqttException ex) {
            Logger.getLogger(PowerOffMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
