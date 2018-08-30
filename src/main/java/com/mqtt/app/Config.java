/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app;

import com.sun.xml.internal.ws.wsdl.writer.document.Definitions;

/**
 *
 * @author andremury
 */
public class Config {

    private static final String DEFAULT_TOPIC= "iot_data";
    private static final int DEFAULT_TIMEOUT = 100;
    private static final String DEFAULT_PROTOCOL = "tcp";
    private static final String DEFAULT_HOST = "themayhem.ddns.net";
    private static final String DEFAULT_PORT = "1883";

    private static String topic = DEFAULT_TOPIC;
    private static String connProtocol = DEFAULT_PROTOCOL;
    private static String host = DEFAULT_HOST;
    private static String port = DEFAULT_PORT;
    private static int timeout = DEFAULT_TIMEOUT;

    public static String getConnProtocol() {
        return connProtocol;
    }

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static void setConnProtocol(String connProtocol) {
        Config.connProtocol = connProtocol;
    }

    public static void setHost(String host) {
        Config.host = host;
    }

    public static void setPort(String port) {
        Config.port = port;
    }

    public static String getFullURI() {
        return getConnProtocol() + "://" + getHost() + ":" + getPort();
    }

    public static String getTopic() {
        return topic;
    }

    public static void setTopic(String topic) {
        Config.topic = topic;
    }

    
}
