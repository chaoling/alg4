import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
    
public class Fast {
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // will throw NumberFormatException
        Point[] pts = new Point[N];
        int i = 0;
 
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.004);
        int xmax = 0;
        int ymax = 0;
        
        while (!in.isEmpty()) {
            int xcoord = in.readInt();
            int ycoord = in.readInt();
            if (xcoord > xmax) xmax = xcoord;
            if (ycoord > ymax) ymax = ycoord;
            pts[i] = new Point(xcoord, ycoord);
            i++;
        }
        StdDraw.setXscale(0, xmax+1);
        StdDraw.setYscale(0, ymax+1);
        for (i = 0; i < pts.length; i++) {
            pts[i].draw();
        }
        assert (i == N);
        //printLine(pts);
        fastSolver(pts);
        
    }
    
    private static void printLine(Point[] pts) {
     assert (pts != null);
     StringBuilder str = new StringBuilder();
     int i;
     for (i = 0; i < pts.length-1; i++) {
         str.append(pts[i]);
         str.append(" -> ");
     }
     str.append(pts[i]);
     System.out.println(str.toString());
    }
    
    private static void fastSolver(Point[] pts) {
         if (pts == null || pts.length < 4) {
            return;
        }
        //first make a clone
        Point[] ptsCopy = new Point[pts.length]; 
        for (int i = 0; i < pts.length; i++) {
            ptsCopy[i] = pts[i];
        }
        assert (pts.length == ptsCopy.length);
        //use ArrayList because it has equals method
        //pure java array does not implement equals method
        Set<ArrayList<Point>> ps = new HashSet<ArrayList<Point>>();
        int size = 0; //size of the hashSet
        for (int i = 0; i < pts.length; i++) {
            //sort the pts according to the slope wrt. to the current point:
            //merge sort takes NlogN running time
            //System.out.println("====== "+pts[i]+" =======");
            Arrays.sort(ptsCopy, pts[i].SLOPE_ORDER);
            double prevSlope = pts[i].slopeTo(ptsCopy[0]); //in Acsending order
            //first point after sorting is always itself.
            assert (pts[i] == ptsCopy[0]); 
            //counter to count number of collinear points, including itself
            int numOfCollinearPts = 1; 
            for (int j = 1; j < ptsCopy.length; j++) {
                int lastPos = j-1;
                double currSlope = pts[i].slopeTo(ptsCopy[j]);
                if (prevSlope == currSlope) {
                    numOfCollinearPts++;
                } 
                if (prevSlope != currSlope || (j+1 == ptsCopy.length)) {
                    if (j+1 == ptsCopy.length && prevSlope == currSlope) {
                        //exclude the index that not belong
                        //to the line group
                        lastPos = j;
                    }
                    if (numOfCollinearPts > 3) {
//                        System.out.println("lastPos: "+lastPos+
//                                    " prevSlope: "+prevSlope
//                                    +" currSlope: "+currSlope
//                                    +" #ofPts: "+numOfCollinearPts);
                        Point[] collinear = new Point[numOfCollinearPts];
                        for (int c = numOfCollinearPts - 1; c > 0; c--) {
                             //exclued current point, which changes the slope.
                            collinear[c] = ptsCopy[lastPos+c-(numOfCollinearPts-1)]; 
                        }
                        collinear[0] = pts[i];
                        Arrays.sort(collinear);
                        ArrayList<Point> collinearList = 
                            new ArrayList<Point>(Arrays.asList(collinear));
                        size = ps.size();
                        ps.add(collinearList);
                        if (size+1 == ps.size()) {
                            printLine(collinear);
                            drawLine(collinear);
                        }
                    }
                    //we still need to count the jth point in the next round
                    numOfCollinearPts = 2;
                    prevSlope = currSlope;
                }  
            } 
         }
    }
    
    private static boolean isColliner(Point[] pts) {
        assert (pts != null);
        assert (pts.length >= 2);
        double slope = pts[0].slopeTo(pts[1]);
        for (int i = 2; i < pts.length; i++) {
            if (slope != pts[0].slopeTo(pts[i])) {
                return false;
            }
        }
        return true;
    }
    
     private static void drawLine(Point[] pts) {
        assert (pts != null);
        assert (pts.length >= 2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.003);
        Arrays.sort(pts);
        pts[0].drawTo(pts[pts.length-1]);
    }
}