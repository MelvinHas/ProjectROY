package app.models;

public class User
{
    private int ID;

    public Film currentFilm;
    private String username;
    private String password;


    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }




    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Film getCurrentFilm() {
        return currentFilm;
    }

    public void setCurrentFilm(Film currentFilm) {
        this.currentFilm = currentFilm;
    }
}
