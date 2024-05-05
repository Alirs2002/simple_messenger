package com.example.lastversionofproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class coursescontroller {


    private Scene scene;

    private Stage stage;

    private Parent root;

    @FXML
    private Button gitbutton,backbutton;

    public void github(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("github.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();


    }
    public void goback(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("tutorial.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();


    }


}
