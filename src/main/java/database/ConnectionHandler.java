package database;

import lendaryModel.Balance;

import java.sql.*;

public class ConnectionHandler {
    private final String host;
    private final String dbName;
    private final String url ;
    private final String password;
    private Connection connection;


    public ConnectionHandler () throws SQLException {
       this.dbName = "dab_di20212b_53";
       this.password = "gHr1FXvOd7ERdqHy";
       this.host = "bronto.ewi.utwente.nl";
        this.url = "jdbc:postgresql://" + host + ":5432/" + dbName + "?currentSchema=database_project";
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        }
        connection = DriverManager.getConnection(url, dbName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }
    //    public void EstablishConnection() {
//        try {
//            Class.forName("org.postgresql.Driver");
//
//        } catch (ClassNotFoundException cnfe) {
//            System.err.println("Error loading driver: " + cnfe);
//        }



    public ResultSet ExecutingQueries() {

        try {
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

        public void insertMethod(double amount, String IBAN_no, double final_balance, double closing_balance,
                    String final_date, String closing_date, String currency, char debit_or_credit_mark) {

        try {
            String query = "INSERT INTO database_project.balance " + "VALUES (" + "'" + IBAN_no + "'" + ", " + final_balance
                    + ", " + closing_balance + ", " + "'" + final_date + "'" + ", " + "'" + closing_date + "'" + ", " + "'" + currency + "'" + ", " + "'"
                    + debit_or_credit_mark + "'" + ", "  + amount + ")";

            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultset = st.executeQuery();
            connection.close();
           // return insertMethod;
        } catch (SQLException sqle) {
            System.err.println("Error connecting: " + sqle);
        }
       //return null;
    }
    public void insertBalance(Balance balance){
        try {
            String query = "INSERT INTO money (money_id, currency, debit_credit)\n" +
                    "VALUES(1, 'EUR', 'D');\n" +
                    "INSERT INTO balance (account_identification,  sequence_number) \n" +
                    "VALUES('"+balance.getAccountID()+"','" +balance.getSequence_number() +"')";

//            INSERT INTO balance (account_identification, transaction_number, sequence_number, first_amount, statement_date, first_amount, statement_date, final_date, booked_funds)
//            VALUES();

//            "SELECT DISTINCT p.name " + "FROM person p " + "INNER JOIN writes w ON w.pid = p.pid " +
//            "INNER JOIN movie m ON m.mid = w.mid " + "INNER JOIN acts a ON a.mid = m.mid " + "INNER JOIN person r ON r.pid = a.pid " + "WHERE r.name = ?";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultset = st.executeQuery();
            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Error connecting: " + sqle);
        }

    }


    public static void main(String[] args) throws SQLException {
        ConnectionHandler connectionHandler = new ConnectionHandler();
//        connectionHandler.insertMethod(2, "djhfhj", 10, 23, "2019-11-02", "2019-11-04", "ssddd", 'D');
        ResultSet finish = connectionHandler.ExecutingQueries();
        while (finish.next()) {
            System.out.println(finish.getString(1));
        }
    }
}
