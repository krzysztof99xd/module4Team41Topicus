package lendaryModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Analytics {

	final private float[] MIN_REQUIRED = {5000, 10000};

	private String accountID;
	private float savePersent;
	private float minAmount;
	private float maxAmount;
	private float avgAmount;
	private List<Float> amounts;
	private List<Date> dates;

	/**
	* @param accountID
	* Contructor
	*/	
	public Analytics(String accountID) {
		this.accountID = accountID;
		this.amounts = new ArrayList<Float>();
		this.dates = new ArrayList<Date>();
	}


	/**
	 *
	 * @param amount 
	 * @param debitCredit type of card, either D for debit either C for credit
	 * @param date
	 * Takes the previous amount from "amounts" list and adds it to the list.
	 * Adds the date afterwards, to the "dates" list, to the corresponding amount, that we've added earlier.
	 * After the amount was added and also the corresponding date, we make use of those 2 if methods to set our new minAmount or maxAmount.
	 */
	public void AddAmount(float amount, String debitCredit, Date date) {

		float previous = amounts.get(amounts.size() - 1);
		if (debitCredit.toLowerCase().contains("d")) {
			amounts.add((previous - amount));
			
		} else {
			amounts.add((previous + amount));
			
		}
		dates.add(date);

		if (minAmount > amounts.get(amounts.size() - 1)) {
			minAmount = amounts.get(amounts.size() - 1);
		}
		if (maxAmount < amounts.get(amounts.size() - 1)) {
			maxAmount = amounts.get(amounts.size() - 1);
		}
	}

	/**
	* @return the maximum amount
	*/
	public float getMaxAmount() {
		return maxAmount;
	}

	/**
	* @param amount
	* @param date
	* As its name states, "setFirst", this method initialises the maxAmount, minAmount with amount,
	* adds to the "amounts" list the amount and the corresponding date in "dates" list.
	*/
	public void setfirst(float amount, Date date) {
		maxAmount = amount;
		minAmount = amount;
		amounts.add(amount);
		dates.add(date);
	}

	/**
	*
	* 
	*/
	public void setfinal() {
		setAvg();
		setSave();
	}

	/**
	* This method calculates the average amount, by iterating through "amounts" list and the making the computations.
	*/
	public void setAvg() {
		float total = 0;
		for (float a : amounts) {
			total += a;
		}
		avgAmount = (total / amounts.size());
	}

	/**
	*
	* 
	*/
	public void setSave() {
		float saveTotal = 0;
		for (float a : amounts) {
			if (a > MIN_REQUIRED[1]) {
				saveTotal += 1;
			} else if (a < MIN_REQUIRED[1] && a > MIN_REQUIRED[0]) {
				saveTotal += (0.5f);
			}
		}
		savePersent = (saveTotal / amounts.size()) * 100;
		System.out.print(savePersent);
//		float total = 0;
//		float saveTotal = 0;
//		for (float a : amounts) {
//			total += a;
//			if (a > MIN_REQUIRED[1]) {
//				saveTotal += a;
//			} else if (a < MIN_REQUIRED[1] && a > MIN_REQUIRED[0]) {
//				saveTotal += (a / 2);
//			}
//
//		}
//		savePersent = (saveTotal / total) * 100;
	}

	/**
	*
	* @return MIN_REQUIRED
	*/
	public float[] getMIN_REQUIRED() {
		return MIN_REQUIRED;
	}

	/**
	* @return accountID the account's ID
	*/
	public String getAccountID() {
		return accountID;
	}

	/**
	*
	* @return savePersent
	*/
	public float getSavePersent() {
		return savePersent;
	}

	/**
	* @return minAmount the minimum amount from the "amounts" list.
	*/
	public float getMinAmount() {
		return minAmount;
	}

	/**
	* @return avgAmount the average amount of the "amounts" list.
	*/
	public float getAvgAmount() {
		return avgAmount;
	}

	/**
	* @return amounts a list with all current amounts
	*/
	public List<Float> getAmounts() {
		return amounts;
	}

	/**
	* @return dates a list with all current dates
	*/
	public List<Date> getDates() {
		return dates;
	}
}