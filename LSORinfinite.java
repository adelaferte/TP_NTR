import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author jrogala
 */
public class LSORinfinite {
    
    int m = 200;
    int index;
    Device source;
    Device destination;
    ArrayList<Device> path;
    Network n;
    
    public LSORinfinite(Network n) {
        this.source = n.getFirst();
        this.destination = n.getLast();
        this.n = n;
                
    }
    public int[] new_simulation() {
        int m1 = 20000;
        int m2 = 0;
        int[] s = new int[2];
        Device dencours = source;
        
        path = parcours(dencours);
        while (dencours != destination){
            Link lencours = dencours.getLinksBetweenNodes(path.get(index));
            m1 = Math.min(m1,lencours.getDebitInst());
            m2 += lencours.getDelaiInst();
            dencours = lencours.getVoisin(dencours);
            index++;
        }
    s[0] = m1;
    s[1] = m2;
    return (s);
    }
    ArrayList<Device> Q = new ArrayList<>();
    
    
    private ArrayList<Device> parcours(Device d){
      init();
      
      for (int i=0;i<n.size;i++) {
          for (int j=0;j<n.size;j++){
              Q.add(n.devicegrille[i][j]);
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
          System.out.print("i:"+s.i+" j:"+s.j);
          A.add(s);
          s = pred[indice(s)];
          
      }
      Collections.reverse(A);
      return (A);
      
    }
    
    int[] d;
    Device[] pred;
    
    private int indice(Device x) {
        return (x.i+x.j*n.size);
    }
    
    private void init() {
        d = new int[n.size*n.size];
        pred = new Device[n.size*n.size];
        for (int i=0;i<n.size*n.size;i++) {
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
        if (d[indice(s2)] > (d[indice(s1)] + 1/s1.getLinksBetweenNodes(s2).getDelaiInst())) { // .getDebitMoy à changer ave inst suivant l'algo
            d[indice(s2)] = d[indice(s1)] + 1/s1.getLinksBetweenNodes(s2).getDelaiInst();
            pred[indice(s2)] = s1;
        }
    }
    
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
    }
}
