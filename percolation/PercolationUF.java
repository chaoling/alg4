public class Percolation {
	private WeightedQuickUnionUF m_uf;
	private int m_Size; //row/column size of the grid
	private boolean [][] m_grid;
	public Percolation(int N) { //create N-by-N grid, with all sites blocked
	       m_Size = N;
	       m_uf = new WeightedQuickUnionUF(N*N+2);//We create a 2D array using 1D arrays notice the dummy start and end notes
	       for (int i=0;i<N; i++) {
		       for(int j=0; j<N;j++) {
			       m_grid[i][j]=false;
			}
		}
	}

	public void open(int i, int j) { //open site (row i, column j) if it is not already opened
		m_grid[i][j] = true;
		if (j<N-1 && m_grid[i][j+1]) m_uf.union(i*N+j+1,i*N+j+2); //union right
		if (j>0 && m_grid[i][j-1]) m_uf.union(i*N+j+1,i*N+j); //union left
		if (i>0 && m_grid[i-1][j]) m_uf.union(i*N+j+1,(i-1)*N+j+1); //union up
		if (i<N-1 && m_grid[i+1][j]) m_uf.union(i*N+j+1,(i+1)*N+j+1);//union down
		if (i=0) m_uf.union(i*N+j+1,0);//union with virtual start site
		if (i=N-1) m_uf.union(i*N+j+1,N*N+1);//union with virtual end site
	}
	public boolean isOpen(int i, int j) { //is site (row i, column j) open?
		return m_grid[i][j];
	}

	public boolean isFull(int i, int j) {// is site (row i, column j) full?
		if (! isOpen(i,j) ) return false;
		if (i==0) return true; //open site in the first row is always full
		return m_uf.connected(0,i*N+j+1);
	}

	//does the system percolate?
	public static boolean percolates() {
		return m_uf.isconnected(0,N*N+1);
	}

	//draw the N-by-N boolean matrix to standard draw
	public static void show(boolean[][] a, boolean which) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
		StdDraw.setYscale(-1, N);
		for (int i =0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (a[i][j] == which) {
					StdDraw.filledSquare(j, N-i-1, .5);
				}
			}
		}
	}

	//return a random N-by-N boolean matrix, where each entry is true with probability p
	public static boolean[][] random(int N, double p) {
		boolean[][] a = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j=0; j < N; j++) {
				a[i][j] = StdRandom.bernoulli(p);
			}
		}
		return a;
	}

	//test case
	public static void main(String[] args) {
		//boolean[][] open = StdArrayIO.readBoolean2D();
		int N = Integer.parseInt(args[0]);
		StdOut.println("matrix size is"+N);
		boolean[][] open = random(N,0.66);
		StdArrayIO.print(open);
		StdOut.println();
		//show(open,true);

		StdArrayIO.print(flow(open));
		StdOut.println(percolates(open));
		show(flow(open),true);
	
	}
}

			
