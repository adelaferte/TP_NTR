import java.util.ArrayList;


public class Device {
	
	private int i;
	private int j;
	
	private int waitlist = 0;
	private int futurwaitlist = 0;
	
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
	
	public int getI() {
		return this.i;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	public void setJ(int j) {
		this.j = j;
	}
	
	public int getWaitlist() {
		return this.waitlist;
	}
	
	public void setWaitlist(int waitlist) {
		this.waitlist = waitlist;
	}
	
	public int getFuturWaitlist() {
		return this.futurwaitlist;
	}
	
	public void setFuturWaitlist(int waitlist) {
		this.futurwaitlist = waitlist;
	}

	@Override
    public String toString() { 
    	return "i"+this.i+"j"+this.j;
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
            Device encours = this.getLinks().get(x).getVoisin(this);
            if (encours == b) {
                return this.getLinks().get(x);
            }
        }
        System.out.print("Fatal error\n");
        return null;
    }
  
  
    public void SendData(LSORk Routage){
        if (this.waitlist > 0){
            Device Next = Routage.getNext(this);
            if (Next != null){
              int debitNext = getLinksBetweenNodes(Next).getDebitInst();
              int DataEnvoyerReel = Math.min(debitNext,waitlist);
              futurwaitlist-= DataEnvoyerReel;
              Next.futurwaitlist+= DataEnvoyerReel;
            }
        }
    }


}
