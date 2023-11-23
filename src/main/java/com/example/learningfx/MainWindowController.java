package com.example.learningfx;

import javafx.beans.value.ObservableNumberValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.xml.sax.HandlerBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.EventListener;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindowController {
    @FXML
    private Button addEmployeeBtn;
    @FXML
    private Button btn;
    @FXML
    private VBox formBox;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField password;
    @FXML
    private TextField cin;
    @FXML
    private TextField address;
    @FXML
    private TextField phNumber;
    @FXML
    private DatePicker sDate;
    @FXML
    private DatePicker dob;
    @FXML
    private HBox mainWindowId;
    @FXML
    private HBox fnameParent;
    @FXML
    private HBox lNameBox;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private VBox mainContainer;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;


    public MainWindowController(){

        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                fName.setPrefWidth(dob.getPrefWidth());
                fName.textProperty().addListener((observable, oldValue, newValue) -> {
                    System.out.println("textfield changed from " + oldValue + " to " + newValue);
                });
            }

        };
        timer.schedule(task,1000l);
    }



    @FXML
    public void onClick(ActionEvent e){
        Button btn=new Button("om");
        btn.prefHeightProperty().bind(lNameBox.heightProperty());
        btn.setMinWidth(lNameBox.getWidth()/6);
        btn.setStyle("-fx-background-color:  yellow;" +
                "    -fx-border-color: grey grey grey transparent;" +
                "    -fx-border-width: 1px;" +
                "    -fx-background-radius: 0 10px 10px 0;" +
                "    -fx-border-radius: 0 10px 10px 0;");
        lNameBox.getChildren().addAll(btn);
        lName.setStyle(
                "    -fx-border-color: grey transparent grey grey;" +
                "    -fx-border-width: 1px;" +
                "    -fx-background-radius: 10px 0 0 10px;" +
                "    -fx-border-radius: 10px 0 0 10px;");


        sideBar.prefHeightProperty().bind(mainWindowId.heightProperty());
        sideBar.prefWidthProperty().bind(mainWindowId.widthProperty());
        mainContainer.prefHeightProperty().bind(mainWindowId.heightProperty());
        mainContainer.prefWidthProperty().bind(mainWindowId.widthProperty());
//        for(Node child : formBox.getChildren()) {
//            ((HBox)child).prefHeightProperty().add(formBox.heightProperty());
//            ((HBox)child).prefWidthProperty().add(formBox.widthProperty());
//        }

    }
//





    public void onAddEmployeeBtnClicked(){

        String sql = "INSERT INTO employees "
                + "(firstName,lastName,address,dateOfBirth,password,phNumber,startDate,cin) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = Connect2DB.connectDb();

        try {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, fName.getText());
                    prepare.setString(2, lName.getText());
                    prepare.setString(3, address.getText());
                    prepare.setString(4, String.valueOf(dob.getValue()));
                    prepare.setString(5, password.getText());
                    prepare.setString(6, phNumber.getText());
                    prepare.setString(7, String.valueOf(sDate.getValue()));
                    prepare.setString(8, cin.getText());
                    prepare.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
