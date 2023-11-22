package com.example.learningfx;

import javafx.beans.value.ObservableNumberValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.xml.sax.HandlerBase;

import java.util.EventListener;

public class MainWindowController {
    @FXML
    private Button addEmployeeBtn;
    @FXML
    private Button btn;
    @FXML
    private VBox formBox;
    @FXML
    private HBox fName;
    @FXML
    private HBox lName;
    @FXML
    private HBox password;
    @FXML
    private HBox cin;
    @FXML
    private HBox dob;
    @FXML
    private HBox address;
    @FXML
    private HBox phNumber;
    @FXML
    private HBox mainWindowId;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private VBox mainContainer;


    @FXML
    public void onClick(ActionEvent e){
        sideBar.prefHeightProperty().bind(mainWindowId.heightProperty());
        sideBar.prefWidthProperty().bind(mainWindowId.widthProperty());
        mainContainer.prefHeightProperty().bind(mainWindowId.heightProperty());
        mainContainer.prefWidthProperty().bind(mainWindowId.widthProperty());
//        for(Node child : formBox.getChildren()) {
//            ((HBox)child).prefHeightProperty().add(formBox.heightProperty());
//            ((HBox)child).prefWidthProperty().add(formBox.widthProperty());
//        }
        System.out.println("omar");
    }
    public void onAddEmployeeBtnClicked(){

    }
}
