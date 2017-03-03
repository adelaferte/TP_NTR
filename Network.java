public class Network {
	
    public int size = 0;
    public int debmin = 10 + (int)(Math.random()*40);
    public int debmax = 60 + (int)(Math.random()*100);
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
        // cr�ation des liens
        for (int i=0;i<size;i++){
                for (int j=0;j<size;j++){
                	if (  ((i%2)== 0  && (j%2)== 0) || ( (i%2)!=0 && (j%2)!=0 ) ){ 
                        if (is_valid(i+1,j,size) & is_valid(i,j,size)){
                            num_link = new Link(this.devicegrille[i][j],this.devicegrille[i+1][j],debmin,debmax);
                            linkgrille[x] = num_link;
                            x++;
                                
                        }
                        if (is_valid(i-1,j,size) & is_valid(i,j,size)){
                            num_link = new Link(this.devicegrille[i][j],this.devicegrille[i-1][j],debmin,debmax);
                            linkgrille[x] = num_link;
                            x++;
                        }
                        if (is_valid(i,j+1,size) & is_valid(i,j,size)){
                            num_link = new Link(this.devicegrille[i][j],this.devicegrille[i][j+1],debmin,debmax);
                            linkgrille[x] = num_link;
                            x++;
                        }
                        if (is_valid(i,j-1,size) & is_valid(i,j,size)){
                            num_link = new Link(this.devicegrille[i][j],this.devicegrille[i][j-1],debmin,debmax);
                            linkgrille[x] = num_link;
                            x++;
                        }
                	}
                }
            }
            System.out.println("Network created.");
        }

    private boolean is_valid(int i, int j, int size) {
        return(i>=0 & j >= 0 & i < size & j < size);
    }
    
    public String ToString (){
    	for ( int x  = 0 ; x < size ; x ++ ){
    		for (int y = 0 ; y < size ; y ++ ){
    			System.out.println("x:"+x+"  y:"+y+"     "+devicegrille[x][y].ToString());
    		}
    		
    	}
    	
		return null;
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






