package com.example.learningfx;

import javafx.beans.value.ObservableNumberValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.xml.sax.HandlerBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
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
    private HBox phNumber;
    @FXML
    private HBox mainWindowId;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private VBox mainContainer;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;


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
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO employees "
                + "(firstName,lastName,address,dateOfBirth,password,phNumber,startDate,cin) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = Connect2DB.connectDb();

        try {
            Alert alert;
            if (fName.getText().isEmpty()
                    || lName.getText().isEmpty()
                    || password.getText().isEmpty()
                    || cin.getSelectionModel().getSelectedItem() == null
                    || dob.getText().isEmpty()
                    || startDat.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT employee_id FROM employee WHERE employee_id = '"
                        + addEmployee_employeeID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + addEmployee_employeeID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addEmployee_phoneNum.getText());
                    prepare.setString(6, (String) addEmployee_position.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);
                    prepare.setString(8, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    String insertInfo = "INSERT INTO employee_info "
                            + "(employee_id,firstName,lastName,position,salary,date) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertInfo);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_position.getSelectionModel().getSelectedItem());
                    prepare.setString(5, "0.0");
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
