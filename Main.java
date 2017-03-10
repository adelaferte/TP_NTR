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
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDeb();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);

		System.out.println("\n\n");		
		System.out.println("/*********************************LSOR*******************************/   debit instantané des liens");

		ArrayList<Device> deviceVisitelsor = new ArrayList<Device>();
		int debitMinLSOR =0;
		System.out.println("Parcours");
		LSOR lsor = new LSOR(n);
		debitMinLSOR = lsor.LSOR_1(deviceVisitelsor);

		System.out.println("\n");
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisitelsor.size(); i++) {
			deviceVisitelsor.get(i).toStringDeb();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinLSOR);
		
		System.out.println("\n\n");
        System.out.println("/*********************************OLSRinfinite*******************************/");

		System.out.println("Parcours");
		OLSRinfinite olsrinfinite = new OLSRinfinite(n);
		debitMinOlsr = olsr.OLSR_1(deviceVisiteolsr);

		System.out.println("\n");
		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			deviceVisiteolsr.get(i).toStringDebMoy();
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);
                 
    }
}