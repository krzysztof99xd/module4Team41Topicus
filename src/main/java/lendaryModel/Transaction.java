package lendaryModel;

import java.util.Date;

public class Transaction {


    private Date valueDate;
//    private String statement_number;
    private String account_id;
//    private char debit_or_credit;
    private String customer_reference;
    private Date entry_date;
    private Money amount;


    public Transaction(){

    }
    public Transaction(Date valueDate, String statement_number, String account_id,
                       String customer_reference,Date entry_date, Money amount ) {
        this.valueDate = valueDate;
        this.account_id= account_id;
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

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
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