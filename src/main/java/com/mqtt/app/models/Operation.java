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
        if (this.action.contentEquals("shutdown")) {
            String myCommand = "notify-send 'Alert' 'We are shutting down your PC due a high temperature.'";
            Runtime.getRuntime().exec(new String[]{"bash", "-c", myCommand});
            myCommand = "shutdown -t " + this.param;
        }
    }
}
