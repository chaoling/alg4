import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class Brute {
    public static void main(String[] args) {
           In in = new In(args[0]);      // input file
        int N = in.readInt();         // will throw NumberFormatException
        Point[] pts = new Point[N];
        int i = 0;
 
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.006);
        int xmax = 0;
        int ymax = 0;
        
        while (!in.isEmpty()) {
            int xcoord = in.readInt();
            int ycoord = in.readInt();
            if (xcoord > xmax) xmax = xcoord;
            if (ycoord > ymax) ymax = ycoord;
            pts[i] = new Point(xcoord, ycoord);
            //pts[i].draw();
            i++;
        }
        StdDraw.setXscale(0, xmax+1);
        StdDraw.setYscale(0, ymax+1);
        for (i = 0; i < pts.length; i++) {
            pts[i].draw();
        }
        assert (i == N);
        bruteForceSolver(pts);
        
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
    
    private static void bruteForceSolver(Point[] pts) {
        if (pts == null || pts.length < 4) {
            return;
        }
        Set<ArrayList<Point>> ps = new HashSet<ArrayList<Point>>();
        int size = 0; //size of the hashSet
        for (int i = 0; i < pts.length; i++) {
           for (int j = 0; j != i && j < pts.length; j++) {
              for (int k = 0; k != i && k != j && k < pts.length; k++) {
                 for (int l = 0; l != i && l != j && l != k && l < pts.length; l++) {
                      //only consider one permutation of ascending order
                         Point[] points = 
                             new Point[] {pts[i], pts[j], pts[k], pts[l]};
                         //printLine(points);
                        Arrays.sort(points); 
                        ArrayList<Point> pointList = 
                            new ArrayList<Point>(Arrays.asList(points));
                        size = ps.size();
                        //System.out.println("set size is --> "+size);
                        ps.add(pointList);
                        //System.out.println("set size is --> "+ps.size());
                        if (size+1 == ps.size() && isColliner(points)) {
                            printLine(points);
                            drawLine(points);
                        }
                     }
                   }
                }
            }
        }
    
    private static boolean isInOrder(Point[] pts) {
        assert (pts != null);
        assert (pts.length >= 2);
        boolean res = true;
        for (int i = 0; i < pts.length-1; i++) {
            if (pts[i].compareTo(pts[i+1]) >= 0) {
                res = false;
                break;
            }
        }
        return res;      
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
        //Arrays.sort(pts);
        pts[0].drawTo(pts[pts.length-1]);
    }
}