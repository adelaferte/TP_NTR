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
	}
	
	public void toStringDebMoy (){
		for (int x = 0; x < links.size() ; x ++){
			links.get(x).toStringDebMoy();
		}
	}
	
}