import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TO DO : Ajouter constructeur dans link, avec paramètre pour choisir l'écart de débit (90 à 110, 80 à 120, 50 à 150...)
		// Dans Link : Surcharger le constructeur
		
		// Parametres
		int tailleNetwork = 3;
		Network n = createNetwork(tailleNetwork);
		
		boolean continu = true;
		
		while(continu) {
			
			System.out.println(" ___________________________________________________");
			System.out.println(" |                                                  |");
			System.out.println(" | Bienvenue !                                      |");
			System.out.println(" | Choisissez votre algorithme de predilection :    |");
			System.out.println(" | ___  1/ OLSR pas a pas                           |");
			System.out.println(" | ___  2/ LSOR pas a pas                           |");
			System.out.println(" | ___  3/ OLSR k                                   |");
			System.out.println(" | ___  4/ LSOR k                                   |");
			System.out.println(" | ___ 11/ Reset network                            |");
			System.out.println(" | ___ 12/ Changer taille reseau                    |");
			System.out.println(" | ___ 13/ Exit                                     |");
			System.out.println(" |__________________________________________________|");
			
			System.out.print("\nVotre choix (1, 2, 3...) : ");
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			
			int debitMinOlsr = 0;
			ArrayList<Device> deviceVisiteolsr = new ArrayList<Device>();

			switch (choix) {
			case 1:main_olsr(n, debitMinOlsr, deviceVisiteolsr);
				break;
			case 2:main_slor(n, debitMinOlsr, deviceVisiteolsr);
				break;
			case 3: System.out.print("Taille de k ? ");
				int k = sc.nextInt();
				main_olsrK(n, debitMinOlsr, deviceVisiteolsr, k);
				break;
			case 4:
				System.out.print("Taille de k ? ");
				k = sc.nextInt();
				main_lsorK(n, debitMinOlsr, deviceVisiteolsr, k);
				break;
			case 11:createNetwork(tailleNetwork);
				break;
			case 12:
				do {
					System.out.print("Nouvelle taille ? ");
					tailleNetwork = sc.nextInt();
					if(tailleNetwork <= 0 || tailleNetwork > 10) {
						System.out.println("/!\\ Erreur : taille max -> 10");
					}
				}while(tailleNetwork <= 0 || tailleNetwork > 10);
				n = createNetwork(tailleNetwork);
				break;
			case 13:
				continu = false;
				System.out.println("Merci et à bientôt :-)");
				break;
			default:;
			}
		}
	}
	
	private static Network createNetwork(int taille) {
		return new Network(taille);
	}
	
	private static void main_olsr(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
		System.out.println("/*********************************OLSR*******************************/");

		System.out.println("Network");
		n.toStringDeb();
		
		System.out.println("Parcours");
		OLSR olsr = new OLSR(n);
		debitMinOlsr = olsr.OLSR_1(deviceVisiteolsr);
		
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDeb();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);
		
		System.out.println("\n\n\n\n");
	}
	
	private static void main_slor(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
		System.out.println("/*********************************SLOR*******************************/");

		System.out.println("Network");
		n.toStringDeb();
		
		ArrayList<Device> deviceVisitelsor = new ArrayList<Device>();
		int debitMinLSOR = 0;
		System.out.println("Parcours");
		LSOR lsor = new LSOR(n);
		debitMinLSOR = lsor.LSOR_1(deviceVisitelsor);
		
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisitelsor.size(); i++) {
			deviceVisitelsor.get(i).toStringDeb();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinLSOR);
	}
	
//	private static void main_olsrInfinite(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
//		System.out.println("/*********************************OLSRinfinite*******************************/");
//
//		System.out.println("Network");
//		n.toStringDeb();
//		
//		System.out.println("Parcours");
//		DelaiRef olsrinfinite = new DelaiRef(n);
//		debitMinOlsr = olsrinfinite.new_simulation();
//		
//		System.out.println("liens visite");
//		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
//			deviceVisiteolsr.get(i).toStringDeb();
//		}
//		
//		System.out.println("Debit Mini du parcours : " + debitMinOlsr);
//		
//		System.out.println("\n\n\n\n");
//	}
	
	private static void main_olsrK(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr, int k) {
		System.out.println("/********* OLSR K : NOT YET IMPLEMENTED ********/");
	}
	
	private static void main_lsorK(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr, int k) {
		System.out.println("/********* LSOR K : NOT YET IMPLEMENTED ********/");
	}
	
}