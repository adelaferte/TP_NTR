public class Link {
	
	private Device A;
	private Device B;
	private int debitMin;
	private int debitMax;
	private int debitInst;
	
	/**
	 * Constructeur
	 * 
	 * @param d1 initialise le device A
	 * @param d2 initialise le device B
	 * 
	 * initialise les débits
	 */
	Link(Device d1, Device d2){
		A = d1;
		B = d2;
		debitMax = 20;
		debitMin = 10;
		debitInst = 0;
	}

	public Device getA() {
		return A;
	}

	public void setA(Device a) {
		A = a;
	}

	public Device getB() {
		return B;
	}

	public void setB(Device b) {
		B = b;
	}

	public int getDebitMin() {
		return debitMin;
	}

	public void setDebitMin(int debitMin) {
		this.debitMin = debitMin;
	}

	public int getDebitMax() {
		return debitMax;
	}

	public void setDebitMax(int debitMax) {
		this.debitMax = debitMax;
	}

	public int getDebitInst() {
		return debitInst;
	}

	public void setDebitInst(int debitInst) {
		this.debitInst = debitInst;
	}
	
	
	
	
	
	

}
