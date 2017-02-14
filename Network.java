public class Network {
		public int size = 0;
		public int debmin = 0;
		public int debmax = 100;
		public Network(int n){
			size = n;
			Device[] grid = new Device[n][n];
			int num_link = 0;
			for (i=0;i<n;i = i+2){
				for (j=0;j<n;j = j+2){
					if (i+1 < n){
						num_link = new Link(grid[i][j],grid[i+1][j],debmin,debmax)
						grid[i][j].addLink(num_link);
						grid[i+1][j].addLink(num_link);
					}
					if (i-1 >=0){
						num_link = new Link(grid[i][j],grid[i-1][j],debmin,debmax)
						grid[i][j].addLink(num_link);
						grid[i-1][j].addLink(num_link);
					}
					if (j+1 < n){
						num_link = new Link(grid[i][j],grid[i][j+1],debmin,debmax)
						grid[i][j].addLink(num_link);
						grid[i][j+1].addLink(num_link);
					}
					if (j-1 >= 0){
						num_link = new Link(grid[i][j],grid[i][j-1],debmin,debmax)
						grid[i][j].addLink(num_link);
						grid[i][j-1].addLink(num_link);
					}
				}
			}
			System.out.println("Network created.")
		}

}
