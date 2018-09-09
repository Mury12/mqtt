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

    private static final int DEFAULT_HEIGHT = 250;
    private static final int DEFAULT_WIDTH = 600;

    private static int height = DEFAULT_HEIGHT;
    private static int width = DEFAULT_WIDTH;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        GuiSettings.height = height;
    }

    public static void setWidth(int width) {
        GuiSettings.width = width;
    }

    public static double getTileHeight(int rows) {
        return DEFAULT_HEIGHT / rows;
    }

    public static double getTileWidth(int cols) {
        return DEFAULT_WIDTH / cols;
    }

}
