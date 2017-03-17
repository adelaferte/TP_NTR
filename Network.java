public class Network {
	
    public int size = 0;
    Link num_link;
    public Device[][] devicegrille;
    public Link[] linkgrille;
    
    
    
    public Network(int n){
        size = n;
        this.devicegrille = new Device[size][size];
        this.linkgrille = new Link[size*size*4];
        
        // creation du tableau de device
        for ( int i=0 ; i<size ; i++ ){
                for (int j=0 ; j<size ; j++ ){
                    Device d = new Device();
                    this.devicegrille[i][j] = d;
                    d.i = i;
                    d.j = j;
               
                }
        }
        int x = 0;
        // creation des liens
        for (int i=0;i<size;i++){
                for (int j=0;j<size;j++){
                	if (  ((i%2)== 0  && (j%2)== 0) || ( (i%2)!=0 && (j%2)!=0 ) ){ 
                        if (is_valid(i+1,j,size) & is_valid(i,j,size)){
                            num_link = new Link(this.devicegrille[i][j],this.devicegrille[i+1][j]);
                            linkgrille[x] = num_link;
                            x++;
                                
                        }
                        if (is_valid(i-1,j,size) & is_valid(i,j,size)){
                            num_link = new Link(this.devicegrille[i][j],this.devicegrille[i-1][j]);
                            linkgrille[x] = num_link;
                            x++;
                        }
                        if (is_valid(i,j+1,size) & is_valid(i,j,size)){
                            num_link = new Link(this.devicegrille[i][j],this.devicegrille[i][j+1]);
                            linkgrille[x] = num_link;
                            x++;
                        }
                        if (is_valid(i,j-1,size) & is_valid(i,j,size)){
                            num_link = new Link(this.devicegrille[i][j],this.devicegrille[i][j-1]);
                            linkgrille[x] = num_link;
                            x++;
                        }
                	}
                }
            }
            System.out.println("\nCreation du reseau..... (taille:" + n + ") OK\n");
        }

    private boolean is_valid(int i, int j, int size) {
        return(i>=0 & j >= 0 & i < size & j < size);
    }
    
    public void toStringDebInst (){
    	System.out.println("# Affichage des debits instantanes");
    	for ( int x  = 0 ; x < size ; x ++ ){
    		for (int y = 0 ; y < size ; y ++ ){
    			System.out.println("x:"+x+"  y:"+y+"     ");
    			devicegrille[x][y].toStringDebInst();
    		}
    	}
    }
    
    public void toStringDebMoy (){
    	for ( int x  = 0 ; x < size ; x ++ ){
    		for (int y = 0 ; y < size ; y ++ ){
    			System.out.print("x:"+x+"  y:"+y+"     ");
    			devicegrille[x][y].toStringDebMoy();
    		}
    	}
    }
    
    
    public void toStringDeb (){
    	for ( int x  = 0 ; x < size ; x ++ ){
    		for (int y = 0 ; y < size ; y ++ ){
    			devicegrille[x][y].toStringDeb();
    		}
    	}
    }
    public Device getFirst(){
        return this.devicegrille[0][0];
    }
    public Device getLast(){
        return this.devicegrille[size-1][size-1];
    }
    
    public void reset(){
        for (int i=0;i<size*size;i++){
                linkgrille[i].randomDebitInstant();
        }
    }
}