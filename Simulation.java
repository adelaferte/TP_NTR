public class Simulation {
	
	
	public static void main(String[] args) {
		// TO DO : Ajouter constructeur dans link, avec paramètre pour choisir l'écart de débit (90 à 110, 80 à 120, 50 à 150...)
		// Dans Link : Surcharger le constructeur
		
		// Paramètres
		int tailleNetwork = 3;
		Network n = new Network(tailleNetwork);
		
		int TimeUnit = 0 ;
		int delayRefreshValInst = 5;
		
		int dataToSend = 5000;
		Device Depart = n.getFirst();
		Device Arrive = n.getLast();
		
		Depart.waitlist = dataToSend;
		
		while(Arrive.waitlist != dataToSend) {
			if (TimeUnit%delayRefreshValInst == 0 ){ // mets a jours les valeurs instantan�es de chaque liens
				n.reset();
			}
			n.SendData();
			TimeUnit++;// incr�mente l'unit� de temps
		}
		
		
		System.out.println("Le delay d'envoi de "+dataToSend+" data est de :"+TimeUnit );
	}
	
	
	
}
