
public class KdTreeTest {

    public static void main(String[] args) { 
// unit testing of the methods (optional)
        String filename = args[0];
        In in = new In(filename);

//        StdDraw.show(0);
        StdDraw.setScale(0.0, 1.0);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(0.5, 0.5, 0.5);    
        // initialize the two data structures with point from standard input
        //PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        int i = 0;
        String[] tx = {"A","B","C","D","E","F","G","H"
            ,"I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W"
            ,"X","Y","Z"};
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            StdDraw.text(p.x(),p.y(),tx[i%26]+i);
            i++;
            //System.out.println(p);
            kdtree.insert(p);
            //brute.insert(p);
            
        }
        StdDraw.setPenRadius(0.01);
        //brute.draw();
        StdDraw.setPenRadius();
        kdtree.draw();
    }
}
