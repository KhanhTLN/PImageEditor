package org.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;

    public void handleLogin(ActionEvent event) {
            validateLogin(event);
    }

    public void validateLogin(ActionEvent event) {
        // Ensure fields are not empty
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            loginMessageLabel.setText("Please enter username and password!");
            loginMessageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB == null) {
            loginMessageLabel.setText("Database connection failed!");
            loginMessageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        String verifyLogin = "SELECT COUNT(1) FROM users WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordTextField.getText());

            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next() && queryResult.getInt(1) == 1) {
                // Get current stage and switch scenes properly
                SceneController sceneController = new SceneController();
                sceneController.switchToScene2(event);
            } else {
                loginMessageLabel.setText("Invalid username or password!");
                loginMessageLabel.setStyle("-fx-text-fill: red;");
            }

            // Close resources to prevent memory leaks
            queryResult.close();
            preparedStatement.close();
            connectDB.close();
        } catch (Exception e) {
            e.printStackTrace();
            loginMessageLabel.setText("An error occurred! Please try again.");
            loginMessageLabel.setStyle("-fx-text-fill: red;");
        }
    }
}