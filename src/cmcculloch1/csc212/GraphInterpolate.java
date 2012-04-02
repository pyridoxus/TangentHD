package cmcculloch1.csc212;

public class GraphInterpolate {

	protected double sizeRatio;	// Number of pixels per graph unit.
	protected double offsetX;		// X axis graph offset.
	protected double offsetY;		// Y axis graph offset.
	private double bmpX;			// X dimension of bitmap.
	private double bmpY;			// Y dimension of bitmap.
	private Point2D q;			// Hold onto the interpolated point.
	
	public GraphInterpolate(int sizeRatio, int offsetX, int offsetY,
							int bmpX, int bmpY) {
		
		// TODO: Make all sizeRatio, offsets, etc into doubles in XML file.
		// This will make computations use the same data type and reduce
		// type casting problems!
		this.offsetX = (double)offsetX;
		this.offsetY = (double)offsetY;
		this.sizeRatio = (double)sizeRatio;
		if(this.sizeRatio < 1) this.sizeRatio = 1;
		this.bmpX = (double)bmpX;
		this.bmpY = (double)bmpY;
		q = new Point2D();
	}
	
	public double getSizeRatio() {
		return sizeRatio;
	}
	
	public void setSizeRatio(int sizeRatio) {
		this.sizeRatio = sizeRatio;
	}
	
	public double getOffsetX() {
		return offsetX;
	}
	
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	
	public double getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
	public Point2D graphToBmp(Point2D p) {
		// If x, y offsets are 0, 0, then origin of graph is center of bitmap.
		q.setX((this.bmpX / 2.0) + (p.getX() - this.offsetX) * this.sizeRatio);
		q.setY((this.bmpY / 2.0) - (p.getY() - this.offsetY) * this.sizeRatio);
		return this.q;
	}
	
	public Point2D bmpToGraph(Point2D p) {
		// This function is not done yet!
		// If x, y offsets are 0, 0, then origin of graph is center of bitmap.
		q.setX((this.bmpX / 2.0) + (p.getX() - this.offsetX) * this.sizeRatio);
		q.setY((this.bmpY / 2.0) - (p.getY() - this.offsetY) * this.sizeRatio);
		return this.q;
	}
}
