import java.util.ArrayList;


public class Device {
    public int i;
    public int j;
    
    public int waitlist = 0;
    public int futurwaitlist = 0;
    
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
