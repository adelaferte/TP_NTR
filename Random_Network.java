import java.util.Random;

/**
 * @author jrogala, qdubois, adelaferte, jgachelin
 */

public class Random_Network {
    
    private int m = 200;
    private Device source;
    private Device destination;
    
    public Random_Network(Network n,Device source, Device destination) {
        this.source = source;
        this.destination = destination;      
    }
    
    public int new_simulation() {
        this.m = 20000;
        Random r = new Random();
        Device dencours = source;
        while (dencours != destination){
            Link lencours = dencours.getLinks().get(r.nextInt(dencours.getLinks().size()));
            m = Math.min(m,lencours.getDebitInst());
            dencours = lencours.getVoisin(dencours);
        }
        return m;
    }
}
