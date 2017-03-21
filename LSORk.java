import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
        return (i*n.size+j);
    }

    private int poids(Link l,Device u,Device origin,int k){ // Return inst if dist(origin,l) < k else moy
        if (Math.abs(u.i-origin.i)+Math.abs(u.j-origin.j) < k){
          return l.getDebitInst();
        }
        else {
            return l.getDebitMoy();
        }
    }
    
    private static boolean isThere(Device entry,Link lentry,Device devicetofind,ArrayList<Link> links){//check if devicetofind is entry->l way (without going back to entry)
        Device dencours = lentry.getVoisin(entry);
        boolean flag = false;
        if (dencours == devicetofind) {
            return true;
        }
        for (Link l : links) {
            Link lcible;
            if (l.getA() == dencours || l.getB() == dencours){
                if (l.getA() != entry && l.getB() != entry){
                    lcible = l;
                    flag = flag || isThere(dencours,lcible,devicetofind,links);
                }
            }
        }
        return flag;
    }

    private Device getWork(Device s,Device e,Device[] pred){
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
        ArrayList l2 = new ArrayList();

        Iterator iterator = links.iterator();

        while (iterator.hasNext())
        {
            Link o = (Link) iterator.next();
            if(!l2.contains(o)) l2.add(o);
        }
        links = l2;
        for (Link l : links) {
            if (l.getA() == dencours || l.getB() == dencours){
                if (isThere(dencours,l,e,links)){
                    return l.getVoisin(dencours);
                }
            }
        }
        return null;
    }

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
        cout[indice(d.i,d.j)] = 999999;
        while(!F.isEmpty()){
            Collections.sort(F, (Device o1, Device o2) ->   -(cout[indice(o1.i,o1.j)] - cout[indice(o2.i,o2.j)]));            
            t = F.get(0);
            F.remove(0);
            for (Link l : t.getLinks()) {
                Device u = l.getVoisin(t);
                    if (cout[indice(u.i,u.j)] < poids(l,u,d,this.k) && F.contains(u)){
                        cout[indice(u.i,u.j)] = poids(l,u,d,this.k);
                        pred[indice(u.i,u.j)] = t;
                    }
            }
        }
        
              
        Device dencours = d;
        Device dsuiv = getWork(dencours,n.getLast(),pred);
        return dsuiv;


    }
}
