import java.util.TreeSet;


public class Device {
	private String name;
	private TreeSet<Link> links;
	
	public Device(String name) {
		this.name = name;
	}
	
	public void addLink(Link dev) {
		links.add(dev);
	}
	
	public String getName() {
		return this.name;
	}
	
	public TreeSet<Link> getLinks() {
		return this.links;
	}
	
	public Link getMaxLink() {
		return this.links.first();
	}
	
	public Link getMinLink() {
		return this.links.last();
	}
	
}

