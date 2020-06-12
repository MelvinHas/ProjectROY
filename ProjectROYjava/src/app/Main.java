package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import app.models.User;

public class Main extends Application {

    public static User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/LoginView.fxml"));
        primaryStage.setTitle("Netflix");
        primaryStage.setScene(new Scene(root, 346, 422));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
