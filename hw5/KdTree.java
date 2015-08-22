import java.util.ArrayList;

public class KdTree {
    
    private Node mKdRoot;
    
    public KdTree() {
    }
    
    public boolean isEmpty() {
        return size() == 0; 
    }
    
    public int size()  {
        // number of points in the set 
        return size(mKdRoot);
    }
    
    
    // return number of pts in kdTree rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    /***********************************************************************
      *  Insert point2d (x, y) into kdTree Node   
      *  If key already exists, update with new value
      ***********************************************************************/
    
    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (this.contains(p)) {
            //System.out.println("point :"+p+" already in the tree");
            return;
        }
        //special case: root node is null, root level is 0
        if (mKdRoot == null) {
            //This is the root node
            double xmin = 0.0;
            double ymin = 0.0;
            double xmax = 1.0;
            double ymax = 1.0;
            RectHV r = new RectHV(xmin, ymin, xmax, ymax);
            mKdRoot = new Node(p, r, true, 1);
        } else {
            mKdRoot = insert(mKdRoot, p);
        }
        //assert check();
    }
    
    private Node insert(Node node, Point2D pt) {
        assert node != null; // we took care of the null case in root node
        assert (pt.x() >= 0.0 && pt.x() <= 1.0 && pt.y() >= 0.0 && pt.y() <= 1.0);
        double cmp;
        if (node.isEvenNode) {
            cmp = pt.x() - node.p.x();
        } else {
            cmp = pt.y() - node.p.y();
        }
        //System.out.println("is even node:"
        //+node.isEvenNode+" cmp: "+cmp+" pt: "+pt+ " node:"+node.p);
        if (cmp < 0) {
            if (node.lb != null) {
                node.lb = insert(node.lb, pt);
            } else {
                RectHV r = getRectHV(pt, node, true);
                node.lb = new Node(pt, r, !node.isEvenNode, 1);
            }
        } else  {
            if (node.rt != null) {
                node.rt = insert(node.rt, pt); //go r/t even if equal
            } else {
                RectHV r = getRectHV(pt, node, false);
                node.rt = new Node(pt, r, !node.isEvenNode, 1); 
            }
        }
        //check invariance
        node.N = 1 + size(node.lb) + size(node.rt);
        return node;
    }
    
    private RectHV getRectHV(Point2D pt, Node node, boolean isLB) {
        assert node != null;
        double xmin = node.rect.xmin();
        double ymin = node.rect.ymin();
        double xmax = node.rect.xmax();
        double ymax = node.rect.ymax();
        if (node.isEvenNode) {
            //horizontal division l/r
            if (isLB) {
                xmax = node.p.x();
            } else {
                xmin = node.p.x();
            }
        } else {
            //vertical division b/t
            if (isLB) {
                ymax = node.p.y();
            } else {
                ymin = node.p.y();
            }
        }
        return new RectHV(xmin, ymin, xmax, ymax);
    }
    
    
    // does there exist an node with Point2D equal to pt?
    public boolean contains(Point2D pt) {
        return get(pt) != null;
    }
    
    // return pt associated with the pt, or null if no such pt exists
    private Point2D get(Point2D pt) {
        return get(mKdRoot, pt);
    }
    
    private Point2D get(Node node, Point2D pt) {
        if (null == node) return null;
        double cmp;
        if (pt.equals(node.p)) return pt;
        if (node.isEvenNode) {
            cmp = pt.x() - node.p.x();
        } else {
            cmp = pt.y() - node.p.y();
        }
        if (cmp < 0) return get(node.lb, pt);
        else         return get(node.rt, pt);
    }
    
    public void draw()    {
        // draw all points to standard draw 
        StdDraw.setScale(0.0, 1.0);
        StdDraw.setPenColor(StdDraw.BLACK);
        //StdDraw.square(0.5, 0.5, 0.5);     
        if (mKdRoot != null && size() > 0) {
            for (Node nd: levelOrder()) {
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(StdDraw.BLACK);
                nd.p.draw();
                StdDraw.setPenRadius();
                drawPartition(nd);
            }
        }
    }
    
    private void drawPartition(Node nd) {
        if (nd.isEvenNode) {
            //draw vertical line
            double x0 = nd.p.x();
            double y0 = nd.rect.ymin();
            double x1 = x0;
            double y1 = nd.rect.ymax(); 
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x0, y0, x1, y1);
        } else {
            //draw horizontal line
            double x0 = nd.rect.xmin();
            double y0 = nd.p.y();
            double x1 = nd.rect.xmax();
            double y1 = y0;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x0, y0, x1, y1);
        }
    }
    
    public Iterable<Point2D> range(RectHV rect)  {
        // all points that are inside the rectangle 
        ArrayList<Point2D> pts = new ArrayList<Point2D>();
        if (rect == null || mKdRoot == null) return pts;
        if (!rect.intersects(mKdRoot.rect)) return pts;
        //do kdTree traversal and add all points that 
        //intersect with the serach rectangle
        getNodeInRange(mKdRoot, rect, pts);
        return pts;
    }
    
    private void getNodeInRange(Node nd, RectHV rect, ArrayList<Point2D> pts) {
        assert rect != null && nd != null;
        if (rect.contains(nd.p)) {
            pts.add(nd.p);
        }
        if (nd.lb != null && rect.intersects(nd.lb.rect)) {
            getNodeInRange(nd.lb, rect, pts);
        }
        if (nd.rt != null && rect.intersects(nd.rt.rect)) {
            getNodeInRange(nd.rt, rect, pts);
        }
    }
    
    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty 
        if (p == null || mKdRoot == null) return null;
        Point2D npt = null;
        double mindist = p.distanceSquaredTo(mKdRoot.p);
        if (mindist == 0.0) return mKdRoot.p;
        npt = nearest(mKdRoot, p, mKdRoot.p);
        return npt;
    }
    
    private Point2D nearest(Node nd, Point2D pt, Point2D cpt) {
        //System.out.println("Nodes visited:" +nd.p+" minPoint: "+cpt);
        double dist = pt.distanceSquaredTo(nd.p);
        double mindist = pt.distanceSquaredTo(cpt);
        Point2D minPt;
        if (dist < mindist) {
            minPt = nd.p;
            mindist = dist;
        } else {
            minPt = cpt;
        }
        double cmp;
        if (nd.isEvenNode) {
            cmp = pt.x() - nd.p.x();
        } else {
            cmp = pt.y() - nd.p.y();
        }
        if (cmp < 0) {
            //consider lb side first:
            if (nd.lb != null && nd.lb.rect.distanceSquaredTo(pt) < mindist) {   
                  Point2D lbpt = nearest(nd.lb, pt, minPt);
                  minPt = minNearestPoint(pt, lbpt, minPt);
                  mindist = pt.distanceSquaredTo(minPt);
            }
            if (nd.rt != null && nd.rt.rect.distanceSquaredTo(pt) < mindist) {
                Point2D rtpt = nearest(nd.rt, pt, minPt);
                minPt = minNearestPoint(pt, rtpt, minPt);
            }
        } else {
            if (nd.rt != null && nd.rt.rect.distanceSquaredTo(pt) < mindist) {   
                Point2D rtpt = nearest(nd.rt, pt, minPt);
                minPt = minNearestPoint(pt, rtpt, minPt);
                mindist = pt.distanceSquaredTo(minPt);
            }
            if (nd.lb != null && nd.lb.rect.distanceSquaredTo(pt) < mindist) {   
                Point2D lbpt = nearest(nd.lb, pt, minPt); 
                minPt = minNearestPoint(pt, lbpt, minPt);
            }
        }
        return minPt;
    }
    
    private Point2D minNearestPoint(Point2D pt, Point2D rpt, Point2D cpt) {
        double rdist = pt.distanceSquaredTo(rpt);
        double mindist = pt.distanceSquaredTo(cpt);
                if (rdist < mindist) {
                    return rpt;
                } else {
                    return cpt;
                }
    }
    
    public static void main(String[] args) {
    }
    
    //kdTreeNode Data Struture
    private static class Node {
        private Point2D p;      // the point
        // the axis-aligned rectangle corresponding to this node
        private RectHV rect;    
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        // root node's level is 0, even node use l/r, odd node use b/t
        private boolean isEvenNode;      
        private int N;         //number of pts rooted at this node
        
        public Node(Point2D pt, RectHV r, boolean isEven, int N) {
            this.p = pt;
            this.rect = r;
            this.isEvenNode = isEven;
            this.N = N;
        }
    }
    
    // level order traversal
    private Iterable<Node> levelOrder() {
        ArrayList<Node> ans = new ArrayList<Node>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(mKdRoot);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            ans.add(x);
            if (x.lb != null) queue.enqueue(x.lb);
            if (x.rt != null) queue.enqueue(x.rt);
        }
        return ans;
    }
    
}