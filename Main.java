import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
            //int EXPSIZE = 1000000;
            Network n = new Network(3);
            
            System.out.println("Network");
            System.out.println(n.ToString());
            
            System.out.println("Parcours");
            OLSR olsr = new OLSR(n);
            ArrayList<Device> deviceVisite = olsr.OLSR_1();

            
            System.out.println("liens visit�");
            for(int i = 0; i < deviceVisite.size(); i++){
            	System.out.println(deviceVisite.get(i).ToString());
            }
            
            /*Random_Network rn = new Random_Network(n.getFirst(),n.getLast());
            int EXPSIZE = 1000000;
            Network n = new Network(13);
            Random_Network rn = new Random_Network(n,n.getFirst(),n.getLast());
            int m = 0;
            for (int i=0;i<EXPSIZE;i++){
                n.reset();
                m += rn.new_simulation();
                if (i%1000 == 0) {
                    System.out.println(i);
                    }
            }
            System.out.println(m/EXPSIZE);*/
    }
}