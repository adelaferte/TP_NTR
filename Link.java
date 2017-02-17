
public class Link {
	
	private Device A;
	private Device B;
	private int debitMin;
	private int debitMax;
	private int debitMoy;
	private int debitInst;
	
	/**
	 * Constructeur
	 * 
	 * @param d1 initialise le device A
	 * @param d2 initialise le device B
	 * @param debMax initialise le debit maximum
	 * @param debMin initialise le debit minimum
	 * 
	 * initialise les débits
	 */
	Link(Device d1, Device d2, int debMax, int debMin){
		A = d1;
		B = d2;
		debitMax = debMax;
		debitMin = debMin;
		setDebitMoy((debitMax+debitMin)/2);
		randomDebitInstant();
		A.addLink(this);
		B.addLink(this);
	}
	
	/**
	  Constructeur
	 * 
	 * @param debMax initialise le debit maximum
	 * @param debMin initialise le debit minimum
	 * 
	 * initialise les debits
	 */
	Link(int debMax, int debMin){
		A = null;
		B = null;
		debitMax = debMax;
		debitMin = debMin;
		setDebitMoy((debitMax+debitMin)/2);
	}
	
	/**
	 * return le voisin du Device a
	 * @param a Device
	 * @return le voisin du Device a
	 */
	public Device getVoisin(Device a){
		
		if(A == a)
			return B;
		else
			return A;
	}

	public Device getA() {
		return A;
	}

	public Device getB() {
		return B;
	}


	public int getDebitMin() {
		return debitMin;
	}

	public int getDebitMax() {
		return debitMax;
	}


	public int getDebitInst() {
		return debitInst;
	}

	public void setDebitInst(int debitInst) {
		this.debitInst = debitInst;
	}
	
	/**
	 * initialisation du débit instantané
	 * @return le debitInst avec sa nouvelle valeur.
	 */
	public int randomDebitInstant(){
		int random = getDebitMin() + (int)(Math.random() * ((getDebitMax() - getDebitMin()) + 1));;
		setDebitInst(random);
		return debitInst;
	}

	public int getDebitMoy() {
		return debitMoy;
	}

	public void setDebitMoy(int debitMoy) {
		this.debitMoy = debitMoy;
	}

}
