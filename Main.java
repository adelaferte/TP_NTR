import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Network n = new Network(3);

		System.out.println("/*********************************OLSR*******************************/");

		System.out.println("Network");
		n.toStringDebMoy();

		System.out.println("Parcours");
		OLSR olsr = new OLSR(n);
		ArrayList<Device> deviceVisiteolsr = olsr.OLSR_1();

		System.out.println("liens visite");
		for (int i = 0; i < deviceVisiteolsr.size(); i++) {
			System.out.println(deviceVisiteolsr.get(i).toStringDebMoy());
		}

		System.out.println("\n\n\n\n");
		System.out.println("/*********************************SLOR*******************************/");

		System.out.println("Network");
		n.toStringDebInst();

		System.out.println("Parcours");
		LSOR lsor = new LSOR(n);
		ArrayList<Device> deviceVisitelsor = lsor.LSOR_1();

		System.out.println("liens visite");
		for (int i = 0; i < deviceVisitelsor.size(); i++) {
			System.out.println(deviceVisitelsor.get(i).toStringDebInst());
		}
	}
}