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

public class pvandpublicchatcontroller {

    @FXML
    private Button pvbutton,backbutton;

    @FXML
    private Button publicchat;


    private Scene scene;

    private Stage stage;

    private Parent root;


    public void gotopv(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("pvChat.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public void showblocked(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("pvChat.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();




    }

    public void gotopublic(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("public chat.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public void goingback(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("serverLogin.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();




    }


}
