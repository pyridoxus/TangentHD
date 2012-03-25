package cmcculloch1.csc212;

public class GraphPoint {
	double x, y;
	private int resolution, h, w;
	public GraphPoint(){
		x = 0;
		y = 0;
	}
	
	public GraphPoint(double a, double b) {
		x = a;
		y = b;
	}
	
	// Convert the point from graph coordinate system into bitmap coordinate
	// system. The origin of the graph coordinate system is always the center
	// of the bitmap. The variable "resolution" determines how many pixels
	// there are between each integer in the graph coordinate system.
	// x and y are altered inside this function, so all graph coordinate system
	// uses must be completed before calling this function.
	public GraphPoint convert() {
		x = w / 2 + x * resolution;
		y = h / 2 - y * resolution;
		return this;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
}
