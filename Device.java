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
		System.out.print("x:"+i+"  y:"+j+"     ");
		for (int x = 0; x < links.size() ; x ++){
			links.get(x).toStringDebInst();
		}
		System.out.println();
	}
	
	public void toStringDeb (){
		System.out.print("x:"+i+"  y:"+j+"     ");
		for (int x = 0; x < links.size() ; x ++){
			links.get(x).toStringDeb();
		}
		System.out.println();
	}


	public void toStringDebMoy (){
		System.out.print("x:"+i+"  y:"+j+"     ");
		for (int x = 0; x < links.size() ; x ++){			
			links.get(x).toStringDebMoy();
		}
		System.out.println();
	}
  public Link getLinksBetweenNodes(Device b){
      for (int x = 0; x<links.size();x++) {
          Device encours = b.getLinks().get(x).getVoisin(b);
          if (encours == b) {
              return b.getLinks().get(x);
          }

      }
      return null;
  }

}
