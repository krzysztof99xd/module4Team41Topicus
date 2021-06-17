package database;

import lendaryModel.Analytics;
import lendaryModel.Balance;
import lendaryModel.Money;
import lendaryModel.Transaction;

import java.sql.*;
import java.util.*;

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
        try {
            return DriverManager.getConnection(url, dbName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getHost() {
        return host;
    }

    public void insertBalance(Balance balance){
        try {
            String query = "INSERT INTO money (money_id, amount, currency, debit_credit)\n" +
                    "VALUES  ('"+ balance.getAccountID() + "f_a"+"','"+balance.getFirst_balance().getAmount() + "','"+balance.getFirst_balance().getCurrency()+"','"+balance.getFirst_balance().getDebit_or_credit()+"'),\n" +
                    "('"+ balance.getAccountID() + "c_a"+"','"+balance.getFinal_balance().getAmount() + "','"+balance.getFinal_balance().getCurrency()+"','"+balance.getFinal_balance().getDebit_or_credit()+"'),\n" +
                    "('"+ balance.getAccountID() + "b_f"+"','"+balance.getBooked_balance().getAmount() + "','"+balance.getBooked_balance().getCurrency()+"','"+balance.getBooked_balance().getDebit_or_credit()+"');\n" +
                    "INSERT INTO balance (account_id,  transaction_number, sequence_number, statement_date, final_date, first_amount, final_amount, booked_funds) \n" +
                    "VALUES('"+balance.getAccountID()+"','" +balance.getTransaction_number() + "','" +balance.getSequence_number() + "','" + balance.getStatement_date() + "','" + balance.getClosing_date() + "','" + balance.getAccountID() + "f_a" + "','" + balance.getAccountID() + "c_a"  + "','"  + balance.getAccountID() + "b_f');";
            System.out.print("\n"+query);
            connection = getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            System.out.println(balance.getTransactions().size());
            int i = 0;
            for(Transaction t : balance.getTransactions()){
                i++;
                insertTransaction(t, balance.getAccountID(), i);
            }
            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Error connecting: " + sqle);
        }

    }

    public void insertTransaction(Transaction t, String account_id, int i){
        try {
            String str = "t_a" + i;
            String query = "INSERT INTO money (money_id, amount, currency, debit_credit) " +
                    "VALUES ('"+ account_id + str +"','"+t.getAmount().getAmount() + "','"+t.getAmount().getCurrency()+"','"+t.getAmount().getDebit_or_credit()+"');\n" +
                    "INSERT INTO transaction (account_id, value_date, entry_date, customer_reference, transaction_amount)\n" +
                    "VALUES ('"+account_id+"','"+t.getValueDate()+"','"+t.getEntry_date()+"','"+t.getCustomer_reference()+"','"+account_id + str +"');";


            System.out.print("\n"+query);
            Statement st = connection.createStatement();
            st.executeUpdate(query);

        } catch (SQLException sqle) {
            System.err.println("Error connecting: " + sqle);
        }
    }
    public List<Balance> getBalances(){
        List<Balance> result = new ArrayList<>();
        try{
            String query = "SELECT account_id , statement_date, final_date,  m1.amount as sb ,m1.currency as sc,  m2.amount as fb, m1.currency as fc\n" +
                    "FROM balance b, money m1, money m2\n" +
                    "WHERE b.first_amount = m1.money_id\n" +
                    "AND b.final_amount = m2.money_id\n"+
                    "ORDER BY final_date DESC";

            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Balance balance = new Balance();
                balance.setAccountID(rs.getString("account_id"));
                balance.setStatement_date(rs.getDate("statement_date"));
                balance.setClosing_date(rs.getDate("final_date"));
                balance.setFirst_balance(new Money(rs.getString("sc"), rs.getFloat("sb"), 'c'));
                balance.setFinal_balance(new Money(rs.getString("fc"), rs.getFloat("fb"), 'c'));
                result.add(balance);
            }
            connection.close();
        }catch (SQLException sqle){
            System.err.println("Error connecting: " + sqle);
        }
        return  result;
    }


    
    public Analytics analysis(String accountID){
    	Analytics t = new Analytics(accountID);
        try {
            String query = "SELECT b.statement_date	AS sd, t.value_date	AS vd, b1.amount AS firstA, b2.amount AS finalA, t1.amount AS t1a, t1.debit_credit AS t1dc\n" +
                    "FROM balance b, money b1, money b2, money t1, transaction t\n" +
                    "WHERE b.first_amount = b1.money_id\n" +
                    "AND b.final_amount = b2.money_id\n" +
                    "AND b.account_id = t.account_id\n" +
                    "AND t.transaction_amount = t1.money_id\n" +
                    "AND b.account_id = '"+ accountID +"';";
            System.out.println(query);
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
        
            int count = 0;
            while(rs.next()){      
            	if(count == 0) {
            		 t.setfirst(rs.getFloat("firstA"),rs.getDate("sd"));
                     count++;
            	}
                t.AddAmount(rs.getFloat("t1a"),rs.getString("t1dc"),rs.getDate("vd"));
            }
            t.setfinal();
            connection.close();
        }catch (SQLException sqle){
            System.err.println("Error connecting: " + sqle);
        }
        return t;
    }
    
    
    
    public static void main(String[] args) throws SQLException {

    }
}