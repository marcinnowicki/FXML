/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.BufferedReader;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author marcinn
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnExternal;
    @FXML
    private TextFlow exStatusWindow;

    @FXML
    private ComboBox exSelectSystem;

    @FXML
    private AreaChart aboutAreaChart;

    @FXML
    private TreeView<String> locationTreeView;

    @FXML
    private Button btWypiszWymaluj;

    @FXML
    private void btnExitOnAction(ActionEvent event) {
        System.out.println("You clicked me!");
        // label.setText("Hello World!");
        Date currentDate = GregorianCalendar.getInstance().getTime();
        // String output = new DateTime( currentDate ).toString("yyyy-MM-dd HH:mm:ss");
        Text text1 = new Text("Hello " + currentDate + "\n");
        exStatusWindow.getChildren().clear();
        exStatusWindow.getChildren().addAll(text1);
        Text text2 = new Text("Reading from file " + exSelectSystem.getSelectionModel().getSelectedItem().toString());
        exStatusWindow.getChildren().addAll(text2);
        MDRSReader MD = new MDRSReader();
        exStatusWindow.getChildren().addAll((Text) MD.readThatFileOut(exSelectSystem.getSelectionModel().getSelectedItem().toString()));
        MD = null;
    }

    @FXML
    private void btnExternalOnAction(ActionEvent event) throws IOException {
        System.out.println("External: You clicked me!");
        Text text2 = new Text("Executing external ");
        exStatusWindow.getChildren().addAll(text2);
        Process process = null;
//        try {
//            // "c:\Program Files\Java\jdk1.8.0_121\bin\java.exe" -jar C:\Users\marcinn\Documents\MDRS\saxon8.jar
//            // eod\mdrs_out\MD.IMPORT.BASISSPREAD_answer.xml check.xslt
//
//            process = new ProcessBuilder(
//                    "c:\\Program Files\\Java\\jdk1.8.0_121\\bin\\java.exe", "-jar", " C:\\Users\\marcinn\\Documents\\MDRS\\saxon8.jar ", "eod\\mdrs_out\\MD.IMPORT.BASISSPREAD_answer.xml ", "check.xslt").start();
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        InputStream is = process.getInputStream();
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);
//        String line;
//
//        // System.out.printf("Output of running %s is:", Arrays.toString(args));
//
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
        try {
            String line;
            Process p = Runtime.getRuntime().exec("c:\\Program Files\\Java\\jdk1.8.0_121\\bin\\java.exe -jar C:\\Users\\marcinn\\Documents\\MDRS\\saxon8.jar C:\\Users\\marcinn\\Documents\\MDRS\\eod\\mdrs_out\\MD.IMPORT.BASISSPREAD_answer.xml C:\\Users\\marcinn\\Documents\\MDRS\\check.xslt");
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = bri.readLine()) != null) {
                // System.out.println(line);
                text2.setText(text2.getText() + "\n" + line );
            }
            exStatusWindow.getChildren().addAll(text2);
            bri.close();
            while ((line = bre.readLine()) != null) {
                // System.out.println(line);
                text2.setText(text2.getText() + "\n" + line);
            }
            exStatusWindow.getChildren().addAll(text2);
            bre.close();
            p.waitFor();
            System.out.println("Done.");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @FXML
    private void btWypiszWymalujOnAction(ActionEvent event) {
        System.out.println("You clicked Wypisz wymaluj!");
        // label.setText("Hello World!");
        for (TreeItem<String> child : locationTreeView.getRoot().getChildren()) {
            // CheckBoxTreeItem item = (CheckBoxTreeItem<String>) child;

            System.out.println("You clicked Wypisz wymaluj! - "
                    + child.toString() + " "
                    + child.getChildren().toArray().length + " "
                    + //item.isSelected()
                    ((CheckBoxTreeItem<String>) child).isSelected()
            );
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void btnStartOnAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("Button Action\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        btnStart.setOnAction((event) -> {
//            // Button was clicked, do something...
//            System.out.println("Button Action from initialize\n");
//        });
//        btnStart.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("hello there,  you clicked me");
//            }
//        }
//        );

        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("About");
                Date currentDate = GregorianCalendar.getInstance().getTime();
                // String output = new DateTime( currentDate ).toString("yyyy-MM-dd HH:mm:ss");
                Text text1 = new Text("Hello " + currentDate + "\n");
                exStatusWindow.getChildren().add(text1);
            }
        });

        exSelectSystem.getItems().addAll(
                "C:\\Users\\marcinn\\Documents\\eod\\mdrs_out\\MD.IMPORT.BASISSPREAD_answer.xml",
                "C:\\Users\\marcinn\\Documents\\eod\\mdrs_out\\MD.IMPORT.BONDPRICES_answer.xml",
                "C:\\Users\\marcinn\\Documents\\eod\\mdrs_out\\MD.IMPORT.BONDPRICES_UPD_answer.xml",
                "C:\\Users\\marcinn\\Documents\\eod\\mdrs_out\\MD.IMPORT.BONDSPREADS_answer.xml",
                "C:\\Users\\marcinn\\Documents\\eod\\mdrs_out\\MD.IMPORT.CRD_SPREADS_INTRADAY_answer.xml");
        exSelectSystem.getSelectionModel().select(0);

        // aboutAreaChart.
        File dir = new File("C:\\Users\\marcinn\\Documents\\eod\\mdrs_out\\");
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            if (file.isFile()) {
                // System.out.println(file.getName() + " " + dir.getAbsolutePath()); 
                exSelectSystem.getItems().addAll(dir.getAbsolutePath() + "\\" + file.getName());
            }
        }

        // loadTreeItems("initial 1", "initial 2", "initial 3");
        // loadCheckBoxItems();
    }

    public void loadCheckBoxItems() {
        CheckBoxTreeItem<String> rootItem
                = new CheckBoxTreeItem<String>("Total list");
        rootItem.setExpanded(true);

        final TreeView tree = new TreeView(rootItem);
        tree.setEditable(true);

        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        for (int i = 0; i < 8; i++) {
            final CheckBoxTreeItem<String> checkBoxTreeItem
                    = new CheckBoxTreeItem<String>("List " + (i + 1));
            rootItem.getChildren().add(checkBoxTreeItem);
        }

        locationTreeView.setRoot(rootItem);
        locationTreeView.setShowRoot(true);
    }

    // loads some strings into the tree in the application UI.
    public void loadTreeItems(String... rootItems) {
        TreeItem<String> root = new TreeItem<String>("Root Node");

        root.setExpanded(true);
        for (String itemString : rootItems) {
            TreeItem<String> MyItem = new TreeItem<String>(itemString);

            root.getChildren().add(MyItem);
        }

        locationTreeView.setRoot(root);

    }

}
