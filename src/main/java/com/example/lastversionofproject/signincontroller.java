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

public class signincontroller {

    private Scene scene;

    private Stage stage;

    private Parent root;

    @FXML
    private Button backtofirstbt;

    @FXML
    private TextField usernamefield,passwordfield;

    @FXML
    private Button signinrequest;

    private String username,password;


    public void backtofirst(ActionEvent action) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("first.fxml"));

        stage=(Stage)((Node)action.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();



    }

    public boolean signin(){

        boolean zaza = false;

        try {

            username = usernamefield.getText();

            password = passwordfield.getText();

            for(User USS:Client.userlist){

                if(USS.getUsername().equals(username)){

                    if(USS.getPassword().equals(password)){

                        Client.userOfProgram=USS;

                        zaza=true;

                    }

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return zaza;
    }

    public void switchtoserverlogin(ActionEvent event) throws IOException {

        if(signin()) {

            root = FXMLLoader.load(Main.class.getResource("choose.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
        }


    }

}
