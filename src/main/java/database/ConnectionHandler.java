package database;

import lendaryModel.Balance;
import lendaryModel.Transaction;

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

    //We still didnt finish inserting into balnce, please come back to me
    public void insertBalance(Balance balance){
        try {
            String query = "INSERT INTO money (money_id, amount, currency, debit_credit)\n" +
                    "VALUES  ('"+ balance.getAccountID() + "f_a"+"','"+balance.getFirst_balance().getAmount() + "','"+balance.getFirst_balance().getCurrency()+"','"+balance.getFirst_balance().getDebit_or_credit()+"'),\n" +
                    "('"+ balance.getAccountID() + "c_a"+"','"+balance.getFinal_balance().getAmount() + "','"+balance.getFinal_balance().getCurrency()+"','"+balance.getFinal_balance().getDebit_or_credit()+"'),\n" +
                    "('"+ balance.getAccountID() + "b_f"+"','"+balance.getBooked_balance().getAmount() + "','"+balance.getBooked_balance().getCurrency()+"','"+balance.getBooked_balance().getDebit_or_credit()+"');" +
                    "INSERT INTO balance (account_id,  transaction_number, sequence_number, statement_date, final_date, first_amount, final_amount, booked_funds) \n" +
                    "VALUES('"+balance.getAccountID()+"','" +balance.getTransaction_number() + "','" +balance.getSequence_number() + "','" + balance.getStatement_date() + "','" + balance.getClosing_date() + "','" + balance.getAccountID() + "f_a" + "','" + balance.getAccountID() + "c_a"  + "','"  + balance.getAccountID() + "b_f');";

            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultset = st.executeQuery();
            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Error connecting: " + sqle);
        }
        int i = 0;
        for(Transaction t : balance.getTransactions()){
            i++;
            insertTransaction(t, balance.getAccountID(), i);
        }

    }
    public void insertTransaction(Transaction t, String account_id, int i){
        try {
            String str = "t_a" + i;
            String query = "INSERT INTO money (money_id, amount, currency, debit_credit) " +
                    "VALUES ('"+ account_id + str +"','"+t.getAmount().getAmount() + "','"+t.getAmount().getCurrency()+"','"+t.getAmount().getDebit_or_credit()+"');" +
                    "INSERT INTO transaction (account_id, value_date, entry_date, customer_reference, transaction_amount)\n" +
                    "VALUES ('"+account_id+"','"+t.getValueDate()+"','"+t.getEntry_date()+"','"+t.getCustomer_reference()+"','"+account_id + str +"');";

            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultset = st.executeQuery();
            connection.close();
        }catch (SQLException sqle) {
                System.err.println("Error connecting: " + sqle);
        }
    }


    public static void main(String[] args) throws SQLException {
        ConnectionHandler connectionHandler = new ConnectionHandler();
        ResultSet finish = connectionHandler.ExecutingQueries();
        while (finish.next()) {
            System.out.println(finish.getString(1));
        }
    }
}
