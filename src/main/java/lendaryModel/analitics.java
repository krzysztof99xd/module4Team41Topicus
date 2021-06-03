package lendaryModel;

public class analitics {
	
	private int minSaveAmount;
	private Balance  b;

	public analitics() {
		
	}
	
	
	public int maxAmount() {
		int amount = 0;
		for(int i = 0; i < b.getTransactions().size() ; i++) {
			if(b.getTransactions().get(i).getAmount() > amount) {
				amount = b.getTransactions().get(i).getAmount();
			}
		}
		return amount;
	}
	
	public int minAmount() {
		int amount = b.getTransactions().get(0).getAmount();
		for(int i = 1; i > b.getTransactions().size() ; i++) {
			if(b.getTransactions().get(i).getAmount() < amount) {
				amount = b.getTransactions().get(i).getAmount();
			}
		}
		return amount;
	}
	
	public int avrageAmount() {
		
		int amount = 0;
		for(int i = 0; i < b.getTransactions().size() ; i++) {
			
				amount += b.getTransactions().get(i).getAmount();
			
		}
		amount = amount/b.getTransactions().size();
		return amount;
	}
	
	public int[] balancelist() {
		
		return new int[10];
	}
	
	public boolean save() {
		if(b.getClosing_balance() >= minSaveAmount) {
			return true;
		}
		return false;
	}


	/**
	 * @return the minSaveAmount
	 */
	public int getMinSaveAmount() {
		return minSaveAmount;
	}


	/**
	 * @param minSaveAmount the minSaveAmount to set
	 */
	public void setMinSaveAmount(int minSaveAmount) {
		this.minSaveAmount = minSaveAmount;
	}
	
	
	
	

}
