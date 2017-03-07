import java.util.ArrayList;


public class OLSR {

	Device source;
	Device destination;

	public OLSR(Network network) {
		this.source = network.getFirst();
		this.destination = network.getLast();
	}
	
	public void OLSR_1(int debitMin, ArrayList<Device> dejaVisite) {

		// liste des liens associe au noeud en cours
		ArrayList<Link> LinkEnCours;
		
		Device deviceEnCours = source;
		dejaVisite.add(deviceEnCours);


		while (!dejaVisite.contains(destination)) {
			LinkEnCours = deviceEnCours.getLinks();
			int debitInt = 0;
			int deviceHD = 0;
			
			for (int j = 0; j < LinkEnCours.size(); j++) {
				
				// si debit du liens superieurs && le noeud de l'autre cote du lien n'a pas ete visite alors
				if (debitInt < LinkEnCours.get(j).getDebitMoy()   &&  !dejaVisite.contains(LinkEnCours.get(j).getVoisin(deviceEnCours))) {
						deviceHD = j;
						debitInt = LinkEnCours.get(j).getDebitMoy();
				}
			}
			if(debitMin>LinkEnCours.get(deviceHD).getDebitMoy()|| debitMin == 0)
				debitMin = LinkEnCours.get(deviceHD).getDebitMoy();
			System.out.println("DebMin : "+ debitMin);
			deviceEnCours = LinkEnCours.get(deviceHD).getVoisin(deviceEnCours);
			deviceEnCours.toStringDebMoy();
			dejaVisite.add(deviceEnCours);
		}
	}

	/**
	 * GETTERS and SETTERS
	 */


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

}
