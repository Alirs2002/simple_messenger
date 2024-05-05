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

public class githubcontroller {

    private static int chosen_index;

    @FXML
    private Button show,backbutton;

    @FXML
    private TextField index_tf;

    private Scene scene;

    private Stage stage;

    private Parent root;


    public static int get_index(){
        return chosen_index;
    }

    public void showmedia(ActionEvent event) throws IOException {

        chosen_index=Integer.parseInt(index_tf.getText());

        root= FXMLLoader.load(Main.class.getResource("media.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public void goback(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("courses.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();


    }



}
