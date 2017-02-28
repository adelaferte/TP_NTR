
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jrogala
 */
public class Random_Network {
    
    int m = 200;
    int i;
    Device source;
    Device destination;
    
    public Random_Network(Device source, Device destination) {
        this.source = source;
        this.destination = destination;
                
    }
    public int new_simulation() {
        m = 20000;
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
