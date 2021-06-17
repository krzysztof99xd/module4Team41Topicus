package lendaryModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Analytics {

	final private float[] MIN_REQUIRED = {10000, 50000};

	private String accountID;
	private float savePersent;


	

	private float minAmount;
	private float maxAmount;
	private float avgAmount;
	private List<AmountPerTime> amounts;


	public Analytics(String accountID) {
		this.accountID = accountID;
		this.amounts = new ArrayList<AmountPerTime>();
	}


	public void AddAmount(float amount, String debitCredit, Date date) {

		float previous = amounts.get(amounts.size() - 1).getAmount();
		if (debitCredit.toLowerCase().contains("c")) {
			amounts.add(new AmountPerTime(previous - amount, date));
		} else {
			amounts.add(new AmountPerTime(previous + amount, date));
		}

		if (minAmount > amounts.get(amounts.size() - 1).getAmount()) {
			minAmount = amounts.get(amounts.size() - 1).getAmount();
		}
		if (maxAmount < amounts.get(amounts.size() - 1).getAmount()) {
			maxAmount = amounts.get(amounts.size() - 1).getAmount();
		}

	}
	public float getMaxAmount() {
		return maxAmount;
	}


	public void setfirst(float amount, Date date) {

		maxAmount = amount;
		minAmount = amount;
		amounts.add(new AmountPerTime(amount, date));
	}

	public void setfinal() {
		setAvg();
		setSave();
	}

	public void setAvg() {

		float total = 0;
		for (AmountPerTime a : amounts) {

			total += a.getAmount();
		}
		avgAmount = (total / amounts.size());

	}

	public void setSave() {

		float total = 0;
		float saveTotal = 0;
		for (AmountPerTime a : amounts) {
			total += a.getAmount();
			if (a.getAmount() > MIN_REQUIRED[1]) {
				saveTotal += a.getAmount();
			} else if (a.getAmount() < MIN_REQUIRED[1] && a.getAmount() > MIN_REQUIRED[0]) {
				saveTotal += (a.getAmount() / 2);
			}

		}
		savePersent = (saveTotal / total) * 100;

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

	public List<AmountPerTime> getAmounts() {
		return amounts;
	}


	public class AmountPerTime {
		public AmountPerTime(float amount, Date date) {
			this.amount = amount;
			this.date = date;
		}

		private float amount;
		private Date date;

		public float getAmount() {
			return amount;
		}

		public Date getDate() {
			return date;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		public void setDate(Date date) {
			this.date = date;
		}

	}

}
	

