import java.io.InputStream;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		Network n = new Network(3);
		n.toStringDeb();

		int debitMinOlsr = 0;
		ArrayList<Device> deviceVisiteolsr = new ArrayList<Device>();

		System.out.println("\n\n");
		
		System.out.println("/*********************************OLSR*******************************/   Debit moyen des liens");

		System.out.println("Parcours");
		OLSR olsr = new OLSR(n);
		debitMinOlsr = olsr.OLSR_1(deviceVisiteolsr);

		System.out.println("\n");
=======
			switch (choix) {
			case 1:main_olsr(n, debitMinOlsr, deviceVisiteolsr);
				break;
			case 2:main_slor(n, debitMinOlsr, deviceVisiteolsr);
				break;
			case 3:main_olsrInfinite(n, debitMinOlsr, deviceVisiteolsr);
				break;
			case 4:createNetwork(3);
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
		n.toStringDebMoy();
		
		System.out.println("Parcours");
		OLSR olsr = new OLSR(n);
		debitMinOlsr = olsr.OLSR_1(deviceVisiteolsr);
		
>>>>>>> 4b3bb49279282708db82a8b236ed0f74b39096d7
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDeb();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);
<<<<<<< HEAD

		System.out.println("\n\n");		
		System.out.println("/*********************************LSOR*******************************/   debit instantané des liens");

=======
		
		System.out.println("\n\n\n\n");
	}
	
	private static void main_slor(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
		System.out.println("/*********************************SLOR*******************************/");

		System.out.println("Network");
		n.toStringDebInst();
		
>>>>>>> 4b3bb49279282708db82a8b236ed0f74b39096d7
		ArrayList<Device> deviceVisitelsor = new ArrayList<Device>();
		int debitMinLSOR = 0;
		System.out.println("Parcours");
		LSOR lsor = new LSOR(n);
		debitMinLSOR = lsor.LSOR_1(deviceVisitelsor);
<<<<<<< HEAD

		System.out.println("\n");
=======
		
>>>>>>> 4b3bb49279282708db82a8b236ed0f74b39096d7
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisitelsor.size(); i++) {
			deviceVisitelsor.get(i).toStringDeb();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinLSOR);
<<<<<<< HEAD
		
		System.out.println("\n\n");
        System.out.println("/*********************************OLSRinfinite*******************************/");

		System.out.println("Parcours");
		OLSRinfinite olsrinfinite = new OLSRinfinite(n);
		debitMinOlsr = olsr.OLSR_1(deviceVisiteolsr);

		System.out.println("\n");
=======
	}
	
	private static void main_olsrInfinite(Network n, int debitMinOlsr, ArrayList<Device> deviceVisiteolsr) {
		System.out.println("/*********************************OLSRinfinite*******************************/");

		System.out.println("Network");
		n.toStringDebMoy();
		
		System.out.println("Parcours");
		OLSRinfinite olsrinfinite = new OLSRinfinite(n);
		debitMinOlsr = olsrinfinite.new_simulation();
		
>>>>>>> 4b3bb49279282708db82a8b236ed0f74b39096d7
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDebMoy();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);
<<<<<<< HEAD
                 
    }
=======
		
		System.out.println("\n\n\n\n");
	}
	
>>>>>>> 4b3bb49279282708db82a8b236ed0f74b39096d7
}