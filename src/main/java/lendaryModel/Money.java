package lendaryModel;

public class Money {
    private String currency;
    private float amount;
    private int money_id;
    private char debit_or_credit ;

    /**
     * @param currency
     * @param amount
     * @param debit_or_credit
     * Constructor
     */
    public Money(String currency, float amount, char debit_or_credit) {
        this.currency = currency;
        this.amount = amount;
        this.debit_or_credit = debit_or_credit;
    }
    /**
     * @param currency
     * Sets the currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets the money id from the money table
     * @return the money id
     */
    public int getMoney_id() {
        return money_id;
    }
    /**
     * @param amount
     * Sets the amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }
    /**
     * Gets the amount of the transaction from the money table
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }
    /**
     * Gets the used currency for the transaction from the money table
     * @return the curreny
     */
    public String getCurrency() {
        return currency;
    }
    /**
     * @param money_id
     * Sets the money_id
     */
    public void setMoney_id(int money_id) {
        this.money_id = money_id;
    }
    /**
     * Gets a mark whether the transaction was debit or credit from the money table
     * @return a letter d or c to indicate debit or credit
     */
    public char getDebit_or_credit() {
        return debit_or_credit;
    }
    /**
     * @param debit_or_credit
     * Sets it to either debit D, either credit C
     */
    public void setDebit_or_credit(char debit_or_credit) {
        this.debit_or_credit = debit_or_credit;
    }
}
