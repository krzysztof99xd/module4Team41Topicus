package lendaryModel;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Balance {
	
	private List<Transaction> transactions;
	
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

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getTransaction_number() {
		return transaction_number;
	}

	public void setTransaction_number(String transaction_number) {
		this.transaction_number = transaction_number;
	}

	public String getSequence_number() {
		return sequence_number;
	}

	public void setSequence_number(String sequence_number) {
		this.sequence_number = sequence_number;
	}

	public Money getFirst_balance() {
		return first_balance;
	}

	public void setFirst_balance(Money first_balance) {
		this.first_balance = first_balance;
	}

	public Money getFinal_balance() {
		return final_balance;
	}

	public void setFinal_balance(Money final_balance) {
		this.final_balance = final_balance;
	}

	public Date getStatement_date() {
		return statement_date;
	}

	public void setStatement_date(Date statement_date) {
		this.statement_date = statement_date;
	}

	public Date getClosing_date() {
		return closing_date;
	}

	public void setClosing_date(Date closing_date) {
		this.closing_date = closing_date;
	}

	public Money getBooked_balance() {
		return booked_balance;
	}

	public void setBooked_balance(Money booked_balance) {
		this.booked_balance = booked_balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction t) {
		 transactions.add(t);
	}

}
