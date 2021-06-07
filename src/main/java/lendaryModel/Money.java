package lendaryModel;

public class Money {
    private String currency;
    private float amount;
    private int money_id;
    private char debit_or_credit;

    public Money(String currency, float amount, char debit_or_credit) {
        this.currency = currency;
        this.amount = amount;
        this.debit_or_credit = debit_or_credit;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getMoney_id() {
        return money_id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setMoney_id(int money_id) {
        this.money_id = money_id;
    }
    public char getDebit_or_credit() {
        return debit_or_credit;
    }
    public void setDebit_or_credit(char debit_or_credit) {
        this.debit_or_credit = debit_or_credit;
    }
}
