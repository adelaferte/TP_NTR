import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author jrogala
 */
public class OLSR {

	int m = 200;
	int i;
	Device source;
	Device destination;
	int[] path;

	public OLSR(Network network) {
		this.source = network.getFirst();
		this.destination = network.getLast();
		this.path = null;
	}

	public int new_simulation() {
		m = 20000;
		Random r = new Random();
		Device dencours = source;
		while (dencours != destination) {
			Link lencours = dencours.getLinks().get(path[i]);
			m = Math.min(m, lencours.getDebitInst());
			dencours = lencours.getVoisin(dencours);
			i++;
		}
		return m;
	}

	/*
	 * private int parcours(Device d){
	 * 
	 * private int[] aux(Device d){
	 * 
	 * ArrayList<Link> links = d.getLinks();
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	public ArrayList<Device> OLSR_1(int debitMin) {

		int debitInt = 0;
		int deviceHD;
		ArrayList<Device> dejaVisite = new ArrayList<Device>();
		dejaVisite.add(source);
		int i=0;

		// creation et initialisation d'un device "pointeur"
		Device deviceEnCours = source;

		// initialisation des liens du deviceEnCours avec ceux de la sources
		ArrayList<Link> LinkEnCours = source.getLinks();

		while (!dejaVisite.contains(destination)) {
			// test valeur des débits de chaque lien
			debitInt = LinkEnCours.get(0).getDebitMoy();
			deviceHD = 0;
			for (int j = 1; j < LinkEnCours.size(); j++) {
				if (debitInt < LinkEnCours.get(j).getDebitMoy()) {
					if (!dejaVisite.contains(LinkEnCours.get(j).getVoisin(
							deviceEnCours))) {
						deviceHD = j;
						debitInt = LinkEnCours.get(0).getDebitMoy();
					}
				}
			}
			deviceEnCours = LinkEnCours.get(deviceHD).getVoisin(deviceEnCours);
			dejaVisite.add(deviceEnCours);
			System.out.println(dejaVisite.get(i).ToString());
			i++;
			if (debitMin > debitInt)
				debitMin = debitInt;
		}
		return dejaVisite;
	}

	/**
	 * GETTERS and SETTERS
	 */

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public Device getSource() {
		return source;
	}

	public void setSource(Device source) {
		this.source = source;
	}

	public Device getDestination() {
		return destination;
	}

	public void setDestination(Device destination) {
		this.destination = destination;
	}

	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}

}
