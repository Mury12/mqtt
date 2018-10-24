/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttdashboard.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import mqttdashboard.controller.PublishController;
import org.eclipse.paho.client.mqttv3.MqttException;


public class PowerOffMachine extends Thread {

    PublishController pc;
    String addr;
    int seconds;

    public PowerOffMachine(String addr, String min) {
        this.addr = addr;
        this.seconds = seconds;
    }

    @Override
    public void run() {
        try {
            pc = new PublishController("action::shutdown::"+seconds, addr);
            pc.init();
        } catch (MqttException ex) {
            Logger.getLogger(PowerOffMachine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PowerOffMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public void shutDownMachine() throws InterruptedException {
        this.start();
    }
}
