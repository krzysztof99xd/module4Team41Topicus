package lendaryModel;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Balance {
	
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
    private String accountID;
    private String transaction_number;
	private String sequence_number;
    private Money first_balance;
    private Money final_balance;
    private Date statement_date;
    private Date closing_date;
    private Money booked_balance;
    

    public Balance(){

	}

	/**
	 * @param accountID
	 * @param transaction_number
	 * @param sequence_number
	 * @param final_balance
	 * @param first_balance
	 * @param statement_date
	 * @param closing_date
	 * @param booked_balance
	 * Constructor
	 */
    public Balance(String accountID, String transaction_number,String sequence_number, Money final_balance, Money first_balance,
				   Date statement_date, Date closing_date,  Money booked_balance) {	
    	this.accountID = accountID;
    	this.transaction_number = transaction_number;
    	this.sequence_number = sequence_number;
		this.first_balance = first_balance;
        this.final_balance = final_balance;
        this.statement_date = statement_date;
        this.closing_date = closing_date;
        this.booked_balance = booked_balance;
    }

	/**
	* @return accountID the accountID for this balance
	*/
	public String getAccountID() {
		return accountID;
	}

	/**
	* @param accountID
	* Sets an accountID for this balance
	*/
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	* @return transaction_number the transaction_number for this balance
	*/
	public String getTransaction_number() {
		return transaction_number;
	}

	/**
	* @param transaction_number
	* Sets a transaction_number for this balance
	*/
	public void setTransaction_number(String transaction_number) {
		this.transaction_number = transaction_number;
	}

	/**
	* @return sequence_number the sequence_number for this balance
	*/
	public String getSequence_number() {
		return sequence_number;
	}

	/**
	* @param sequence_number
	* Sets a sequence_number for this balance
	*/
	public void setSequence_number(String sequence_number) {
		this.sequence_number = sequence_number;
	}

	/**
	* @return first_balance the first_balance for this balance
	*/
	public Money getFirst_balance() {
		return first_balance;
	}

	/**
	* @param first_balance
	* Sets a first_balance for this balance
	*/
	public void setFirst_balance(Money first_balance) {
		this.first_balance = first_balance;
	}

	/**
	* @return final_balance the final_balance for this balance
	*/
	public Money getFinal_balance() {
		return final_balance;
	}

	/**
	* @param final_balance
	* Sets a final_balance for this balance
	*/
	public void setFinal_balance(Money final_balance) {
		this.final_balance = final_balance;
	}

	/**
	* @return statement_date the statement_date for this balance
	*/
	public Date getStatement_date() {
		return statement_date;
	}

	/**
	* @param statement_date
	* Sets a statement_date for this balance
	*/
	public void setStatement_date(Date statement_date) {
		this.statement_date = statement_date;
	}

	/**
	* @return closing_date the closing_date for this balance
	*/
	public Date getClosing_date() {
		return closing_date;
	}

	/**
	* @param closing_date
	* Sets a closing_date for this balance
	*/
	public void setClosing_date(Date closing_date) {
		this.closing_date = closing_date;
	}

	/**
	* @return booked_balance the booked_balance for this balance
	*/
	public Money getBooked_balance() {
		return booked_balance;
	}

	/**
	* @param booked_balance
	* Sets a booked_balance for this balance
	*/
	public void setBooked_balance(Money booked_balance) {
		this.booked_balance = booked_balance;
	}

	/**
	* @return transactions the transactions list for this balance
	*/
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	* @param t
	* Adds a transaction for this balance
	*/
	public void addTransaction(Transaction t) {
		 transactions.add(t);
	}
}