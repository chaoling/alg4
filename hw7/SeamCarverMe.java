import java.util.List;
import java.util.ArrayList;

public class SeamCarver {
    
    private static final double EBOARDER = 195075;
    private Picture mPic;
    //private double[][] mEnergy;
    private ShortestEnergyPath mSp;
    
    public SeamCarver(Picture picture) {
        // create a seam carver object based on the given picture
        mPic = new Picture(picture);
        int w = width();
        int h = height();
        //mEnergy = new double[w][h];
        //initializeEnergyGraph();
    }
    
    public Picture picture() {
        // current picture
        return mPic;
    }
    
    public int width() {
        // width of current picture
        return mPic.width();
    }
    
    public int height() {
        // height of current picture
        return mPic.height();
    }
    
    public  double energy(int x, int y) {
        //check boundary conditions
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException("point("+x+","+y+") is out of pic bounds");
        }
        // energy of pixel at column x and row y
        if (isBoarderPixel(x, y)) {
            return EBOARDER;
        } else {
            //may cache it here
            return gradient(x, y);
        }
    }
    
    public   int[] findHorizontalSeam() {
        // sequence of indices for horizontal seam
        int[] res = null;
        mSp = new ShortestEnergyPath(width(), height(), false);
        List<Integer> endPts = new ArrayList<Integer>();
        for (int y = 0; y < height(); y++) {
            endPts.add(xyTop(width() - 1, y));
        }
        int minEndPoint = findMin(mSp, endPts);
        res = horizontalPath(minEndPoint, mSp);
        return res;
    }
    
    public   int[] findVerticalSeam() {
        // sequence of indices for vertical seam
        int[] res = null;
        mSp = new ShortestEnergyPath(width(), height(), true);
        List<Integer> endPts = new ArrayList<Integer>();
        for (int x = 0; x < width(); x++) {
            endPts.add(xyTop(x, height() - 1));
        }
        int minEndPoint = findMin(mSp, endPts);
        res = verticalPath(minEndPoint, mSp);
        return res;
    }
    
    public    void removeHorizontalSeam(int[] seam) {
        if (seam.length != width()) {
           throw new IllegalArgumentException();
        }
        Picture nPic = new Picture(width(), height() - 1);
        //System.out.println("width: "+width()+ " height: "+height());
        //i-->col/x; j-->row/y
        for (int i = 0; i < width(); i++) {
            System.out.println("seam :"+i+" is "+seam[i]);
            for (int j = 0; j < seam[i]; j++) {
                nPic.set(i, j, mPic.get(i, j));
            }
            for (int j = seam[i]+1; j < height(); j++) {
                nPic.set(i, j-1, mPic.get(i,j));
            }
        }
        this.mPic = nPic;    
    }
    
    public    void removeVerticalSeam(int[] seam) {
        if (seam.length != height()) {
           throw new IllegalArgumentException();
        }
        Picture nPic = new Picture(width() - 1, height());
        for (int j = 0; j < height(); j++) {
            for (int i = 0; i < seam[j]; i++) {
                nPic.set(i, j, mPic.get(i, j));
            }
            for (int i = seam[j]+1; i < width(); i++) {
                nPic.set(i-1, j, mPic.get(i,j));
            }
        }
        this.mPic = nPic;    
    }
    
    private class ShortestEnergyPath {
                
        // energyTo[v] = total Energy of shortest s->v path
        // similar: distTo[]
        private double[] energyTo;
        // pathTo[v] = last point on shortest s->v path
        // similar: edgeTo[]
        private int[] pathTo; 
        
        private int width;
        
        private int height;
        
        private int size;
        
        boolean isVertical;


    /**
     * Computes a shortest paths tree from <tt>source(s)</tt> to every other vertex in
     * the directed acyclic graph <tt>G</tt>.
     * @param eg the Energy Array (build acyclic graph as you go)
     * @param vertcial is a boolean to compute source vertex(es) from the graph
     * depends on the orientation of the graph (i.e, horizontal vs. vertical)
     * @does not throw IllegalArgumentException if the digraph is not acyclic
     * @does not throw IllegalArgumentException unless 0 &le; <tt>s</tt> &le; <tt>V</tt> - 1
     */
    public ShortestEnergyPath(int width, int height, boolean vertical) {
        this.width = width;
        this.height = height;
        this.size = width * height;
        isVertical = vertical;
        energyTo = new double[size];
        pathTo = new int[size];
        

        List<Integer> startPts = new ArrayList<Integer>(); //initial starting points to search for in sp algorithm
        if(isVertical) {
            //start from the top row
            for (int i = 0; i < width; i++) {
                startPts.add(i);
            }
        } else {
            //start from the left column
            for (int j = 0; j < height; j += width) {
                startPts.add(j);
            }
        }
        
        initCondition(startPts);
        //relax pts in topological order
        List<Integer> topological = new ArrayList<Integer>();
        if(isVertical) {
            for(int i = 0; i < size; i++) {
                //System.out.println(pToxy(i)[0]+","+pToxy(i)[1]+":"+i);
                topological.add(i);
            }
        } else {
            for(int x = 0; x < width; x++) {
                for(int y = height - 1; y >= 0; y--) {
                    //System.out.println(x+","+y+":"+xyTop(x,y));
                    topological.add(xyTop(x,y));
                }
            }
        }
        
        for (int v : topological) {
            List<Integer> n = neighbours(v);
            if ( n != null && n.size() > 0) {
                for (int p : neighbours(v)) {
                    relax(v, p);
                }
            }
        }
    }

    // relax edge from point v to point w
    public void relax(int v, int w) {
        if (energyTo[w] > energyTo[v] + energy(w)) {
            energyTo[w] = energyTo[v] + energy(w);
            pathTo[w] = v;
        }       
    }

    /**
     * Returns the length of a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>.
     * @param v the destination vertex
     * @return the length of a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>;
     *    <tt>Double.POSITIVE_INFINITY</tt> if no such path
     */
    public double distTo(int v) {
        return energyTo[v];
    }

    /**
     * Is there a path from the source vertex <tt>s</tt> to vertex <tt>v</tt>?
     * @param v the destination vertex
     * @return <tt>true</tt> if there is a path from the source vertex
     *    <tt>s</tt> to vertex <tt>v</tt>, and <tt>false</tt> otherwise
     */
    public boolean hasPathTo(int v) {
        return energyTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>.
     * @param v the destination vertex
     * @return a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>
     *    as an iterable of edges, and <tt>null</tt> if no such path
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (Integer p = pathTo[v]; p != null; p = pathTo[p]) {
            path.push(p);
        }
        return path;
    }
    
    private List<Integer> neighbours(int p) {
        List<Integer> res = new ArrayList<Integer>();
        int[] coord = pToxy(p);
        if (isVertical) { //fix coord[0]
            if (coord[1] == height - 1){
                return null;
            }
            if (coord[0] > 0) {
                int ld = xyTop(coord[0] - 1, coord[1] + 1);
                res.add(ld);
            }
            int md = xyTop(coord[0], coord[1] + 1);
            res.add(md);
            if (coord[0] < width - 1) {
                int rd = xyTop(coord[0] +1, coord[1] +1);
                res.add(rd);
            }
     } else { //horizontal, fix coord[1]
            if (coord[0] == width -1){
                return null;
            }
            if (coord[1] < height -1) {
                int dd = xyTop(coord[0] +1, coord[1] +1);
                res.add(dd);
            }
            int md = xyTop(coord[0] + 1, coord[1]);
            res.add(md);
            if (coord[1] > 0) {
                int ud = xyTop(coord[0] + 1, coord[1] - 1);
                res.add(ud);
            }  
     }
     return res;
        
    }
    
    private void initCondition(List<Integer> startPoints) {
        for (int v = 0; v < size; v++) {
            energyTo[v] = Double.POSITIVE_INFINITY;
            pathTo[v] = -1;
        }
        for (int i:startPoints) {
            energyTo[i] = 0.0;
        }
    }
        
}
    private int findMin(ShortestEnergyPath sp, List<Integer> pts) {
        int res = -1;
        double min = Double.POSITIVE_INFINITY;
        for (int p : pts) {
            //System.out.println("endpt:"+pToxy(p)[0]+","+pToxy(p)[1]+": totoalEnergy: "+sp.distTo(p));
            if (sp.distTo(p) < min) {
                min = sp.distTo(p);
                res = p;
            }
        }
        return res;
    }
    
    private int[] verticalPath(int endPoint, ShortestEnergyPath sp) {
        int[] res = new int[height()];
        int[] coord = pToxy(endPoint);
        res[coord[1]] = coord[0]; //one and exactly one pt for each row
        //walk backwards to find all points in the shortest path
        for(int p = sp.pathTo[endPoint]; p >= 0; p = sp.pathTo[p]) {
            coord = pToxy(p);
            res[coord[1]] = coord[0];
        }
        return res;
    }
    
     private int[] horizontalPath(int endPoint, ShortestEnergyPath sp) {
        int[] res = new int[width()];
        int[] coord = pToxy(endPoint);
        res[coord[0]] = coord[1]; //one and exactly one pt for each column
        //walk backwards to find all points in the shortest path
        for(int p = sp.pathTo[endPoint]; p >= 0; p = sp.pathTo[p]) {
            coord = pToxy(p);
            res[coord[0]] = coord[1];
        }
        return res;
    }
    
    private boolean isBoarderPixel(int x, int y) {
        if (x == 0 || x == width() -1 || y == 0 || y == height() - 1) {
            return true;
        } else {
            return false;
        }
    }
    
    private double gradient(int x, int y) {
        double res = 0;
        assert (!isBoarderPixel(x, y));
        int[] rgbL = getRGB(x - 1, y);
        int[] rgbR = getRGB(x + 1, y);
        int[] rgbU = getRGB(x, y - 1);
        int[] rgbD = getRGB(x, y + 1);
        double deltaX = 0;
        double deltaY = 0;
        for (int i = 0; i < rgbL.length; i++) {
            deltaX += (rgbR[i] - rgbL[i]) * (rgbR[i] - rgbL[i]);
        }
        
        for (int j = 0; j < rgbU.length; j++) {
            deltaY += (rgbD[j] - rgbU[j]) * (rgbD[j] - rgbU[j]);
        }
        res = deltaX + deltaY;
        return res;
    }
    
    private int[] getRGB(int x, int y) {
        int rgb = mPic.get(x, y).getRGB();
        int red = (rgb >> 16) & 0x000000FF;
        int green = (rgb >> 8) & 0x000000FF;
        int blue = (rgb) & 0x000000FF;
        return new int[] {red, green, blue};
    }
    
    private double energy(int p) {
        int[] coord = pToxy(p);
        return energy(coord[0], coord[1]);
    }
    
    private int[] pToxy(int p) {
        if (p < 0 || p >= width() * height()) {
            throw new IllegalArgumentException("point is out of the range of the graph");
        } else {
            int y = p / width();
            int x = p - y * width();
            return new int[] {x, y};
        }
    }
    
    private int xyTop(int x, int y) {
        // x is the column, y is the row
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new IllegalArgumentException("coordinate is outside the graph");
        } else {
            int p = x + y * width();
            return p;
        }
    }
}