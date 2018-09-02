package com.mqtt.app;

/**
 * This class holds the configurations for the working of the project.
 *
 * @author andremury
 */
public class Config {

    /**
     * @var DEFAULT_TOPIC It's the default topic for subscription.
     */
    private static final String DEFAULT_TOPIC = "iot_data";
    /**
     * @var DEFAULT_REPLY_TOPIC It's the default topic for replying.
     */
    private static final String DEFAULT_REPLY_TOPIC = "andremury";
    /**
     * @var DEFAULT_TIMEOUT It's the default timeout for waiting and other
     * processes.
     */
    private static final int DEFAULT_TIMEOUT = 100;
    /**
     * @var DEFAULT_PROTOCOL It's the default connection protocol.
     */
    private static final String DEFAULT_PROTOCOL = "tcp";
    /**
     * @var DEFAULT_HOST It's the default host for message sending.
     */
    private static final String DEFAULT_HOST = "themayhem.ddns.net";
    /**
     * @var DEFAULT_PORT It's the default Mosquitto Broker port.
     */
    private static final String DEFAULT_PORT = "1883";
    /**
     * @var reply_topic This is the current replying topic.
     */
    private static String reply_topic  = DEFAULT_REPLY_TOPIC;
    /**
     * @var topic This is the current topic setting.
     */
    private static String topic = DEFAULT_TOPIC;
    /**
     * @var connProtocol This is the current protocol setting.
     */
    private static String connProtocol = DEFAULT_PROTOCOL;
    /**
     * @var host This is the current host setting.
     */
    private static String host = DEFAULT_HOST;
    /**
     * @var port This is the current port setting.
     */
    private static String port = DEFAULT_PORT;
    /**
     * @var timeout This is the default timeout setting.
     */
    private static int timeout = DEFAULT_TIMEOUT;

    /**
     * This function returns the protocol type for connection.
     *
     * @return String containing the protocol.
     */
    public static String getConnProtocol() {
        return connProtocol;
    }

    /**
     * This function returns the current set host.
     *
     * @return String containing the host for the connection.
     */
    public static String getHost() {
        return host;
    }

    /**
     * This function returns the current set port.
     *
     * @return String containing the port set for the connection.
     */
    public static String getPort() {
        return port;
    }

    /**
     * This function returns the timeout set.
     *
     * @return Int timeout.
     */
    public static int getTimeout() {
        return timeout;
    }

    /**
     * Sets the new protocol configuration.
     *
     * @param connProtocol String for udp or tcp.
     */
    public static void setConnProtocol(String connProtocol) {
        Config.connProtocol = connProtocol;
    }

    /**
     * This sets the new host configuration.
     *
     * @param host String host domain.com or IP 123.123.123.123
     */
    public static void setHost(String host) {
        Config.host = host;
    }

    /**
     * This sets the port configuration.
     *
     * @param port String for port 1883 is default for Mosquitto.
     */
    public static void setPort(String port) {
        Config.port = port;
    }

    /**
     * This function returns the full URI for the connection.
     *
     * @return String containing the full URI tcp://domain.com:1883
     */
    public static String getFullURI() {
        return getConnProtocol() + "://" + getHost() + ":" + getPort();
    }

    /**
     * This returns the current topic set.
     *
     * @return String containing the configured topic.
     */
    public static String getTopic() {
        return topic;
    }

    /**
     * This sets the new topic for subscribing.
     *
     * @param topic String with topic name.
     */
    public static void setTopic(String topic) {
        Config.topic = topic;
    }

}
