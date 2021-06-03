package lendaryModel;

public class analitics {
	
	private int minSaveAmount;
	private Balance  b;

	public analitics() {
		
	}
	
	
	public int maxAmount() {
		
		return 10;
	}
	
	public int minAmount() {
		
		return 1;
	}
	
	public int avrageAmount() {
		
		return 5;
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
