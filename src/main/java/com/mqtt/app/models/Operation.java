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

    public Operation(String action) {
        this.action = action;
    }
    
    private void doOperation() throws IOException {
        String action = this.action.split("action::")[0].split(":")[0];
        String param = this.action.split("action::")[0].split(":")[1];
        
        if(action.contentEquals("shutdown")){
            String[] args = new String[] {"/bin/bash", "-c", "xterm", "with", "args"};
            Process p = new ProcessBuilder(args).start();
        }
        
    }
}
