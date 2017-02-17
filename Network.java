public class Network {
	
    public int size = 0;
    public int debmin = 0;
    public int debmax = 100;
    Link num_link;
    public Device[][] grille;
    
    
    
    public Network(int n){
        size = n;
        this.grille = new Device[size][size];
        
        // creation du tableau de device
        for ( int i=0 ; i<size ; i++ ){
                for (int j=0 ; j<size ; j++ ){
                    this.grille[i][j] = new Device();
                }
        }
        
        // création des liens
        for (int i=0;i<size;i+=2){
                for (int j=0;j<size;j+=2){
                        if (is_valid(i+1,j,size) & is_valid(i,j,size)){
                            num_link = new Link(this.grille[i][j],this.grille[i+1][j],debmin,debmax);
                        }
                        if (is_valid(i-1,j,size) & is_valid(i,j,size)){
                            num_link = new Link(this.grille[i][j],this.grille[i-1][j],debmin,debmax);
                        }
                        if (is_valid(i,j+1,size) & is_valid(i,j,size)){
                            num_link = new Link(this.grille[i][j],this.grille[i][j+1],debmin,debmax);
                        }
                        if (is_valid(i,j-1,size) & is_valid(i,j,size)){
                            num_link = new Link(this.grille[i][j],this.grille[i][j-1],debmin,debmax);
                        }
                }
            }
            System.out.println("Network created.");
        }

    private boolean is_valid(int i, int j, int size) {
        return(i>=0 & j >= 0 & i < size & j < size);
    }
}
