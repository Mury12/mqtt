/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app;

import com.mqtt.app.Services.Replyer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.stage.WindowEvent;
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
        root = getInitialStage();
        stage.setScene(root);
        stage.show();
        init(stage);
    }

    private Scene getStage() {
        StackPane l = new StackPane();
        Scene s = new Scene(l);
        return s;
    }

    private Scene getInitialStage() {
        TilePane layout = new TilePane();
        TilePane left = new TilePane();
        TilePane right = new TilePane();
        final Button pub = new Button("Publisher");
        final Button sub = new Button("Subscriber");
        reply.setText("Reply: " + Replyer.getReply());
        pub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if (t.getSource() == pub) {
                    publish = true;
                    try {
                        initApp("pub");
                        Thread.sleep(500);
                        reply.setText(Replyer.getReply());
                    } catch (MqttException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        sub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if (t.getSource() == sub) {
                    subscribe = true;
                    try {
                        initApp("sub");
                    } catch (MqttException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        left.setOrientation(Orientation.VERTICAL);
        left.setPrefTileHeight(50);
        left.setPrefTileWidth(100);
        right.setPrefTileHeight(50);
        right.setPrefTileWidth(200);
        right.getChildren().add(reply);
        left.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
        left.getChildren().add(pub);
        left.getChildren().add(sub);
        layout.setOrientation(Orientation.HORIZONTAL);
        layout.getChildren().add(left);
        layout.getChildren().add(right);

        layout.setPrefTileHeight(500);
        layout.setPrefTileWidth(200);
        return new Scene(layout, 500, 500);
    }

    private void initApp(String option) throws MqttException, InterruptedException {
        App a = new App();
        a.init(option);
    }

    public void handle(ActionEvent t) {
        if (publish || subscribe) {
            System.out.println("dec");
        }
    }

    public void init(Stage stage) {


    }

}
