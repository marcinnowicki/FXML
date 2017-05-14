/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author marcinn
 */
public class FXML extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Pierwszy.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.getIcons().add(new Image("https://yt3.ggpht.com/-qJgUfmz5mT8/AAAAAAAAAAI/AAAAAAAAAAA/tR01voPs6H4/s900-c-k-no-mo-rj-c0xffffff/photo.jpg"));
        stage.setTitle("Some sort of application");
        stage.show();
        System.out.println("Start");
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    public static void dopisz() {
        System.out.println("Dopisuję");
        MDRSReader MD = new MDRSReader();
        MD.readThatFile();
        MD=null ;
    }
}
