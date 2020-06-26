package app.controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.models.Database;
import app.models.Film;
import app.Main;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HomeController
{
    public Button btnHome;
    public Button btnMyList;
    public Button btnSeries;
    public Button btnMovies;
    public Button btnExit;
    public ScrollPane scrlPaneDown;
    public ScrollPane scrlPaneUp;
    public Pane paneDown;
    public Pane paneUp;
    public Integer interger;
    public Event event;



    public ArrayList<Film> films = new ArrayList<>();


    //AFSLUITKNOP
    public void exitHomeNow()
    {
        btnExit.setTextFill(Color.RED);

        System.exit(0);
    }

    //GET ALL FILMS
    public void allFilmsNow()
    {
        try
        {
            Database db = new Database();
            db.connect();
            ResultSet result = db.get("SELECT * FROM `Films`;");
            while (result.next()) {
                Film film = new Film();
                film.setID(result.getInt("ID"));
                film.setTitle(result.getString("Titel"));
                film.setGenre(result.getString("Genre"));
                film.setYear(result.getString("Jaar"));
                film.setTime(result.getString("Tijd"));
                film.setIntro(result.getString("Samenvatting"));
                film.setPoster(result.getString("Poster"));

                films.add(film);
                showAllFilms();

                ResultSet resultD = db.get("SELECT * FROM `Films` ORDER BY `Jaar` DESC;");
                Film filmD = new Film();
                filmD.setID(resultD.getInt("ID"));
                filmD.setTitle(resultD.getString("Titel"));
                filmD.setGenre(resultD.getString("Genre"));
                filmD.setYear(resultD.getString("Jaar"));
                filmD.setTime(resultD.getString("Tijd"));
                filmD.setIntro(resultD.getString("Samenvatting"));
                filmD.setPoster(resultD.getString("Poster"));

                films.add(filmD);
                showAllFilmsD();
            }

            db.disconnect();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //GET ALL FILMS GENRE WAR
    public void warNow()
    {
        try
        {
            Database db = new Database();
            db.connect();
            ResultSet result = db.get("SELECT * FROM Films WHERE Genre='War';");
            while (result.next()) {
                Film film = new Film();
                film.setID(result.getInt("ID"));
                film.setTitle(result.getString("Titel"));
                film.setGenre(result.getString("Genre"));
                film.setYear(result.getString("Jaar"));
                film.setTime(result.getString("Tijd"));
                film.setIntro(result.getString("Samenvatting"));
                film.setPoster(result.getString("Poster"));

                films.add(film);
                showAllFilms();
            }


            ResultSet resultD = db.get("SELECT * FROM Films WHERE Genre='War' ORDER BY Jaar DESC;");
            while(resultD.next())
            {
                Film filmD = new Film();
                filmD.setID(resultD.getInt("ID"));
                filmD.setTitle(resultD.getString("Titel"));
                filmD.setGenre(resultD.getString("Genre"));
                filmD.setYear(resultD.getString("Jaar"));
                filmD.setTime(resultD.getString("Tijd"));
                filmD.setIntro(resultD.getString("Samenvatting"));
                filmD.setPoster(resultD.getString("Poster"));

                films.add(filmD);
                showAllFilmsD();
            }

            db.disconnect();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //GET ALL FILMS GENRE FINANCE
    public void financeNow()
    {
        try
        {
            Database db = new Database();
            db.connect();
            ResultSet result = db.get("SELECT * FROM `Films` WHERE `Genre` = `Finance`;");
            while (result.next()) {
                Film film = new Film();
                film.setID(result.getInt("ID"));
                film.setTitle(result.getString("Titel"));
                film.setGenre(result.getString("Genre"));
                film.setYear(result.getString("Jaar"));
                film.setTime(result.getString("Tijd"));
                film.setIntro(result.getString("Samenvatting"));
                film.setPoster(result.getString("Poster"));

                films.add(film);
                showAllFilms();

                ResultSet resultD = db.get("SELECT * FROM `Films` WHERE `Genre` = `Finance` ORDER BY `Jaar` DESC;");
                Film filmD = new Film();
                filmD.setID(resultD.getInt("ID"));
                filmD.setTitle(resultD.getString("Titel"));
                filmD.setGenre(resultD.getString("Genre"));
                filmD.setYear(resultD.getString("Jaar"));
                filmD.setTime(resultD.getString("Tijd"));
                filmD.setIntro(resultD.getString("Samenvatting"));
                filmD.setPoster(resultD.getString("Poster"));

                films.add(filmD);
                showAllFilmsD();
            }

            db.disconnect();
            showAllFilms();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //SHOW ALL FILMS UPPER PANE
    public void showAllFilms()
    {
        for (int i=0; i<films.size(); i++)
        {
            String imageURL = getClass().getResource("../assets/image/" + films.get(i).getPoster()).toString();
            Image image = new Image(imageURL);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setY(50);
            imageView.setX(i*350);

            paneUp.getChildren().add(imageView);
        }
    }

    public void showAllFilmsD()
    {
        for (int i=0; i<films.size(); i++)
        {
            String imageURL = getClass().getResource("../assets/image/" + films.get(i).getPoster()).toString();
            Image image = new Image(imageURL);
            ImageView imageView = new ImageView();
           imageView.setImage(image);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setY(50);
            imageView.setX(i*350);

            imageView.setCursor(Cursor.HAND);
            imageView.setId("" + films.get(i).getID());
            imageView.setOnMouseClicked(event -> goToMovie(event));

            paneDown.getChildren().add(imageView);
        }
    }

    public void goToMovie(MouseEvent event)
    {
        String id =((ImageView)event.getSource()).getId();
        int filmID = Integer.parseInt(id);

        for(int i=0; i<films.size(); i++)
        {
            if (films.get(i).getID() == filmID)
            {
                Main.user.setCurrentFilm(films.get(i));
            }
        }

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("../views/FilmView.fxml"));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Film");
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
}


