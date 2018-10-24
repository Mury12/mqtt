/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.models;

import java.io.IOException;

/**
 *
 * @author andremury
 */
public class Operation {

    String action;
    String param;

    public Operation(String action, String param) {
        this.action = action;
        this.param = param;
    }

    public void doOperation() throws IOException {
        String plat = new String();
        String myCommand = new String();
        if (this.action.contentEquals("shutdown")) {
            if(System.getProperty("os.name").contains("Windows")){
                plat = "cmd";
                int p = Integer.parseInt(this.param)*60;
                myCommand = "shutdown -t -s "+ p;
            }else{
                plat = "bash";
            myCommand = "notify-send 'Alert' 'We are shutting down your PC due a high temperature. TTL: "+this.param+" min.'";
            Runtime.getRuntime().exec(new String[]{plat, "-c", myCommand});
            myCommand = "shutdown -t " + this.param;
            }
            System.out.println(this.param);
            Runtime.getRuntime().exec(new String[]{plat, "-c", myCommand});
        }
    }
}
