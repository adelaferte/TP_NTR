import java.util.ArrayList;

/**
 * @author jrogala, qdubois, adelaferte, jgachelin
 */

public class LSOR {
	private Device source;
	private Device destination;

	public LSOR(Network network) {
		this.source = network.getFirst();
		this.destination = network.getLast();
	}

	public int LSOR_1(ArrayList<Device>  dejaVisite) {
		int debitMin = 0;

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
				if (debitInt < LinkEnCours.get(j).getDebitInst()   &&  !dejaVisite.contains(LinkEnCours.get(j).getVoisin(deviceEnCours))) {
					deviceHD = j;
					debitInt = LinkEnCours.get(j).getDebitInst();
				}
			}
			if(debitMin>LinkEnCours.get(deviceHD).getDebitInst()|| debitMin == 0)
				debitMin = LinkEnCours.get(deviceHD).getDebitInst();
			System.out.println("DebMin : "+ debitMin);

			deviceEnCours = LinkEnCours.get(deviceHD).getVoisin(deviceEnCours);
			deviceEnCours.toStringDeb();
			dejaVisite.add(deviceEnCours);
		}
		return debitMin;
	}

}
