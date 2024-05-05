package com.example.lastversionofproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class signupcontroller {

    private Scene scene;

    private Stage stage;

    private Parent root;

    @FXML
    private Button backtofirsbt;

    @FXML
    private Button signuprequest;

    @FXML
    private TextField usernamefield,passwordfield,emailfield;

    private String username,password,email;

    public void backtofirst(ActionEvent action) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("first.fxml"));

        stage=(Stage)((Node)action.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public boolean signup() throws IOException {

        boolean jordi;

        username=usernamefield.getText();

        password=passwordfield.getText();

        email=emailfield.getText();

        if(password.length()>=8){

            User user=new User(username,password,email);

            Client.userlist.add(user);

            Client.userOfProgram=user;

            jordi=true;
        }
        else{
            jordi=false;
        }

    return jordi;
    }

    public void switchtoserverlogin(ActionEvent event) throws IOException {


        if(signup()) {

            root = FXMLLoader.load(Main.class.getResource("choose.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
        }

    }




}
