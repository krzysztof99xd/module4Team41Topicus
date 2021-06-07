package lendaryModel;

import java.util.Date;

public class Transaction {

    
    private Date valueDate;
    private Date enteryDate;
    private int amount;
    
    
    public Transaction(Date valueDate, Date enteryDate, int amount) {
        this.valueDate = valueDate;
        this.enteryDate = enteryDate;
        this.amount = amount;
    }


    /**
     * @return the valueDate
     */
    public Date getValueDate() {
        return valueDate;
    }


    /**
     * @param valueDate the valueDate to set
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
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
     * @return the enteryDate
     */
    public Date getEnteryDate() {
        return enteryDate;
    }


    /**
     * @param enteryDate the enteryDate to set
     */
    public void setEnteryDate(Date enteryDate) {
        this.enteryDate = enteryDate;
    }
    

}