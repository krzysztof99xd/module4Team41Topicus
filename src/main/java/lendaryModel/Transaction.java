package lendaryModel;

import java.util.Date;

public class Transaction {


    private Date valueDate;
    private String accountID;
    private String customer_reference;
    private Date entry_date;
    private Money amount;


    public Transaction(){

    }

    /**
     * @param valueDate
     * @param statement_number
     * @param account_id
     * @param customer_reference
     * @param entry_date
     * @param amount
     * Constructor
     */
    public Transaction(Date valueDate, String statement_number, String account_id,
                       String customer_reference,Date entry_date, Money amount ) {
        this.valueDate = valueDate;
        this.accountID= accountID;
        this.customer_reference = customer_reference;
        this.entry_date = entry_date;
        this.amount = amount;
    }
    /**
     * @return valueDate
     */
    public Date getValueDate() {
        return valueDate;
    }
    /**
     * @param valueDate
     * Sets the valueDate for this transaction
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }
    /**
     * @return accountID the accountID of this transaction
     */
    public String getAccountID() {
        return accountID;
    }
    /**
     * @param account_id
     * Sets an account_id for this transaction
     */
    public void setAccount_id(String account_id) {
        this.accountID = accountID;
    }

    /**
     * @return customer_reference
     */
    public String getCustomer_reference() {
        return customer_reference;
    }
    /**
     * @param customer_reference
     * Sets a customer_reference to this transaction
     */
    public void setCustomer_reference(String customer_reference) {
        this.customer_reference = customer_reference;
    }
    /**
     * @return entry_date
     */
    public Date getEntry_date() {
        return entry_date;
    }
    /**
     * @param entry_date
     * Sets an entry_date for this transaction
     */
    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }
    /**
     * @return amount the amount of this transaction
     */
    public Money getAmount() {
        return amount;
    }
    /**
     * @param amount
     * Sets an amount to this transaction
     */
    public void setAmount(Money amount) {
        this.amount = amount;
    }
}