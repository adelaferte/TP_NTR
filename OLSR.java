import java.util.Random;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jrogala
 */
public class OLSR {
    
    int m = 200;
    int i;
    Device source;
    Device destination;
    int[] path;
    
    public OLSR(Device source, Device destination) {
        this.source = source;
        this.destination = destination;
                
    }
    public int new_simulation() {
        m = 20000;
        Random r = new Random();
        Device dencours = source;
        while (dencours != destination){
            Link lencours = dencours.getLinks().get(path[i]);
            m = Math.min(m,lencours.getDebitInst());
            dencours = lencours.getVoisin(dencours);
            i++;
        }
    return m;
    }
    
    private int parcours(Device d){
        
        private int[] aux(Device d){
            
            ArrayList<Link> links = d.getLinks();
        
        }
        
        
    }
}
