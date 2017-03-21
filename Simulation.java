public class Simulation {
	
	
	public static void main() {
		// TO DO : Ajouter constructeur dans link, avec paramètre pour choisir l'écart de débit (90 à 110, 80 à 120, 50 à 150...)
		// Dans Link : Surcharger le constructeur
		
		// Paramètres
		int k = 100;
		int tailleNetwork = 10;
		Network n = new Network(tailleNetwork);
		LSORk Routage = new LSORk(n,k);
		
		int TimeUnit = 0 ;
		int delayRefreshValInst = 10;
		
		int dataToSend = 1000000;
		Device Depart = n.getFirst();
		Device Arrive = n.getLast();
		
		Depart.waitlist = dataToSend;
		Depart.futurwaitlist = dataToSend;
		while(Arrive.waitlist != dataToSend) {
                    if (TimeUnit%100 == 0 ){ // mets a jours les valeurs instantan�es de chaque liens
				System.out.print((float)Arrive.waitlist/dataToSend*100 +"%\n");
			}
                    if (TimeUnit%delayRefreshValInst == 0 ){ // mets a jours les valeurs instantan�es de chaque liens
                            n.reset();
                    }
                    n.SendData(Routage);
                    n.maj();
                    TimeUnit++;// incr�mente l'unit� de temps
		}
		
		
		System.out.println("Le delay d'envoi de "+dataToSend+" data est de :"+TimeUnit );
	}
	
	
	
}
