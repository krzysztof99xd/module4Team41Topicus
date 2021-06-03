package lendaryModel;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Balance {
	
	private List<Transaction> transactions;
	
    private String IBAN_no;
    private int final_balance;
    private int closing_balance;
    private Date final_date;
    private Date closing_date;	
    private String currency;
    private char debit_or_credit_mark;
    private int amount;
    
    
    
    private String name;
    private String field;
    public Balance(String IBAN_no, String name, String field) throws IOException {
        this.IBAN_no = IBAN_no;
        this.name = name;
        this.field = field;
    }
    
    
    public Balance(String IBAN_no, int final_balance, int closing_balance ,
    		Date final_date, Date closing_date, String currency, char debit_or_credit_mark ,int amount) {
    	
    	this.IBAN_no = IBAN_no;
    	this.final_balance = final_balance;
    	this.closing_balance = closing_balance;
		this.final_date = final_date;
        this.closing_date = closing_date;
        this.currency = currency;
        this.debit_or_credit_mark = debit_or_credit_mark;
        this.amount = amount;
    }


	public String getIBAN_no() {
		return IBAN_no;
	}


	public void setIBAN_no(String iBAN_no) {
		IBAN_no = iBAN_no;
	}


	/**
	 * @return the closing_balance
	 */
	public int getClosing_balance() {
		return closing_balance;
	}


	/**
	 * @param closing_balance the closing_balance to set
	 */
	public void setClosing_balance(int closing_balance) {
		this.closing_balance = closing_balance;
	}


	/**
	 * @return the final_balance
	 */
	public int getFinal_balance() {
		return final_balance;
	}


	/**
	 * @param final_balance the final_balance to set
	 */
	public void setFinal_balance(int final_balance) {
		this.final_balance = final_balance;
	}


	/**
	 * @return the closing_date
	 */
	public Date getClosing_date() {
		return closing_date;
	}


	/**
	 * @param closing_date the closing_date to set
	 */
	public void setClosing_date(Date closing_date) {
		this.closing_date = closing_date;
	}


	/**
	 * @return the final_date
	 */
	public Date getFinal_date() {
		return final_date;
	}


	/**
	 * @param final_date the final_date to set
	 */
	public void setFinal_date(Date final_date) {
		this.final_date = final_date;
	}


	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}


	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}


	/**
	 * @return the debit_or_credit_mark
	 */
	public char getDebit_or_credit_mark() {
		return debit_or_credit_mark;
	}


	/**
	 * @param debit_or_credit_mark the debit_or_credit_mark to set
	 */
	public void setDebit_or_credit_mark(char debit_or_credit_mark) {
		this.debit_or_credit_mark = debit_or_credit_mark;
	}


	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}


	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}


	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}



}
