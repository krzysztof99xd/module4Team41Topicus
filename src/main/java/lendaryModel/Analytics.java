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

	public Analytics(String accountID) {
		this.accountID = accountID;
		this.amounts = new ArrayList<Float>();
		this.dates = new ArrayList<Date>();
	}


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
	public float getMaxAmount() {
		return maxAmount;
	}


	public void setfirst(float amount, Date date) {

		maxAmount = amount;
		minAmount = amount;
		amounts.add(amount);
		dates.add(date);
	}

	public void setfinal() {
		setAvg();
		setSave();
	}

	public void setAvg() {

		float total = 0;
		for (float a : amounts) {

			total += a;
		}
		avgAmount = (total / amounts.size());

	}

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
	public float[] getMIN_REQUIRED() {
		return MIN_REQUIRED;
	}

	public String getAccountID() {
		return accountID;
	}

	public float getSavePersent() {
		return savePersent;
	}

	public float getMinAmount() {
		return minAmount;
	}

	public float getAvgAmount() {
		return avgAmount;
	}

	public List<Float> getAmounts() {
		return amounts;
	}

	public List<Date> getDates() {
		return dates;
	}

	

}
	

