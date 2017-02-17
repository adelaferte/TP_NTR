import java.util.ArrayList;


public class Device {
	private String name;
	private ArrayList<Link> links;
	
	public Device() {
		links = new ArrayList<Link>();
	}
	
	public void addLink(Link dev) {
		links.add(dev);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Link> getLinks() {
		return this.links;
	}
	

	
}

