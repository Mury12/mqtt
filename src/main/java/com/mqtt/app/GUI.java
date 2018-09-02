/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app;

import com.mqtt.app.Services.ReplierService;
import com.mqtt.app.states.PublishingState;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.eclipse.paho.client.mqttv3.MqttException;

public class GUI extends Application implements EventHandler<ActionEvent> {

    boolean publish = false,
            subscribe = false;

    public Scene root;
    Label reply = new Label();

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MQTT Applicaton Example");
        root = setInitialStage();
        stage.setScene(root);
        stage.show();
    }

    private Scene getStage() {
        StackPane l = new StackPane();
        Scene s = new Scene(l);
        return s;
    }

    /**
     * Defines an initial pane. 
     * @return the scene.
     */
    private Scene setInitialStage() {
        final TilePane layout = new TilePane();
        TilePane left = new TilePane();
        final TilePane right = new TilePane();
        final Button pub = new Button("Publisher");
        final Button sub = new Button("Subscriber");

        left.setOrientation(Orientation.VERTICAL);
        left.setPrefTileHeight(50);
        left.setPrefTileWidth(500);
        left = fillBackground(left, "c7c7c7");
        left.getChildren().addAll(pub, sub);

        layout.setOrientation(Orientation.VERTICAL);
        layout.getChildren().addAll(left, right);

        pub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                publish = true;
                PublishingState pubs = new PublishingState();
                pubs.setPublisherModule(layout);
            }

        });
        sub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                subscribe = true;
                try {
                    App app = new App();
                    app.init("sub", "");
                } catch (MqttException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return new Scene(layout, 500, 100);
    }

    /**
     * Starts the chosen module.
     * @param option String with the module option.
     * @param args String original payload.
     * @throws MqttException
     * @throws InterruptedException 
     */
    private void initApp(String option, String args) throws MqttException, InterruptedException {
        App a = new App();
        a.init(option, args);
    }

    public void handle(ActionEvent t) {
        if (publish || subscribe) {
            System.out.println("dec");
        }
    }

    /**
     * Fills a pane background with a chose Hex color.
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


}
