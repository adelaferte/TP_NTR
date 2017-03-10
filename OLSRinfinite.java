
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jrogala
 */
public class OLSRinfinite {
    
    int m = 200;
    int index;
    Device source;
    Device destination;
    ArrayList<Device> path;
    Network n;
    
    public OLSRinfinite(Network n) {
        this.source = n.getFirst();
        this.destination = n.getLast();
        this.n = n;
                
    }
    public int new_simulation() {
        m = 20000;
        Random r = new Random();
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
    
    private ArrayList<Device> parcours(Device d){
        int k = 0;
        boolean[][] P = new boolean[n.size][n.size];
        int[][] dist = new int[n.size][n.size];
        Device[][] pred = new Device[n.size][n.size];
        Device deviceencours = d;
        for (int i=0;i<n.size;i++) {
            for (int j=0;j<n.size;j++) {
                dist[i][j] = 99999;
                P[i][j] = false;
            }
        }
        pred[0][0] = deviceencours;
        dist[0][0] = 0;
        while (notAllTrue(P)) {
            int[] deviceindex = choose(P,dist);
            deviceencours = n.devicegrille[deviceindex[0]][deviceindex[1]];
            P[deviceindex[0]][deviceindex[1]] = true;
            for (int linkindex =0;linkindex<deviceencours.getLinks().size();linkindex++) {
                Link link = deviceencours.getLinks().get(linkindex);
                Device voisin = link.getVoisin(deviceencours);
                if (!P[voisin.i][voisin.j]) {
                    if (dist[voisin.i][voisin.j] > dist[deviceencours.i][deviceencours.j] + link.getDebitInst()) {
                        
                        dist[voisin.i][voisin.j] = dist[deviceencours.i][deviceencours.j] + link.getDebitInst();
                        pred[voisin.i][voisin.j] = deviceencours;
                    }
                }
            }
        }
        
        for (Device[] pred1 : pred) {
            for (Device p : pred1) {
                System.out.print ( "Device i:" + p.i + " j:" + p.j + "Distance is: " + dist[p.i][p.j] + "\n");
            }
        }
        ArrayList<Device> s = new ArrayList<>();
        deviceencours = n.getLast();
        
        s.add(deviceencours);
        while (deviceencours.i + deviceencours.j != 0){
            deviceencours = pred[deviceencours.i][deviceencours.j];
            System.out.print("Working on i:" + deviceencours.i + " j:" + deviceencours.j+ "\n");
            s.add(deviceencours);
        }
        java.util.Collections.reverse(s);
        
        return s;
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
