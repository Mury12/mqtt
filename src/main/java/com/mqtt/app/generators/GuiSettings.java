/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.generators;

/**
 *
 * @author andremury
 */
public class GuiSettings {

    /**
     * Sets the default value for screen height.
     *
     * @var DEFAULT_HEIGHT
     */
    private static final int DEFAULT_HEIGHT = 250;
    /**
     * Sets the default value for screen width.
     *
     * @var DEFAULT_WIDTH
     */
    private static final int DEFAULT_WIDTH = 600;
    /**
     * Sets the current value for screen height.
     *
     * @var height
     */
    private static int height = DEFAULT_HEIGHT;
    /**
     * Sets the default value for screen width.
     *
     * @var width
     */
    private static int width = DEFAULT_WIDTH;

    /**
     * Gets the current screen width.
     *
     * @return int width.
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Gets the current screen height.
     *
     * @return int height.
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Sets the screen height replacing defaults.
     *
     * @param height
     */
    public static void setHeight(int height) {
        GuiSettings.height = height;
    }

    /**
     * Sets the screen width replacing defaults.
     *
     * @param width
     */
    public static void setWidth(int width) {
        GuiSettings.width = width;
    }

    /**
     * Gets the preferred tile height for each children tile height.
     *
     * @param rows number of row to divide each row.
     * @return double with the tile height
     */
    public static double getTileHeight(int rows) {
        return DEFAULT_HEIGHT / rows;
    }

    /**
     * Gets the preferred tile width for each children tile width.
     *
     * @param rows number of row to divide each row.
     * @return double with the tile width
     */
    public static double getTileWidth(int cols) {
        return DEFAULT_WIDTH / cols;
    }

}
