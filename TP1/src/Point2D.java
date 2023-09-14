
public class Point2D {
	public final Point2D O = new Point2D(0,0);
	public final Point2D X  = new Point2D (1,0);
	public final Point2D Y = new Point2D (0,1);
	public final Point2D Z = new Point2D (1,1);
	
	public int abs, ord;

	public Point2D(int x, int y) {
		abs=x;
		ord=y;
	}
	public double distance(){
		return Math.sqrt(abs/2)+(ord/2);
	}
}
