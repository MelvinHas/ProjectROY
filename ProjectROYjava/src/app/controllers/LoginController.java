package app.controllers;

import app.models.Database;
import app.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

public class LoginController
{
    public TextField txtUsername;
    public TextField txtPassword;
    public Button btnLogin;
    public Button btnRegister;
    public TextField txtAgeRegister;
    public TextField txtEMailRegister;
    public TextField txtUsernameRegister;
    public TextField txtPasswordRegister;
    public Button btnExitLogin;
    public Button btnYesLogin;
    public Label lblAnswer;
    public Label LBLpowered;
    public Label LBLpowered2;


    Database db = new Database();

    ArrayList<User> users = new ArrayList<>();

    //AFSLUITKNOP
    public void exitLoginNow(ActionEvent actionEvent)
    {
        btnExitLogin.setTextFill(Color.RED);

        System.exit(0);
    }

    //INLOGGEN
    public void loginNow(ActionEvent actionEvent)
    {
        String usernameL = txtUsername.getText();
        String passwordL = txtPassword.getText();

        btnLogin.setTextFill(Color.RED);


        try
        {
            db.connect();
            ResultSet rs = db.get("SELECT * FROM Users WHERE Username='"+usernameL+"' AND Password ='"+passwordL+"';");
            while (rs.next())
            {
                User user = new User();
                user.setID(rs.getInt("ID"));
                user.setPassword(rs.getString("Password"));
                user.setUsername(rs.getString("Username"));
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        if(users.equals(null))
        {
            lblAnswer.setText("Conbinatie niet gevonden.");
        }
        else
        {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("../views/HomeView.fxml"));
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

        //Speel muziek
        InputStream music;
        try
        {
            System.out.println("Muziek speelt");
            music = new FileInputStream("../assets/music/Manuel - Gas Gas Gas.mp");
            AudioStream audios = new AudioStream (music);
            AudioPlayer.player.start(audios);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error");
        }

    }

    //REGISTREREN WEERGEVEN
    public void yesNow(ActionEvent actionEvent) {
        btnRegister.setVisible(true);
        txtAgeRegister.setVisible(true);
        txtEMailRegister.setVisible(true);
        txtUsernameRegister.setVisible(true);
        txtPasswordRegister.setVisible(true);
        btnYesLogin.setTextFill(Color.RED);
        LBLpowered.setVisible(false);
        LBLpowered2.setVisible(false);

    }


    //REGISTREREN
    public void registerNow(ActionEvent actionEvent)
    {

        btnRegister.setTextFill(Color.RED);
        String usernameR = txtUsernameRegister.getText();
        String passwordR = txtPasswordRegister.getText();
        String ageR = txtAgeRegister.getText();
        String emailR = txtEMailRegister.getText();


        try
        {
            Database db = new Database();
            db.connect();
            db.query( "INSERT INTO `Users`(`Username`, `EMail`, `Age`, `Password`) VALUES ('"+usernameR+"', '"+emailR+"', '"+ageR+"', '"+passwordR+"');");
            hash("Pils");
            db.disconnect();
            System.out.println("Geregistreerd!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }



    //WACHTWOORD HASHEN
    public String hash(String password)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-255");
            messageDigest.update(password.getBytes());
            return Base64.getUrlEncoder().encodeToString(messageDigest.digest());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

