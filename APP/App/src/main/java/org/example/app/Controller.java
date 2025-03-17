package org.example.app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("app-UI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }


    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;

    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            loginMessageLabel.setText("Please enter your username and password");
        }
        else {
            validateLogin();
        }
    }


    public void validateLogin(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT COUNT(1) FROM users WHERE  username = '" + usernameTextField.getText() + "' AND password = '" + passwordTextField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Welcome " + usernameTextField.getText());
                }
                else{
                    loginMessageLabel.setText("Invalid username or password");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openImage(ActionEvent e) {
        
    }
    public void saveImage(ActionEvent e) {

    }
    
    public void exitApp(ActionEvent e) {

    }

    public void applyGrayscale(ActionEvent e) {
    }

    public void applyBlur(ActionEvent actionEvent) {
    }

    public void applyNegative(ActionEvent actionEvent) {
    }

    public void undo(ActionEvent actionEvent) {
    }

    public void redo(ActionEvent actionEvent) {
    }

    public void resetImage(ActionEvent actionEvent) {
    }

    public void adjustBrightness(MouseEvent mouseEvent) {
    }

    public void adjustContrast(MouseEvent mouseEvent) {
    }

    public void applyFilter(ActionEvent actionEvent) {
    }
}