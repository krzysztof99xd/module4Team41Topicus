package lendaryModel;

import java.util.Date;

public class Transaction {


    private Date valueDate;
    private String statement_number;
    private String transaction_number;
//    private char debit_or_credit;
    private String customer_reference;
    private Date entry_date;
    private Money amount;


    public Transaction(Date valueDate, String statement_number, String transaction_number,
                       String customer_reference,Date entry_date, Money amount ) {
        this.valueDate = valueDate;
        this.statement_number = statement_number;
        this.transaction_number = transaction_number;
//        this.debit_or_credit = debit_or_credit;
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

    public String getStatement_number() {
        return statement_number;
    }

    public void setStatement_number(String statement_number) {
        this.statement_number = statement_number;
    }

    public String getTransaction_number() {
        return transaction_number;
    }

    public void setTransaction_number(String transaction_number) {
        this.transaction_number = transaction_number;
    }

//    public char getDebit_or_credit() {
//        return debit_or_credit;
//    }

//    public void setDebit_or_credit(char debit_or_credit) {
//        this.debit_or_credit = debit_or_credit;
//    }

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