import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Network n = new Network(3);
		
		int debitMinOlsr = 0;
		ArrayList<Device> deviceVisiteolsr = new ArrayList<Device>();

		System.out
				.println("/***********************************OLSR*********************************/");

		System.out.println("Network");
		System.out.println(n.toStringDebMoy());

		System.out.println("Parcours");
		OLSR olsr = new OLSR(n);
		olsr.OLSR_1(debitMinOlsr, deviceVisiteolsr);

		System.out.println("liens visit�");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			System.out.println(deviceVisiteolsr.get(i).toStringDebMoy());
		}
		
		System.out.println("Debit Mini du parcours : " + debitMinOlsr);

		System.out
				.println("\n\n\n\n/***********************************SLOR*********************************/");

		System.out.println("Network");
		System.out.println(n.toStringDebInst());

		System.out.println("Parcours");
		LSOR lsor = new LSOR(n);
		ArrayList<Device> deviceVisitelsor = lsor.LSOR_1();

		System.out.println("liens visit�");
		for (int i = 0; i < deviceVisitelsor.size(); i++) {
			System.out.println(deviceVisitelsor.get(i).toStringDebInst());
		}
	}
}