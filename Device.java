import java.util.ArrayList;


public class Device {
        public int i;
        public int j;
	private ArrayList<Link> links;

	public Device() {
		links = new ArrayList<Link>();
	}

	public void addLink(Link dev) {
		links.add(dev);
	}

	public ArrayList<Link> getLinks() {
		return this.links;
	}

	public void toStringDebInst (){
		for (int x = 0; x < links.size() ; x ++){
			links.get(x).toStringDebInst();
		}
		System.out.println();
	}

	public void toStringDebMoy (){
		for (int x = 0; x < links.size() ; x ++){
			links.get(x).toStringDebMoy();
		}
		System.out.println();
	}
  public Link getLinksBetweenNodes(Device b){
      for (int x = 0; x<links.size();x++) {
          Device encours = this.getLinks().get(x).getVoisin(this);
          if (encours == b) {
              return this.getLinks().get(x);
          }

      }
      System.out.print("Fatal error\n");
      return null;
  }

}
