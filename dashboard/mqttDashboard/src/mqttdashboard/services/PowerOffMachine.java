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
    String seconds;

    public PowerOffMachine(String addr, String min) {
        this.addr = addr;
        this.seconds = min;
    }

    public PowerOffMachine() {

    }

    @Override
    public void run() {
        System.out.println("---------------"+this.seconds);
        try {
            pc = new PublishController("action::shutdown::"+this.seconds, addr);
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

    void setPath(String addr, String param) {
        this.addr = addr;
        this.seconds = param;
    }
}
