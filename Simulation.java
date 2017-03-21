public class Simulation {
	
	
	public static void main(String[] args) {
		
		// Parametres
		int k = 0;
		int tailleNetwork = 3;
		Network n = new Network(tailleNetwork);
		LSORk Routage = new LSORk(n,k);
		
		int TimeUnit = 0 ;
		int delayRefreshValInst = 5;
		
		int dataToSend = 1;
		Device Depart = n.getFirst();
		Device Arrive = n.getLast();
		
		Depart.waitlist = dataToSend;
		
		while(Arrive.waitlist != dataToSend) {
			if (TimeUnit%delayRefreshValInst == 0 ){ // mets a jours les valeurs instantan�es de chaque liens
				n.reset();
			}
			n.SendData(Routage);
			TimeUnit++;// incr�mente l'unit� de temps
		}
		
		
		System.out.println("Le delay d'envoi de "+dataToSend+" data est de :"+TimeUnit );
	}
	
	
	
}
