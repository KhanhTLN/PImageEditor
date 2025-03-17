package org.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 650, 400);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("app-UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("PIamgeEditor");
        stage.setScene(scene);
        stage.show();

//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
//            Scene scene = new Scene(root, 650, 400);
//            stage.setScene(scene);
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        launch();
    }
}