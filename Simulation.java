/**
 * @author jrogala, qdubois, adelaferte, jgachelin
 */

public class Simulation {
	
	private int[][][] waitList;
    private Network n;
    private int k;
    private int dataToSend;
    private int[] lsorkfinished;
        
	public void lancerSimulation(int k,int tailleNetwork) {
		// TO DO : Ajouter constructeur dans link, avec paramètre pour choisir l'écart de débit (90 à 110, 80 à 120, 50 à 150...)
		// Dans Link : Surcharger le constructeur
		
		// Paramètres
                
                waitList = new int[tailleNetwork][tailleNetwork][k];
                this.k = k;

		n = new Network(tailleNetwork);
		LSORk[] Routages = new LSORk[k];
                lsorkfinished = new int[k];
                
                for (int i = 0;i<k-1;i++){
                    Routages[i] = new LSORk(n,i);
                }
                Routages[k-1] = new LSORk(n,999999);
		int TimeUnit = 0 ;
		int delayRefreshValInst = 100;
		
		dataToSend = 100000;
                for (int z = 0;z<k;z++){
                    waitList[0][0][z] = dataToSend;
                }
		
                int numberoffinish = 0;
		while(numberoffinish < k) {
                    if (TimeUnit%10 == 0 ){ // mets a jours les valeurs instantanes de chaque liens
                            test();
                            for (int i = 0;i<k;i++){
                                float val = (float)waitList[tailleNetwork-1][tailleNetwork-1][i]/dataToSend*100;
                                System.out.print(val+" ");
                            }
                            
                            System.out.println();
                            //printstate();
                            //n.toStringDeb();
                    }
                    if (TimeUnit%delayRefreshValInst == 0 ){ // mets a jours les valeurs instantanes de chaque liens
                            n.reset();
                    }
                    for (int i=0;i<k;i++){
                        if (lsorkfinished[i] == 0){
                            n.set(waitList,i);
                            n.SendData(Routages[i]);
                            n.maj();
                            majwaitlist(n,i);
                        }
                    }
                    
                    TimeUnit++;// incremente l'unite de temps
                    for (int i = 0;i<k;i++){
                        if(waitList[tailleNetwork-1][tailleNetwork -1][i] == dataToSend && lsorkfinished[i] == 0){
                            numberoffinish++;
                            lsorkfinished[i] = numberoffinish;
                        }
                    }
		}
                for (int i = 0;i<k;i++){
                                float val = (float)waitList[tailleNetwork-1][tailleNetwork-1][i]/dataToSend*100;
                                System.out.print(val+" ");
                }
                System.out.println();
                for (int i = 0;i<k;i++){
                    System.out.println("LSORk#"+i+" Finished at the "+lsorkfinished[i]+" position");
                }
                
		
	}
        public void majwaitlist(Network n,int i){
            for ( int x  = 0 ; x < n.getSize() ; x ++ ){
    		for (int y = 0 ; y < n.getSize() ; y ++ ){
    			waitList[x][y][i] = n.getDeviceGrille()[x][y].getWaitlist();
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
        
        public void test(){
            for (int i =0;i<k;i++){
                if (lsorkfinished[i] == 0){
                    int m = 0;
                    for ( int x  = 0 ; x < n.getSize() ; x ++ ){
                        for (int y = 0 ; y < n.getSize() ; y ++ ){
                                m+=waitList[x][y][i];
                        }
                    }
                    if (m != dataToSend){
                        System.out.println("Error at LSOR"+i);
                    }
                }
                
            }
          
        }
        
        public void printstate(){
            for (int i = 0;i<n.getSize();i++){
                for (int j = 0;j<n.getSize();j++){
                    for (int l = 0; l < k; l++){
                       System.out.print(waitList[i][j][l]);
                    }
                    System.out.print(" ");
                }
            System.out.println();
            }
        }
}
