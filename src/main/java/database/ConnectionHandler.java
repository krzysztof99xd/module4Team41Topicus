package database;

import lendaryModel.*;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class ConnectionHandler {
    private final String host;
    private final String dbName;
    private final String url ;
    private final String password;
    private Connection connection;

    /**
     * Creates a connection to the database
     * @throws SQLException if there is an error in the database
     */
    public ConnectionHandler () throws SQLException {
        this.dbName = "dab_di20212b_53";
//        this.dbName = "dab_di20212b_293";
        this.password = "gHr1FXvOd7ERdqHy";
//        this.password = "avqsSVSDgiY3GMUl";
        this.host = "bronto.ewi.utwente.nl";
        this.url = "jdbc:postgresql://" + host + ":5432/" + dbName + "?currentSchema=database_project";
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        }
        connection = DriverManager.getConnection(url, dbName, password);
    }
    /**
     * gets the connection
     * @return the url, dbName and password || null
     */
    public Connection getConnection() throws SQLException {
         return DriverManager.getConnection(url, dbName, password);
    }

    /**
     * gets the host
     * @return the session host
     */
    public String getHost() {
        return host;
    }

    /**
     * inserts values into the a balance table
     * @param balance balance object
     */
    public Boolean insertBalance(Balance balance) throws SQLException{
    	
    	if(balanceExists(balance.getAccountID())) {
    		return false;
    	}
    	String query = "INSERT INTO money (money_id, amount, currency, debit_credit)\n" +
                "VALUES  ('"+ balance.getAccountID() + "f_a"+"','"+balance.getFirst_balance().getAmount() + "','"+balance.getFirst_balance().getCurrency()+"','"+balance.getFirst_balance().getDebit_or_credit()+"'),\n" +
                "('"+ balance.getAccountID() + "c_a"+"','"+balance.getFinal_balance().getAmount() + "','"+balance.getFinal_balance().getCurrency()+"','"+balance.getFinal_balance().getDebit_or_credit()+"'),\n" +   
                "('"+ balance.getAccountID() + "b_f"+"','"+balance.getBooked_balance().getAmount() + "','"+balance.getBooked_balance().getCurrency()+"','"+balance.getBooked_balance().getDebit_or_credit()+"');\n" +
                "INSERT INTO balance (account_id,  transaction_number, sequence_number, statement_date, final_date, first_amount, final_amount, booked_funds, upload_time) \n" +
                "VALUES('"+balance.getAccountID()+"','" +balance.getTransaction_number() + "','" +balance.getSequence_number() + "','" + balance.getStatement_date() + "','" + balance.getClosing_date() + "','" + balance.getAccountID() + "f_a" + "','" + balance.getAccountID() + "c_a"  + "','"  + balance.getAccountID() + "b_f','" + LocalDateTime.now() + "');"; //(new LocalDateTime()).now()
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
      

        return true;
    }
    /**
     * inserts values into the transaction table
     * @param t Transaction object
     * @param account_id
     * @param i addition number to the id
     */
    public void insertTransaction(Transaction t, String account_id, int i) throws SQLException{
       
            String str = "t_a" + i;
            String query = "INSERT INTO money (money_id, amount, currency, debit_credit) " +
                    "VALUES ('"+ account_id + str +"','"+t.getAmount().getAmount() + "','"+t.getAmount().getCurrency()+"','"+t.getAmount().getDebit_or_credit()+"');\n" +
                    "INSERT INTO transaction (account_id, value_date, entry_date, customer_reference, transaction_amount)\n" +
                    "VALUES ('"+account_id+"','"+t.getValueDate()+"','"+t.getEntry_date()+"','"+t.getCustomer_reference()+"','"+account_id + str +"');";


            System.out.print("\n"+query);
            Statement st = connection.createStatement();
            st.executeUpdate(query);

        
    }

    /**
     * gets all the balances
     * @return list of the result of the query
     */
    public List<Balance> getBalances() throws SQLException{
        List<Balance> result = new ArrayList<>();
        String query = "SELECT account_id , statement_date, final_date,  m1.amount as sb ,m1.currency as sc,  m2.amount as fb, m1.currency as fc\n" +
                "FROM balance b, money m1, money m2\n" +
                "WHERE b.first_amount = m1.money_id\n" +
                "AND b.final_amount = m2.money_id\n"+
                "ORDER BY upload_time DESC";

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
        return  result;
    }

    /**
     * Gets all the information that will be used in the analytics class
     * @param accountID account ID
     * @return the information needed for making the analytics
     */
    public Analytics analysis(String accountID) throws SQLException{
    	Analytics t = new Analytics(accountID);
        
    	String query = "SELECT b.statement_date	AS sd, t.value_date	AS vd, b1.amount AS firstA, b2.amount AS finalA, t1.amount AS t1a, t1.debit_credit AS t1dc , b1.currency AS cur \n" +
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
        		 t.setfirst(rs.getFloat("firstA"),rs.getDate("sd"),rs.getString("cur"));
                 count++;
        	}
            t.AddAmount(rs.getFloat("t1a"),rs.getString("t1dc"),rs.getDate("vd"));
        }
        t.setfinal();
        connection.close();
        
        return t;
    }

    /**
     * Removes the balance table from the database
     * @param remove account id
     * @throws SQLException
     */
    public void removeBalance(String remove) throws SQLException {
		
    	String query = "DELETE FROM balance \n"
        		+ "    where account_id LIKE '"+remove+"%';";
        

        System.out.print("\n"+query);
        connection = getConnection();
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        
        query = "DELETE FROM transaction \n"
        		+ "    where  transaction_amount LIKE '"+remove+"%';";
        

        System.out.print("\n"+query);
        connection = getConnection();
        st = connection.createStatement();
        st.executeUpdate(query);
        
        query = "DELETE FROM money \n"
        		+ "    where money_id LIKE '"+remove+"%'";
       

        System.out.print("\n"+query);
        connection = getConnection();
        st = connection.createStatement();
        st.executeUpdate(query);
        connection.close();

        
	}

    /**
     * checks if the balance already exists in the database
     * @param check account id
     * @return true when the account id is already in the database, false otherwise
     * @throws SQLException
     */
    public boolean balanceExists(String check) throws SQLException{
        boolean accountIDexists = false;
        String query = "SELECT COUNT(*) as number_of_balances FROM database_project.balance WHERE account_id = '" +check+ "';";
        connection = getConnection();
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while(rs.next()){ 
        	if (rs.getInt("number_of_balances")>=1){
                accountIDexists = true;
            }
        }
        
        connection.close();
        System.out.print("its "+accountIDexists);
        
        return  accountIDexists;
    }

    /**
     * checks the credentials of the user that wants to login
     * @param email email
     * @param password password
     * @return if the credentials are correct
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws NoSuchAlgorithmException
     */
    public User checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException, NoSuchAlgorithmException {
        connection = getConnection();
        String sql = "SELECT * FROM users WHERE email = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, getPasswordHash(password));

        ResultSet result = statement.executeQuery();

        User user = null;

        if (result.next()) {
            user = new User();
            user.setEmail(email);
            user.setPassword(password);
        }

        connection.close();

        return user;
    }
    /**
     * hashes the users password
     * @param password password
     * @return the hashed password
     * @throws NoSuchAlgorithmException
     */
    public static String getPasswordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("SHA-256");
        return Hex.encodeHexString((md.digest(password.getBytes())));
    }

    /**
     * gets the password to the database
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * gets the url
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * gets the database name
     * @return dbName
     */
    public String getDbName() {
        return dbName;
    }
}