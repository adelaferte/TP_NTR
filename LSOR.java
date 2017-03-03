import java.util.ArrayList;


public class LSOR {
	Device source;
	Device destination;

	public LSOR(Network network) {
		this.source = network.getFirst();
		this.destination = network.getLast();
	}

	public ArrayList<Device> LSOR_1() {

		
		// liste des noeud deja visit� 
		ArrayList<Device> dejaVisite = new ArrayList<Device>();
		// liste des liens associ� au noeud en cours
		ArrayList<Link> LinkEnCours;
		
		Device deviceEnCours = source;
		dejaVisite.add(deviceEnCours);


		while (!dejaVisite.contains(destination)) {
			LinkEnCours = deviceEnCours.getLinks();
			int debitInt = 0;
			int deviceHD = 0;
			
			for (int j = 0; j < LinkEnCours.size(); j++) {
				
				// si debit du liens sup�rieurs && le noeud de l'autre cot� du lien n'a pas �t� visit� alors
				if (debitInt < LinkEnCours.get(j).getDebitInst()   &&  !dejaVisite.contains(LinkEnCours.get(j).getVoisin(deviceEnCours))) {
						deviceHD = j;
						debitInt = LinkEnCours.get(j).getDebitInst();
				}
			}
			deviceEnCours = LinkEnCours.get(deviceHD).getVoisin(deviceEnCours);
			System.out.println(deviceEnCours.toStringDebInst());
			dejaVisite.add(deviceEnCours);
		}
		return dejaVisite;
	}

	/**
	 * GETTERS and SETTERS
	 */

	
}
