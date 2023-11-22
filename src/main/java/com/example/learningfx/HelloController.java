package com.example.learningfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {


    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private Label welcomeText;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;


    @FXML
    protected void onHelloButtonClick() {
        selectEmployees();
//        System.out.println(Connect2DB.connectDb()==null);
//        System.out.println(Connect2DB.connectDb()==NullPointerException);
        welcomeText.setText("Welcome to JavaFX Application!");

    }


    @FXML
    public void showDate(ActionEvent e){
        System.out.println(firstName.getText());
        System.out.println(lastName.getText());
        System.out.println(address.getText());
        firstName.setVisible(!firstName.isVisible());
    }
    public void selectEmployees() {
        connect = Connect2DB.connectDb();
        String sql = "SELECT * FROM employees";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                System.out.print(result.getString("firstName")+"  ");
                System.out.print(result.getString("lastName"));
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}