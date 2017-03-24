public class Simulation {
	
	public int[][][] waitList;
	public void Simulation(int k,int tailleNetwork) {
		// TO DO : Ajouter constructeur dans link, avec paramètre pour choisir l'écart de débit (90 à 110, 80 à 120, 50 à 150...)
		// Dans Link : Surcharger le constructeur
		
		// Paramètres
                
                waitList = new int[tailleNetwork][tailleNetwork][k];

		Network n = new Network(tailleNetwork);
		LSORk[] Routages = new LSORk[k];
                
                for (int i = 0;i<k;i++){
                    Routages[i] = new LSORk(n,i);
                }
		int TimeUnit = 0 ;
		int delayRefreshValInst = 10;
		
		int dataToSend = 100000;
                for (int z = 0;z<k;z++){
                    waitList[0][0][z] = dataToSend;
                }
		Device Arrive = n.getLast();
		
                int numberoffinish = 0;
		while(numberoffinish < k) {
                    if (TimeUnit%1000 == 0 ){ // mets a jours les valeurs instantanes de chaque liens
                            System.out.println();
                    }
                    if (TimeUnit%delayRefreshValInst == 0 ){ // mets a jours les valeurs instantanes de chaque liens
                            n.reset();
                    }
                    for (int i=0;i<k;i++){
                        n.set(waitList,i);
                        n.SendData(Routages[i]);
                        n.maj();
                        majwaitlist(n,i);
                        TimeUnit++;// incremente l'unite de temps
                    }
                    for (int i = 0;i<k;i++){
                        if(waitList[tailleNetwork-1][tailleNetwork -1][i] == dataToSend){
                            System.out.println("LSOR "+ i + " finished in "+ TimeUnit + " time Unit");
                            waitList[tailleNetwork-1][tailleNetwork -1][i] = 0;
                            numberoffinish++;
                        }
                    }
		}
		
	}
        public void majwaitlist(Network n,int i){
            for ( int x  = 0 ; x < n.size ; x ++ ){
    		for (int y = 0 ; y < n.size ; y ++ ){
    			waitList[x][y][i] = n.devicegrille[x][y].waitlist;
    		}
            }
        }
        public boolean isFinish(int val,int tailleNetwork){
            boolean flag = false;
            for (int i = 0;i<waitList[0][0].length;i++){
                flag = flag && (waitList[tailleNetwork-1][tailleNetwork-1][i] == -1);
            }
            return flag;
        }
}
