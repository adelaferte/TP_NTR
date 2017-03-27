import java.util.ArrayList;
import java.util.Collections;

/**
 * @author jrogala, qdubois, adelaferte, jgachelin
 */

public class OLSRinfinite {
    
    private int m = 200;
    private int index;
    private Device source;
    private Device destination;
    private ArrayList<Device> path;
    private Network n;
    
    public OLSRinfinite(Network n) {
        this.source = n.getFirst();
        this.destination = n.getLast();
        this.n = n;
                
    }
    public int new_simulation() {
        m = 20000;
        Device dencours = source;
        
        path = parcours(dencours);
        while (dencours != destination){
            Link lencours = dencours.getLinksBetweenNodes(path.get(index));
            m = Math.min(m,lencours.getDebitInst());
            dencours = lencours.getVoisin(dencours);
            index++;
        }
    return m;
    }
    ArrayList<Device> Q = new ArrayList<>();
    
    
    private ArrayList<Device> parcours(Device d){
      init();
      
      for (int i=0;i<n.getSize();i++) {
          for (int j=0;j<n.getSize();j++){
              Q.add(n.getDeviceGrille()[i][j]);
          }
      }
      Device s1 = null;
      while (!Q.isEmpty()) {
          s1 = trouve_min();
          Q.remove(s1);
          for (int i=0;i<s1.getLinks().size();i++) {
              maj_distance(s1,s1.getLinks().get(i).getVoisin(s1));
          }
      }
      
      ArrayList<Device> A = new ArrayList<>();
      
      Device s = n.getLast();
      while (s != n.getFirst()) {
          System.out.print("i:"+s.getI()+" j:"+s.getJ());
          A.add(s);
          s = pred[indice(s)];
          
      }
      Collections.reverse(A);
      return (A);
      
    }
    
    int[] d;
    Device[] pred;
    
    private int indice(Device x) {
        return (x.getI()+x.getJ()*n.getSize());
    }
    
    private void init() {
        d = new int[n.getSize()*n.getSize()];
        pred = new Device[n.getSize()*n.getSize()];
        for (int i=0;i<n.getSize()*n.getSize();i++) {
            d[i] = 99999;
            pred[i] = null;
        }
        d[indice(n.getFirst())] = 0;   
    }
    
    private Device trouve_min() {
        int mini = 99999;
        Device sommet = null;
        for (int i=0;i<Q.size();i++) {
            if (d[ indice(Q.get(i)) ] < mini) {
                mini = d[ indice(Q.get(i)) ];
                sommet = Q.get(i);
            }
        }
        return sommet;
    }
    
    private void maj_distance(Device s1,Device s2) {
        if (d[indice(s2)] > (d[indice(s1)] + 1/s1.getLinksBetweenNodes(s2).getDebitMoy())) { // .getDebitMoy à changer ave inst suivant l'algo
            d[indice(s2)] = d[indice(s1)] + 1/s1.getLinksBetweenNodes(s2).getDebitMoy();
            pred[indice(s2)] = s1;
        }
    }
    
    /* A supprimer ?
      private boolean notAllTrue(boolean[][] T) {
        boolean flag = false;
        for (boolean[] T1 : T) {
            for (boolean val : T1) {
                flag = flag || !val;
            }
        }
        return flag;
    }
    private int[] choose(boolean[][] P,int[][] dist){
        int[] choosenOne = {-1,-1};
        int valencours = 999999999;
        for (int i = 0;i<P.length;i++){
            for (int j = 0;j<P[i].length;j++){
                if (!P[i][j] && dist[i][j] < valencours) {
                    choosenOne[0] = i;
                    choosenOne[1] = j;
                    valencours = dist[i][j];
                }
            }
            
        }
        return choosenOne;
    }*/
}
