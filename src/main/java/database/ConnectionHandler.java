package database;

import java.sql.*;

public class ConnectionHandler {
    static String host = "bronto.ewi.utwente.nl";
    static String dbName = "dab_di20212b_53";
    static String url = "jdbc:postgresql://" + host + ":5432/" + dbName + "?currentSchema=database_project";
    static String password = "gHr1FXvOd7ERdqHy";



    public static void EstablishConnection() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        }
    }

    public static ResultSet ExecutingQueries() {

        try {
            Connection connection = DriverManager.getConnection(url, dbName, password);
            /* Statement statement = connection.createStatement(); */
            String query = "SELECT * " + "FROM database_project.balance; ";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultset = st.executeQuery();
            connection.close();
            return resultset;
        } catch (SQLException sqle) {
            System.err.println("Error connecting: " + sqle);
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
        EstablishConnection();
        ResultSet finish = ExecutingQueries();
        while (finish.next()) {
            System.out.println(finish.getString(1));
        }
    }
}
