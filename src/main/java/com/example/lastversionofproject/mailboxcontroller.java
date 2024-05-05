package com.example.lastversionofproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ResourceBundle;

public class mailboxcontroller implements Initializable {


    private Scene scene;

    private Stage stage;

    private Parent root;


    @FXML
    private Button backbutton;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private VBox vbox_main;

    public void goback(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("pvChat.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(messageForPrivateChat mfpc:Client.pvMessages){

            if(mfpc.getReciever().equals(Client.userOfProgram.getUsername())){

                HBox hBox=new HBox();

                hBox.setAlignment(Pos.CENTER);

                hBox.setPadding(new Insets(5,10,5,10));


                Text text=new Text(mfpc.getMessage());
                TextFlow textflow=new TextFlow(text);


                textflow.setPadding(new Insets(5,10,5,10));
                hBox.getChildren().add(textflow);

                vbox_main.getChildren().add(hBox);


            }

        }

    }
}
