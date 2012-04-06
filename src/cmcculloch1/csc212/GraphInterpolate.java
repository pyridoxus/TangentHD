package cmcculloch1.csc212;

public class GraphInterpolate extends GraphObjectBase {

	protected double sizeRatio;	// Number of pixels per graph unit.
	protected double offsetX;		// X axis graph offset.
	protected double offsetY;		// Y axis graph offset.
	private Point2D q;			// Hold onto the interpolated point.
	
	public GraphInterpolate(double sizeRatio, double offsetX, double offsetY) {
		
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.sizeRatio = sizeRatio;
		if(this.sizeRatio < 1) this.sizeRatio = 1;
		q = new Point2D();
	}
	
	public double getSizeRatio() {
		return sizeRatio;
	}
	
	public void setSizeRatio(double sizeRatio) {
		this.sizeRatio = sizeRatio;
	}
	
	public double getOffsetX() {
		return offsetX;
	}
	
	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}
	
	public double getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}
	
	public void graphToBmp(double x, double y) {
		double bmpX;
		double bmpY;
		bmpX = (double)this.bmp.getWidth();
		bmpY = (double)this.bmp.getHeight();
		// If x, y offsets are 0, 0, then origin of graph is center of bitmap.
//		System.out.println("Inside graphToBmp");
//		System.out.println(Double.toString(x) + ", " + Double.toString(y));
		q.setX((bmpX / 2.0) + (x - this.offsetX) * this.sizeRatio);
		q.setY((bmpY / 2.0) - (y - this.offsetY) * this.sizeRatio);
//		System.out.println(Double.toString(q.getX()) + ", " + Double.toString(q.getY()));
//		System.out.println("Leaving graphToBmp");
	}
	
	public void bmpToGraph(double x, double y) {
		double bmpX;
		double bmpY;
		bmpX = (double)this.bmp.getWidth();
		bmpY = (double)this.bmp.getWidth();
		// This function is not done yet!
		// If x, y offsets are 0, 0, then origin of graph is center of bitmap.
		q.setX((x - (bmpX / 2.0)) / this.sizeRatio + this.offsetX);
		q.setY((y - (bmpY / 2.0)) / this.sizeRatio + this.offsetY);
	}
	
	public double getInterpX() {
		return q.getX();
	}

	public double getInterpY() {
		return q.getY();
	}
}
