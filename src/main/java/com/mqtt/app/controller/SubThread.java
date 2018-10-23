/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andremury
 */
public class SubThread extends Thread {

    String message;

    public SubThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            doOperation();
        } catch (IOException ex) {
            Logger.getLogger(SubThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doOperation() throws IOException {
        String action = this.message.split("action::")[0].split(":")[0];
        String param = this.message.split("action::")[0].split(":")[1];
        
        if(action.contentEquals("shutdown")){
            String[] args = new String[] {"/bin/bash", "-c", "xterm", "with", "args"};
            Process p = new ProcessBuilder(args).start();
        }
        
    }

    public void shutDown() {
        this.start();
    }

}
