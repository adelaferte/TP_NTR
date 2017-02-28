public class Main {
    public static void main(String[] args) {
            int EXPSIZE = 1000000;
            Network n = new Network(8);
            Random_Network rn = new Random_Network(n.getFirst(),n.getLast());
            int m = 0;
            for (int i=0;i<EXPSIZE;i++){
                n.reset();
                m += rn.new_simulation();
            }
            System.out.println(m/EXPSIZE);
    }
}
