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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class serverlogincontroller implements Initializable {

    private String servername;

    private static madeServer serveOfTheProgram;



    @FXML
    private Button backtofirst,enterbutton,createbutton;

    @FXML
    private TextField servernamefield,madeserverfield;

    @FXML
    private VBox vbox_main;

    @FXML
    private ScrollPane sp_main;



    private Scene scene;

    private Stage stage;

    private Parent root;

    public void backtofirstpage(ActionEvent event) throws IOException {

        Client.saveserver();

        Client.saveUser();

        Client.savechanel();

        Client.savereq();

        root= FXMLLoader.load(Main.class.getResource("first.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();


    }

    public boolean createServer(){

        boolean check=false;

        if(servernamefield!=null) {

            servername = servernamefield.getText();

            madeServer ms=new madeServer(servername,Client.userOfProgram.getUsername());

            Client.serverList.add(ms);

            serveOfTheProgram=ms;

            check=true;

        }

        return check;

    }

    public static madeServer getServer(){
        return serveOfTheProgram;
    }

    public void gotopvpublic(ActionEvent event) throws IOException {

        if(createServer()){

            root= FXMLLoader.load(Main.class.getResource("pv and public chat.fxml"));

            stage=(Stage)((Node)event.getSource()).getScene().getWindow();

            scene=new Scene(root);

            stage.setScene(scene);

            stage.show();


        }

    }

    public void serserver(madeServer ms){
        serveOfTheProgram=ms;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(madeServer ms:Client.serverList){

            HBox hBox=new HBox();

            hBox.setAlignment(Pos.CENTER);

            hBox.setPadding(new Insets(5,10,5,10));


            Text text=new Text(ms.getServerName());
            TextFlow textflow=new TextFlow(text);


            textflow.setPadding(new Insets(5,10,5,10));
            hBox.getChildren().add(textflow);

            vbox_main.getChildren().add(hBox);

        }

    }
}
