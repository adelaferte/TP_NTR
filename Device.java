import java.util.ArrayList;


public class Device {
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
	
	public String ToString (){
		String str = "";
		for (int x = 0; x < links.size() ; x ++){
			str += links.get(x).ToString();
		}
		return str;
	}
	
}