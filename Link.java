
public class Link {
	
	private Device A;
	private Device B;
	private int debitMin;
	private int debitMax;
	private int debitMoy;
	private int debitInst;
	
	/**
	 * Constructeur par defaut
	 * 
	 * @param d1 initialise le device A
	 * @param d2 initialise le device B
	 * @param debMax initialise le debit maximum
	 * @param debMin initialise le debit minimum
	 * 
	 * initialise les debits
	 */
	Link(Device d1, Device d2){
		A = d1;
		B = d2;
		debitMin = 10 + (int)(Math.random()*30);
	    debitMax = 60 + (int)(Math.random()*40);
		setDebitMoy((debitMax+debitMin)/2);
		randomDebitInstant();
		A.addLink(this);
		B.addLink(this);
	}
	/**
	 * Constructeur 
	 * 
	 * @param d1 initialise le device A
	 * @param d2 initialise le device B
	 * @param debitMinInf la borne Inferieur du debit minimum
	 * @param debitMinSup la borne Superieur du debit minimum
	 * @param debitMaxInf la borne Inferieur du debit maximum
	 * @param debitMaxSup la borne Superieur du debit maximum
	 * 
	 * Constructeur pour creer des liens avec des valeurs de debit min et max aleatoire avec les bornes fournies en parametre
	 * debitMinInf < debitMinSup <= debitMaxInf < debitMaxSup
	 */
	Link(Device d1, Device d2,int debitMinInf,int debitMinSup, int debitMaxInf, int debitMaxSup){
		A = d1;
		B = d2;
		debitMin = debitMinInf + (int)(Math.random()*(debitMinSup-debitMinInf));
	    debitMax = debitMaxInf + (int)(Math.random()*(debitMaxSup-debitMaxInf));
		setDebitMoy((debitMax+debitMin)/2);
		randomDebitInstant();
		A.addLink(this);
		B.addLink(this);
	}
	
	/**
	 * Constructeur
	 * 
	 * @param d1 initialise le device A
	 * @param d2 initialise le device B
	 * @param debMax initialise le debit maximum
	 * @param debMin initialise le debit minimum
	 * 
	 * initialise les debits, valeur minimum aleatoire entre 10 et 40, valeur maximun = debitmin+ecart
	 */
	Link(Device d1, Device d2, int ecart){
		A = d1;
		B = d2;
		debitMin = 10 + (int)(Math.random()*30);
	    debitMax = debitMin + ecart;
		setDebitMoy((debitMax+debitMin)/2);
		randomDebitInstant();
		A.addLink(this);
		B.addLink(this);
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
	 * initialisation du débit instantane
	 * @return le debitInst avec sa nouvelle valeur.
	 */
	public int randomDebitInstant(){
		int random = getDebitMin() + (int)(Math.random() * ((getDebitMax() - getDebitMin()) + 1));
		setDebitInst(random);
		return debitInst;
	}

	public int getDebitMoy() {
		return debitMoy;
	}

	public void setDebitMoy(int debitMoy) {
		this.debitMoy = debitMoy;
	}

	public void toStringDebInst(){
		System.out.print("[Debit instant "+getDebitInst()+"] ");
	}
	
	public void toStringDebMoy(){
		System.out.print("[Debit moyen "+getDebitMoy()+"] ");
	}
	
	public void toStringDeb(){
		System.out.print("[ ~ "+getDebitMoy()+"/ I "+getDebitInst()+"] ");
	}
}
