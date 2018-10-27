/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttdashboard.services;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mqttdashboard.controller.PublishController;
import org.eclipse.paho.client.mqttv3.MqttException;


public class RemoteAccess extends Thread {

    PublishController pc;
    String addr;
    String param;

    public RemoteAccess(String addr, String param) {
        this.addr = addr;
        this.param = param;
    }

    @Override
    public void run() {
        try {
            System.out.println("---------------"+this.param);
            openxTerm();
            try {
                
                pc = new PublishController("action::remote::"+this.param, addr);
                pc.init();
            } catch (MqttException ex) {
                Logger.getLogger(RemoteAccess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(RemoteAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(RemoteAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public void remoteControl() throws InterruptedException {
        this.start();
    }

    private void openxTerm() throws IOException {
        String myCommand = "xterm -e /bin/bash -l -c \"nc -lvvp 1890 | nc -lvvp 1891\"";
        Runtime.getRuntime().exec(new String[]{"bash", "-c", myCommand});
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(RemoteAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
