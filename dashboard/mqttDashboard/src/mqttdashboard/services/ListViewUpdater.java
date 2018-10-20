/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttdashboard.services;

import com.mqtt.app.services.ReplierService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import mqttdashboard.DashboardController;
import mqttdashboard.Report;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class ListViewUpdater extends Thread {

    ListView<String> lv;
    ArrayList<Report> arr;
    String reported;
    boolean low, high;
    Double val;
    private String id;
    private String local;
    private String name;
    private Double temp;
    Report rep;
    int i = 0;
    Boolean running = true;
    static int actionIndex;

    public ListViewUpdater(ListView lv) {
        this.lv = lv;
        this.arr = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!ReplierService.got()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ListViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (true) {
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ListViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            String payback = ReplierService.getReply();
            if (payback.contains("addSensor")) {
                continue;
            }
            rep = new Report(getLocation(payback) + "::" + getMachine(payback),
                    getLocation(payback), getMachine(payback), getTemperature(payback));

            if (!checkIfExists(arr, rep.getId())) {
                arr.add(rep);
            } else {
                arr = replaceItemOnList(arr, rep);
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (running) {
                        lv.getItems().clear();
                        for (Report r : arr) {
                            lv.getItems().add(r.getMessage());
                        }
                    }
                }
            });

            lv.setCellFactory((ListView<String> lv) -> {
                ListCell<String> cell = new ListCell<>();
                ContextMenu cm = new ContextMenu();
                MenuItem pwoff = new MenuItem();
                pwoff.textProperty().bind(Bindings.format("Power off this machine"));
                pwoff.setOnAction(evt -> {
                    powerOffMachine();
                });
                MenuItem rmvFlist = new MenuItem();
                rmvFlist.setText("Remove from list");
                rmvFlist.setOnAction(evt -> {
                    removeFromList();
                });

                MenuItem listenLocation = new MenuItem();
                listenLocation.setText("Listen this location");
                listenLocation.setOnAction(evt -> {
                    
                    lv.getItems().clear();
                    try {
                        subscribeOnRoom();
                    } catch (MqttException ex) {
                        Logger.getLogger(ListViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                cm.getItems().addAll(pwoff, rmvFlist, listenLocation);

                cell.textProperty().bind(cell.itemProperty());
                cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                    if (isNowEmpty) {
                        cell.setContextMenu(null);
                    } else {
                        cell.setContextMenu(cm);
                    }
                });

                return cell;
            });

            if (i >= 60000 / 100) {
                arr = updateArray(arr);
                i = 0;
            }
        }
    }

    public void main(String args[]) {

    }

    public ListView<String> updateListView() {
        this.start();
        return lv;
    }

    public String getLocation(String payback) {
        return payback.split(":")[0];
    }

    public String getMachine(String payback) {
        return payback.split(":")[1];
    }

    public Double getTemperature(String payback) {
        return Double.parseDouble(payback.split(": ")[1]);
    }

    private boolean checkIfExists(ArrayList<Report> arr, String id) {
        int i = 0;

        for (Report r : arr) {
            if (r.getId().contentEquals(id)) {
                return true;
            }
            ++i;
        }
        return false;
    }

    private ArrayList<Report> replaceItemOnList(ArrayList<Report> arr, Report rep) {
        int i = 0;
        for (Report r : arr) {
            if (!r.getId().contentEquals(rep.getId())) {
                arr.get(i).set(rep);
            }
            ++i;
        }
        return arr;
    }

    private ArrayList<Report> updateArray(ArrayList<Report> arr) {

        for (Iterator<Report> it = arr.iterator(); it.hasNext();) {
            Report r = it.next();
            if (Instant.now().getEpochSecond() > r.getLastReport()) {
                it.remove();
            }
        }

        return arr;
    }

    private void removeFromList() {
        Report r = arr.get(actionIndex);
        System.out.println("Removing " + r.getName());
        this.arr.remove(r);
        this.lv.getItems().remove(actionIndex);
    }

    private void powerOffMachine() {
        Report r = arr.get(actionIndex);
        System.out.println("Shuting down " + r.getName() + " at room " + r.getLocal());
    }

    private void subscribeOnRoom() throws MqttException {
        Report r = arr.get(actionIndex);        
        DashboardController.changeSubscription("sensor/"+r.getLocal()+"/#");
        
    }


}
