/**
 * @author jrogala, qdubois, adelaferte, jgachelin
 */

public class Simulation {

	private int[][][] waitList;
	private Network n;
	private int k;
	private int dataToSend;
	private int[] lsorkfinished;
	private int[] lsorkfinishedTime;
	private int tailleNetwork;

	// creation d'une simulation avec network prefait.
	public void lancerSimulationWithPremadeNetWork(int k, Network netw) {
		n = netw;
		tailleNetwork = n.getSize();
		lancerSimulation(k);
	}

	// creation d'une simulation avec nouveau network.
	public void lancerSimulationWithCreationNetWork(int k, int sizeNetwork) {
		n = new Network(sizeNetwork);
		tailleNetwork = sizeNetwork;
		lancerSimulation(k);
	}

	private void lancerSimulation(int k) {

		// Paramètres
		this.k = k;

		// initialisation du tableau de waitlist ( etat des fils d'attente pour
		// chaque noeud, pour chaque test
		waitList = new int[tailleNetwork][tailleNetwork][k];

		// creation du network de taille tailleNetwork*tailleNetwork si il est null
		// creation et initialisation de la liste des routages utilise pour le test
		LSORk[] Routages = new LSORk[k];
		for (int i = 0; i < k - 1; i++) {
			Routages[i] = new LSORk(n, i);
		}
		// defini le dernier routage avec vision global.
		Routages[k - 1] = new LSORk(n, 999999);

		// tableau d'INT representant l'etat actuel d'une simulation pour chaque routage
		lsorkfinished = new int[k];
		lsorkfinishedTime = new int[k];

		// nombre d'unite de temps ecoule depuis le debut du test
		int TimeUnit = 0;

		// delay
		int delayRefreshValInst = 100;
		int delayRefreshAffichageDEBUG = 100;

		// gestion des parametres pour les Delay
		int delayEntree = 10;
		int debitEntree = 100;
		int Entree = debitEntree * delayEntree;
		int totalEntree = 0;

		// ajoute les packets au noeud 0-0
		dataToSend = 100000;
		for (int z = 0; z < k; z++) {
			waitList[0][0][z] = dataToSend;
		}

		System.out
				.println("Progression du pourcentage de paquet arrive a destination en fonction du nombre k si dessous");
		System.out.print("Time/k\t");
		for (int i = 0; i < k; i++) {
			System.out.print(i + "\t");
		}
		System.out.println("");

		int numberoffinish = 0;
		while (numberoffinish < k) {// tant que tout les tests ne sont pas
									// termine
			if (TimeUnit%delayEntree == 0 && totalEntree < dataToSend){ 
		        for (int z = 0;z<k;z++){
		            waitList[0][0][z] = Entree;
		        }
	            totalEntree += Entree;
            }
			if (TimeUnit % delayRefreshAffichageDEBUG == 0) {
				//test();
				System.out.print(TimeUnit + "\t");
				for (int i = 0; i < k; i++) {
					float val = (float) waitList[tailleNetwork - 1][tailleNetwork - 1][i]
							/ dataToSend * 100;
					System.out.print(String.format("%.1f", val) + "\t");
				}

				System.out.println();
				// printstate();
				// n.toStringDeb();
			}
			if (TimeUnit % delayRefreshValInst == 0) { // mets a jours les
														// valeurs instantanes
														// de chaque liens
				n.reset();
			}
			for (int i = 0; i < k; i++) {
				if (lsorkfinished[i] == 0) {
					n.set(waitList, i);
					n.SendData(Routages[i]);
					n.maj();
					majwaitlist(n, i);
				}
			}

			TimeUnit++;// incremente l'unite de temps
			for (int i = 0; i < k; i++) {
				if (waitList[tailleNetwork - 1][tailleNetwork - 1][i] == dataToSend
						&& lsorkfinished[i] == 0) {
					numberoffinish++;
					lsorkfinished[i] = numberoffinish;
					lsorkfinishedTime[i] = TimeUnit - 1;
				}
			}
		} // fin while
		System.out.print(TimeUnit + "\t");
		for (int i = 0; i < k; i++) {
			float val = (float) waitList[tailleNetwork - 1][tailleNetwork - 1][i]
					/ dataToSend * 100;
			System.out.print(String.format("%.1f", val) + "\t");
		}

		System.out.println();
		for (int i = 0; i < k; i++) {
			float packetPerUnit = ((float) dataToSend)
					/ ((float) lsorkfinishedTime[i]);
			System.out.println("LSORk#" + i + " Finished at the "
					+ lsorkfinished[i] + " position in " + lsorkfinishedTime[i]
					+ " time Unit ( " + String.format("%.2f", packetPerUnit)
					+ " packet/TimeUnit)");
		}

	}

	public void majwaitlist(Network n, int i) {
		for (int x = 0; x < n.getSize(); x++) {
			for (int y = 0; y < n.getSize(); y++) {
				waitList[x][y][i] = n.getDeviceGrille()[x][y].getWaitlist();
			}
		}
	}

	public boolean isFinish(int val, int tailleNetwork) {
		boolean flag = false;
		for (int i = 0; i < waitList[0][0].length; i++) {
			flag = flag
					&& (waitList[tailleNetwork - 1][tailleNetwork - 1][i] == -1);
		}
		return flag;
	}

	public void test() {
		for (int i = 0; i < k; i++) {
			if (lsorkfinished[i] == 0) {
				int m = 0;
				for (int x = 0; x < n.getSize(); x++) {
					for (int y = 0; y < n.getSize(); y++) {
						m += waitList[x][y][i];
					}
				}
				if (m != dataToSend) {
					System.out.println("Error at LSOR" + i);
				}
			}
		}
	}

	public void printstate() {
		for (int i = 0; i < n.getSize(); i++) {
			for (int j = 0; j < n.getSize(); j++) {
				for (int l = 0; l < k; l++) {
					System.out.print(waitList[i][j][l]);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
