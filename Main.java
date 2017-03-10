import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Paramètres
		int tailleNetwork = 3;
		Network n = createNetwork(tailleNetwork);
		
		while(true) {
			
			System.out.println(" ___________________________________________________");
			System.out.println(" |                                                  |");
			System.out.println(" | Bienvenue !                                      |");
			System.out.println(" | Choisissez votre algorithme de prédilection :    |");
			System.out.println(" | ___ 1/ OLSR pas à pas                            |");
			System.out.println(" | ___ 2/ SLOR pas à pas                            |");
			System.out.println(" | ___ 3/ OLSR infinite                             |");
			System.out.println(" | ___ 4/ Reset network                             |");
			System.out.println(" | ___ 5/ Changer taille réseau                     |");
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
			case 3:main_olsrInfinite(n, debitMinOlsr, deviceVisiteolsr);
				break;
			case 4:createNetwork(tailleNetwork);
				break;
			case 5:
				System.out.print("Nouvelle taille ? ");
				tailleNetwork = sc.nextInt();
				createNetwork(tailleNetwork);
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
	
	private static void main_olsrInfinite(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
		System.out.println("/*********************************OLSRinfinite*******************************/");

		System.out.println("Network");
		n.toStringDeb();
		
		System.out.println("Parcours");
		DelaiRef olsrinfinite = new DelaiRef(n);
		debitMinOlsr = olsrinfinite.new_simulation();
		
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDeb();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);
		
		System.out.println("\n\n\n\n");
	}
	
}