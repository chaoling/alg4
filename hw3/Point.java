/*************************************************************************
 * Name: Chao Ling
 * Email: ling.chao@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlopeOrder(); 
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double res;
        if (this.isEqual(that)) {
            res = Double.NEGATIVE_INFINITY;
        } else if (this.isVertical(that)) {
            res = Double.POSITIVE_INFINITY;
        } else if (this.isHorizontal(that)) {
            res = 0.0;
        } else {
            res = 1.0 * (that.y - this.y) / (that.x - this.x);
        }
        return res;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    @Override
    public int compareTo(Point that) {
        if (this.y != that.y) {
            return (this.y - that.y);
        } else {
            return (this.x - that.x);
        }
//        if (this.y > that.y || (this.y == that.y && this.x > that.x)) {
//            res = 1;
//        } else if (this.y < that.y || this.x < that.x) {
//            res = -1;
//        } else {
//            res = 0;
//        } 
    }

    private class BySlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point p0, Point p1) {
            int res;
            double s0 = slopeTo(p0);
            double s1 = slopeTo(p1);
            if (s0 > s1) res = 1;
            else if (s0 < s1) res = -1;
            else res = 0;
            return res;
        }
        
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        double d0 = Double.POSITIVE_INFINITY;
        double d1 = Double.NEGATIVE_INFINITY;
        if (d0 == d1) {
            System.out.println("d0 equals d1: "+(d0 == d1));
        } else {
            System.out.println("d0 not equal d1");
        }
    }
    
    private boolean isEqual(Point that) {
        return (this.x == that.x && this.y == that.y);
    }
    
    private boolean isVertical(Point that) {
        return (this.x == that.x && this.y != that.y);
    }
    
    private boolean isHorizontal(Point that) {
        return (this.y == that.y && this.x != that.x);
    }
}