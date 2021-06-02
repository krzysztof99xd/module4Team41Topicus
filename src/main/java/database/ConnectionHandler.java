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

    //    public static void insertMethod(double amount, String IBAN_no, double final_balance, double closing_balance,
//                    String final_date, String closing_date, String currency, char debit_or_credit_mark) {
//
//        try {
//            //Connection connection = DriverManager.getConnection(url, dbName, password);
////            Connection connection = DriverManager.getConnection("jdbc:postgresql://bronto.ewi.utwente.nl/"+dbname + "?currentSchema=database_project", dbname, password);
//            /* Statement statement = connection.createStatement(); */
//
//            // st.executeUpdate("INSERT INTO Customers " +
//            //    "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
//
//            String query = "INSERT INTO database_project.balance " + "VALUES (" + "'" + IBAN_no + "'" + ", " + final_balance
//                    + ", " + closing_balance + ", " + "'" + final_date + "'" + ", " + "'" + closing_date + "'" + ", " + "'" + currency + "'" + ", " + "'"
//                    + debit_or_credit_mark + "'" + ", "  + amount + ")";
//
//            PreparedStatement st = connection.prepareStatement(query);
//            ResultSet resultset = st.executeQuery();
//            connection.close();
//           // return insertMethod;
//        } catch (SQLException sqle) {
//            System.err.println("Error connecting: " + sqle);
//        }
//       //return null;
//    }




    public static void main(String[] args) throws SQLException {
        EstablishConnection();
        ResultSet finish = ExecutingQueries();
        while (finish.next()) {
            System.out.println(finish.getString(1));
        }
    }
}

// helloooo
