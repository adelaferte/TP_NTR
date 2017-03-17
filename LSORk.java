import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author jrogala
 */
public class LSORk {
    int k;
    int m = 200;
    int index;
    Device source;
    Device destination;
    ArrayList<Device> path;
    Network n;

    public LSORk(Network n,int k) { // k= 0: OLSR k != 0:LSOR
        this.source = n.getFirst();
        this.destination = n.getLast();
        this.n = n;
        this.k = k;

    }
    public int[] new_simulation() {
        int m1 = 20000;
        int m2 = 0;
        int[] s = new int[2];
        Device dencours = source;

        path = null;
        while (dencours != destination){
            Link lencours = dencours.getLinksBetweenNodes(path.get(index));
            m1 = Math.min(m1,lencours.getDebitInst());
            dencours = lencours.getVoisin(dencours);
            index++;
        }
    s[0] = m1;
    s[1] = m2;
    return (s);
    }

    private int indice(int i,int j){
        return (i+j*n.size);
    }

    private int poids(Link l,Device u,Device origin,int k){ // Return inst if dist(origin,l) < k else moy
        if (Math.abs(u.i-origin.i)+Math.abs(u.j-origin.j) < k){
          return l.getDebitInst();
        }
        else {
            return l.getDebitMoy();
        }
    }
    
    /*private boolean isThere(Device entry,Link lentry,Device devicetofind,ArrayList<Link> links){//check if devicetofind is entry->l way (without going back to entry)
        Device dencours = lentry.getVoisin(entry);
        Boolean flag = false;
        for (Link l : links) {
            Device dcible = null;
            Link lcible = null;
            if (l.getA() == dencours || l.getB() == dencours){
                if (l.getA() != entry && l.getB() != entry){
                    dcible = l.getVoisin(dencours);
                    lcible = l;
                }
            }
            if (dcible == null) {
                return false;
            }
            else if (dcible == devicetofind) {
                flag = true;
            }
            else {
            flag = isThere(dcible,lcible,devicetofind,links);
            }
        }
        return flag;
    }

    private Device getNext(Device s,Device e,Device[] pred){
        Device dencours = s;
        ArrayList <Link> links = new ArrayList<>();
        for (int i=0;i<n.size;i++){
           for (int j=0;j<n.size;j++){
               if (pred[indice(i,j)] != null){
                   Device d = n.devicegrille[i][j];
                   Device dprim = pred[indice(i,j)];
                   Link l = d.getLinksBetweenNodes(dprim);
                   links.add(l);    
               }
           }
        }
        for (Link l : links) {
                if (l.getA() == dencours || l.getB() == dencours){
                    
                }
            }
        while (dencours != e)
            for (Link l : links) {
                if (l.getA() == dencours || l.getB() == dencours){
                    
                }
            }
        
    }*/

    public Device getNext(Device d){
        int[] cout = new int[n.size*n.size];
        Device[] pred = new Device[n.size*n.size];
        Device t;
        ArrayList<Device> F = new ArrayList<>();
        for(int i = 0;i<n.size;i++){
            for (int j=0;j<n.size;j++){
                cout[indice(i,j)] = 0;
                pred[indice(i,j)] = null;
                F.add(n.devicegrille[i][j]);
            }
        }
        cout[indice(d.i,d.j)] = 0;
        while(!F.isEmpty()){
            Collections.sort(F, (Device o1, Device o2) -> cout[indice(o1.i,o1.j)] - cout[indice(o2.i,o2.j)]);
            t = F.get(0);
            F.remove(0);
            for (int x=0;x < t.getLinks().size();x++) {
                Link l = t.getLinks().get(x);
                Device u = l.getVoisin(t);
                    if (cout[indice(u.i,u.j)] < poids(l,u,d,this.k)){
                        cout[indice(u.i,u.j)] = poids(l,u,d,this.k);
                        pred[indice(u.i,u.j)] = t;
                    }
            }
        }
        Device dencours = n.getLast();
        Device dprec = pred[indice(dencours.i,dencours.j)];
        while(dprec != d){
            dencours = dprec;
            dprec = pred[indice(dprec.i,dprec.j)];
        }
        return dencours;

    }
}
