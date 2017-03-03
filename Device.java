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
	
	public String toStringDebInst (){
		String str = "";
		for (int x = 0; x < links.size() ; x ++){
			str += links.get(x).toStringDebInst();
		}
		return str;
	}
	
	public String toStringDebMoy (){
		String str = "";
		for (int x = 0; x < links.size() ; x ++){
			str += links.get(x).toStringDebMoy();
		}
		return str;
	}
	
}