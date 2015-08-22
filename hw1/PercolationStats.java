public class PercolationStats {
    private Percolation mPc;
    private double[] mThreshold;
    public PercolationStats(int N, int T) {    
        // perform T independent computational experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        mThreshold = new double[T];
        for (int iter = 0; iter < T; iter++) {
            //System.out.println("==== interation No. "+iter);
            int numOfOpenSite = 0;
            mPc = new Percolation(N);
            while (numOfOpenSite < N*N) {
                int i = StdRandom.uniform(N)+1;
                int j = StdRandom.uniform(N)+1;
                //System.out.println("opening : row "+i+" column "+j);
                if (!mPc.isOpen(i, j)) {
                    mPc.open(i, j);
                    numOfOpenSite++;
                }
                if (mPc.percolates()) {
                    //System.out.println("Number of Open Site: "+numOfOpenSite);
                    mThreshold[iter] = numOfOpenSite*1.0/(N*N);
                    break;
                }     
            }
        }    
    }
    public double mean() { 
        // sample mean of percolation threshold
        return StdStats.mean(mThreshold);
    }
    public double stddev() {  
        // sample standard deviation of percolation threshold
        return StdStats.stddev(mThreshold); 
    }
    public double confidenceLo() { 
        // returns lower bound of the 95% confidence interval
        return mean()-1.96*stddev()/Math.sqrt(mThreshold.length);
    }
    public double confidenceHi() {
        // returns upper bound of the 95% confidence interval
        return mean()+1.96*stddev()/Math.sqrt(mThreshold.length);
    }
    public static void main(String[] args) {
        // test client, described below
        int N = 0;
        int T = 0;
        try {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //System.out.println("size :"+N+"by"+N+" try "+T+" times");
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean          = "+ps.mean());
        StdOut.println("stddev        = "+ps.stddev());
        StdOut.println("%95 confidence level = "+ps.confidenceLo()
                           +","+ps.confidenceHi()); 
    }
}