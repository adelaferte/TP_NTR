import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		while(true) {
			System.out.println(" ___________________________________________________");
			System.out.println(" |                                                  |");
			System.out.println(" | Bienvenue !                                      |");
			System.out.println(" | Choisissez votre algorithme de prédilection :    |");
			System.out.println(" | ___ 1/ OLSR pas à pas                            |");
			System.out.println(" | ___ 2/ SLOR pas à pas                            |");
			System.out.println(" | ___ 3/ OLSR infinite                             |");
			System.out.println(" |__________________________________________________|");
			
			System.out.print("\nVotre choix (1, 2 ou 3) : ");
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			
			// Paramètres
			Network n = new Network(3);
			int debitMinOlsr = 0;
			ArrayList<Device> deviceVisiteolsr = new ArrayList<Device>();

			switch (choix) {
			case 1:main_olsr(n, debitMinOlsr, deviceVisiteolsr);
				break;
			case 2:main_slor(n, debitMinOlsr, deviceVisiteolsr);
				break;
			case 3:main_olsrInfinite(n, debitMinOlsr, deviceVisiteolsr);
				break;
			default:;
			}
		}
	}
	
	private static void main_olsr(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
		System.out.println("/*********************************OLSR*******************************/");

		System.out.println("Network");
		n.toStringDebMoy();
		
		System.out.println("Parcours");
		OLSR olsr = new OLSR(n);
		debitMinOlsr = olsr.OLSR_1(deviceVisiteolsr);
		
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDebMoy();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);
		
		System.out.println("\n\n\n\n");
	}
	
	private static void main_slor(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
		System.out.println("/*********************************SLOR*******************************/");

		System.out.println("Network");
		n.toStringDebInst();
		
		ArrayList<Device> deviceVisitelsor = new ArrayList<Device>();
		int debitMinLSOR = 0;
		System.out.println("Parcours");
		LSOR lsor = new LSOR(n);
		debitMinLSOR = lsor.LSOR_1(deviceVisitelsor);
		
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisitelsor.size(); i++) {
			deviceVisitelsor.get(i).toStringDebInst();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinLSOR);
	}
	
	private static void main_olsrInfinite(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
		System.out.println("/*********************************OLSRinfinite*******************************/");

		System.out.println("Network");
		n.toStringDebMoy();
		
		System.out.println("Parcours");
		OLSRinfinite olsrinfinite = new OLSRinfinite(n);
		debitMinOlsr = olsrinfinite.new_simulation();
		
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDebMoy();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);
		
		System.out.println("\n\n\n\n");
	}
	
}