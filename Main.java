import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Network n = new Network(3);
		
		int debitMinOlsr = 0;
		ArrayList<Device> deviceVisiteolsr = new ArrayList<Device>();

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
		System.out.println("/*********************************SLOR*******************************/");

		System.out.println("Network");
		n.toStringDebInst();

		ArrayList<Device> deviceVisitelsor = new ArrayList<Device>();
		int debitMinLSOR =0;
		System.out.println("Parcours");
		LSOR lsor = new LSOR(n);
		debitMinLSOR = lsor.LSOR_1(deviceVisitelsor);

		System.out.println("liens visite");
		for (int i = 0; i < deviceVisitelsor.size(); i++) {
			deviceVisitelsor.get(i).toStringDebInst();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinLSOR);

                System.out.println("/*********************************OLSRinfinite*******************************/");

		System.out.println("Network");
		n.toStringDebMoy();

		System.out.println("Parcours");
		OLSRinfinite olsrinfinite = new OLSRinfinite(n);
		debitMinOlsr = olsr.OLSR_1(deviceVisiteolsr);

		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDebMoy();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);

		System.out.println("\n\n\n\n");
                 
    }
}