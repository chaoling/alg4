import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    
    private TreeSet<Point2D> mSet;
    
    public PointSET() {
        // construct an empty set of points 
        mSet = new TreeSet<Point2D>();
    }
    
    public boolean isEmpty() {
        return (size() == 0); 
    }
    
    public int size()  {
        // number of points in the set 
        return mSet.size();
    }
    
    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        mSet.add(p);
    }
    
    public boolean contains(Point2D p) {
        // does the set contain point p?
        return mSet.contains(p);
    }
    
    public void draw()    {
        // draw all points to standard draw 
        StdDraw.setScale(0.0, 1.0);
        StdDraw.setPenColor(StdDraw.BLACK);
        if (mSet != null && size() > 0) {
            for (Point2D pt: mSet) {
                pt.draw();
            }
        }
    }
    
    public Iterable<Point2D> range(RectHV rect)  {
        // all points that are inside the rectangle 
        ArrayList<Point2D> pts = new ArrayList<Point2D>();
        if (mSet != null && size() > 0) {
            for (Point2D pt:mSet) {
                if (rect.contains(pt)) {
                    pts.add(pt);
                }
            }
        }
        return pts;
    }
    
    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty 
        Point2D npt = null;
        double min = Double.MAX_VALUE;
        if (size() > 0) {
            for (Point2D pt:mSet) {
                double dis = p.distanceSquaredTo(pt);
                if (dis < min) {
                    npt = pt;
                    min = dis;
                }
            }
        }
        return npt;
    }
    
    public static void main(String[] args) {
        // unit testing of the methods (optional)
        
    }
}