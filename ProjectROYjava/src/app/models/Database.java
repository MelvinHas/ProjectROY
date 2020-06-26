package app.models;

import java.sql.*;

/**
 * A simple Java class used to connect to a database and execute queries.
 * @author Rick Schepers
 * @version 1.0
 * @since 2019-03-31
 */
public class Database {
    private String host = "185.104.29.78"; // The IP-address of the database host.
    private String database = "mhasselaar_brrr"; // The name of the database.
    private String user = "mhasselaar_brrr"; // The name of the database user.
    private String pass = "1234"; // The password of the database user.

    private Connection con;

    /**
     * Open a connection to the database.
     */
    public void connect()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    String.format("jdbc:mysql://%s:3306/%s?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", host, database),
                    user,
                    pass
            );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * This method is used to execute queries that retrieve data from the database.
     * @param sql The SELECT query you want to execute on the database.
     * @return Returns the result as a ResultSet or null if error.
     */
    public ResultSet get(String sql)
    {
        try
        {
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * This method is used to execute queries that manipulate data in the database.
     * @param sql The INSERT, UPDATE, or DELETE query you want to execute on the database.
     * @return Returns an integer that indicates the number of rows affected, or 0 if error.
     */
    public int query(String sql)
    {
        try
        {
            Statement stmt = con.createStatement();
            return stmt.executeUpdate(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return 0;
    }

    /**
     * Close the connection to the database.
     */
    public void disconnect()
    {
        try
        {
            con.close();
            con = null;
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
