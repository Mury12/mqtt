/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttdashboard.services;

import com.mqtt.app.Config;
import com.mqtt.app.services.ReplierService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javax.swing.JOptionPane;
import mqttdashboard.controller.DashboardController;
import mqttdashboard.models.Report;
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
    private boolean reset;
    private String param;

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
        while (running) {
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
                    if (running && lv != null) {
                        lv.getItems().clear();
                        for (Report r : arr) {
                            lv.getItems().add(r.getMessage());
                        }
                    }
                }
            });

        }
    }

    public void main(String args[]) {

    }

    public ListView<String> updateListView() {
        this.start();
        setContextMenu();
        return lv;
    }

    public String getLocation(String payback) {
        return payback.split(":")[0];
    }

    public String getMachine(String payback) {
        return payback.split(":")[1];
    }

    public Double getTemperature(String payback) {
        if (!payback.split(":")[1].isEmpty()) {
            return Double.parseDouble(payback.split(": ")[1]);
        } else {
            return arr.get(actionIndex).getTemp();
        }
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
        for(Report r : arr){
            if(r.getId().matches(rep.getId())){
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

    private void powerOffMachine() throws InterruptedException {
        Report r = arr.get(actionIndex);
        TextInputDialog dia = new TextInputDialog("0");
        dia.setTitle("Tempo para desligar");
        dia.setHeaderText("Digite o tempo em minutos para desligar:");
        dia.setContentText("(min):");
        Optional<String> result = dia.showAndWait();
        result.ifPresent(name -> {
            if (name != null) {
                this.param = name;
            } else {
                this.param = "0";
            }
        });
        PowerOffMachine pof = new PowerOffMachine("server/" + r.getLocal() + "/" + r.getName(), this.param);
        pof.shutDownMachine();
    }

    private void powerOffRecursive() throws InterruptedException {
        TextInputDialog dia = new TextInputDialog("0");
        dia.setTitle("Tempo para desligar");
        dia.setHeaderText("Digite o tempo em minutos para desligar TODAS as máquinas:");
        dia.setContentText("(min):");
        Optional<String> result = dia.showAndWait();
        result.ifPresent(name -> {
            if (name != null) {
                this.param = name;
            } else {
                this.param = "0";
            }
        });

        PowerOffMachine pof;

        for (Iterator<Report> it = arr.iterator(); it.hasNext();) {
            Report r = it.next();
            pof = null;
            pof = new PowerOffMachine();
            pof.setPath("server/" + r.getLocal() + "/" + r.getName(), this.param);
            pof.shutDownMachine();
        }

    }

    private void remoteControl() throws InterruptedException {
        Report r = arr.get(actionIndex);
        this.param = "";
        System.out.println(this.param);
        RemoteAccess rma = new RemoteAccess("server/" + r.getLocal() + "/" + r.getName(), this.param);
        rma.remoteControl();
    }

    private void subscribeOnRoom() throws MqttException {
        Report r = arr.get(actionIndex);
        this.changeSubscription("sensor/" + r.getLocal() + "/#");

    }

    private void setContextMenu() {
        lv.setCellFactory((ListView<String> lv) -> {
            ListCell<String> cell = new ListCell<>();
            ContextMenu cm = new ContextMenu();
            if (cm.isShowing()) {
                this.running = false;
            } else {
                this.running = true;
            }

            MenuItem pwoff = new MenuItem();
            pwoff.textProperty().bind(Bindings.format("Desligar"));
            pwoff.setOnAction(evt -> {
                actionIndex = cell.getIndex();

                try {
                    powerOffMachine();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ListViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            MenuItem remote = new MenuItem();
            remote.textProperty().bind(Bindings.format("Acesso remoto"));
            remote.setOnAction((evt) -> {
                actionIndex = cell.getIndex();

                try {
                    remoteControl();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ListViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            MenuItem pwall = new MenuItem();
            pwall.textProperty().bind(Bindings.format("Desligar recursivamente"));
            pwall.setOnAction(evt -> {
                try {
                    powerOffRecursive();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ListViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            cm.getItems().addAll(remote, pwoff, pwall);

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
    }

    public void reset() {
        this.reset = true;
    }

    public void changeSubscription(String topic) throws MqttException {
        System.out.println("Changing to topic " + topic);
        DashboardController.sc.disconnect();
        Config.setTopic(topic);
        DashboardController.sc.connect();

    }

    public void toggleRun() {
        this.running = !this.running;
    }
}
