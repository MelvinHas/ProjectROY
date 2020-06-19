package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.models.Database;

public class FilmController
{

    public Button btnExitFilm;
    public Label lblTitel;
    public ImageView imgFilm;
    public Label lblGenre;
    public Label lblJaarFilm;
    public Label lblSamenvatting;
    public Label lblTIJD;
    public Button btnR5;
    public Button btnR4;
    public Button btnR3;
    public Button btnR2;
    public Button btnR1;


    Database db = new Database();

    //TERUG NAAR HOME DIKKE ROY
    public void exitFilmNow(ActionEvent actionEvent)
    {
        btnExitFilm.setTextFill(Color.RED);

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("../app/views/HomeView.fxml"));
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("Home");
            primaryStage.setScene(new Scene(root));
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreen(true);
            primaryStage.show();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    //RATE 1
    public void rate1()
    {

        btnR1.setTextFill(Color.RED);
        try
        {
            db.connect();
            db.query( "INSERT INTO `Ratings`(`Rating`, `FilmID`, `UserID`) VALUE ('1', '+userID+', '+filmID+' );");
            db.disconnect();
            System.out.println("Rating Done!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //RATE 2
    public void rate2()
    {

        btnR1.setTextFill(Color.RED);
        btnR2.setTextFill(Color.RED);
        try
        {
            db.connect();
            db.query( "INSERT INTO `Ratings`(`Rating`) VALUE ('2');");
            db.disconnect();
            System.out.println("Rating Done!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //RATE 3
    public void rate3()
    {

        btnR1.setTextFill(Color.RED);
        btnR2.setTextFill(Color.RED);
        btnR3.setTextFill(Color.RED);
        try
        {
            db.connect();
            db.query( "INSERT INTO `Ratings`(`Rating`) VALUE ('3');");
            db.disconnect();
            System.out.println("Rating Done!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //RATE 4
    public void rate4()
    {

        btnR1.setTextFill(Color.RED);
        btnR2.setTextFill(Color.RED);
        btnR3.setTextFill(Color.RED);
        btnR4.setTextFill(Color.RED);
        try
        {
            db.connect();
            db.query( "INSERT INTO `Ratings`(`Rating`) VALUE ('4');");
            db.disconnect();
            System.out.println("Rating Done!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //RATE 5
    public void rate5()
    {

        btnR1.setTextFill(Color.RED);
        btnR2.setTextFill(Color.RED);
        btnR3.setTextFill(Color.RED);
        btnR4.setTextFill(Color.RED);
        btnR5.setTextFill(Color.RED);
        try
        {
            db.connect();
            db.query( "INSERT INTO `Ratings`(`Rating`, `FilmID`, `UserID`) VALUE ('5', '+userID+', '+filmID+' );");
            db.disconnect();
            System.out.println("Rating Done!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //
}
