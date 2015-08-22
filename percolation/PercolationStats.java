public class PercolationStats {
	private Percolation m_pc;
	public PercolationStats(int N, int T) { //perform T independent computational experiments on an N-by-N grid
			if (N <= 0 || T <= 0) throw java.lang.IllegalArgumentException(ex);
			m_pc = new Percolation(N);
	}

	public double mean() {//
	}

	public double stddev() {
	}

	public double confidenceLo() {
	}

	public double confidenceHi() { //returns upper bound of the 95% confidence interval
	}

	public static void main (String[] args) { // test client
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(N,T);
		StdOut.println("mean			= "+ps.mean();
		StdOut.println("stdev			= "+ps.stdev();
		StdOut.println("%95 confidence level	= "+ps.confidenceLo()+",  "+ps.conidenceHi();
	}
	}


