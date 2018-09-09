package com.mqtt.app.generators;

import com.mqtt.app.App;
import com.mqtt.app.states.MenuState;
import com.mqtt.app.states.SettingsState;
import com.mqtt.app.states.SubscriberState;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.eclipse.paho.client.mqttv3.MqttException;

public class GUI extends Application {

    static TilePane layout = new TilePane();

    public GUI() {

    }

    /**
     * This function is responsible for launching the Graphic User Interface
     * system.
     *
     * @param args
     */
    public static void go(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        getMenu();

        stage.setTitle("MQTT Applicaton");
        stage.setScene(new Scene(layout,
                GuiSettings.getWidth(),
                GuiSettings.getHeight())
        );

        stage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    private Scene getStage() {
        StackPane l = new StackPane();
        Scene s = new Scene(l);
        return s;
    }

    /**
     * Starts the chosen module.
     *
     * @param option String with the module option.
     * @param args String original payload.
     * @throws MqttException
     * @throws InterruptedException
     */
    private void initApp(String option, String args) throws MqttException, InterruptedException {
        App a = new App();
        a.init(option, args);
    }

    /**
     * Fills a pane background with a chose Hex color.
     *
     * @param pane The original pane.
     * @param hexColor The Hex color (with no hash #).
     * @return The original pane modified.
     */
    public static TilePane fillBackground(TilePane pane, String hexColor) {
        pane.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#" + hexColor),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));
        return pane;
    }

    /**
     * This function is responsible for showing the Menu state of the system.
     */
    public static void getMenu() {
        clear();
        layout.getChildren().addAll(new MenuState().getMenuState());
    }

    /**
     * This function is responsible for showing the Settings state of the system.
     */
    public static void getSettings() {
        clear();
        layout.getChildren().addAll(new SettingsState().getState());
    }

    /**
     * This function is responsible for showing the Subscriber state of the system.
     */
    public static void getSubscriber() {
        clear();
        layout.getChildren().addAll(new SubscriberState().getState());
    }

    /**
     * This function is responsible for clearing the main GUI pane.
     */
    public static void clear() {
        layout.getChildren().clear();
    }
}
