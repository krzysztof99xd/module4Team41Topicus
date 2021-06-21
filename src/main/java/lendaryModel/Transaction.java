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

    public Transaction(Date valueDate, String statement_number, String account_id,
                       String customer_reference,Date entry_date, Money amount ) {
        this.valueDate = valueDate;
        this.accountID= accountID;
        this.customer_reference = customer_reference;
        this.entry_date = entry_date;
        this.amount = amount;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccount_id(String account_id) {
        this.accountID = accountID;
    }

    public String getCustomer_reference() {
        return customer_reference;
    }

    public void setCustomer_reference(String customer_reference) {
        this.customer_reference = customer_reference;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
}