package cmcculloch1.csc212;

public class Point2D {
	private double x, y;
	
	public Point2D(double X, double Y) {
		setX(X);
		setY(Y);
	}
	
	public Point2D() {
		setX(0);
		setY(0);
	}

	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

}
