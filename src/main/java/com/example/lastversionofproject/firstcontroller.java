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

public class firstcontroller {

    @FXML
    private Button signin,signup;

    private Scene scene;

    private Stage stage;

    private Parent root;


    public void enterSignin(ActionEvent even) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("signin.fxml"));

        stage=(Stage)((Node)even.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();


    }

    public void enterSignup(ActionEvent action) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("signup.fxml"));

        stage=(Stage)((Node)action.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();



    }



}
