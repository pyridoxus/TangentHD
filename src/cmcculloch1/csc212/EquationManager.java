package cmcculloch1.csc212;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class EquationManager extends GraphPoint{
	private ArrayList<Equation> e;
	private int id;	// ID of the currently used equation
	private int color;	// Color of graph
	private ArrayList<Point2D> data;	// Array of currently used data
//	private double xmin, xmax, step;
	public EquationManager(double xmin, double xmax, double step,
							int resolution) {
		super();
		e = new ArrayList<Equation>();
		init(xmin, xmax, step, resolution);
		setId(0);	// Must be set when selecting equation
	}

	public EquationManager() {
		super();
		e = new ArrayList<Equation>();
		setId(0);	// Must be set when selecting equation
	}

	public int getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	private void setData() {
		switch(id) {
			case R.id.equation1:
				data = (ArrayList<Point2D>)e.get(0).getPoints();
				System.out.println("Using equation 1");
			break;
			case R.id.equation2:
				data = (ArrayList<Point2D>)e.get(1).getPoints();
				System.out.println("Using equation 2");
			break;
			case R.id.equation3:
				data = (ArrayList<Point2D>)e.get(2).getPoints();
				System.out.println("Using equation 3");
			break;
			default:
				data = null;
		}
	}
	
	public void setId(int id) {
		this.id = id;
		if(e.size() > 0) this.setData(); // Set data based on the current ID
	}
	
	public void init(double xmin,  double xmax, double step, int resolution) {
		e.clear();
//		this.xmin = xmin;
//		this.xmax = xmax;
//		this.step = step;
		this.setResolution(resolution);	// GraphPoint requires size
		System.out.println("Initialize with xmin=" + xmin + 
						" xmax=" + xmax + " step=" + step);
		Equation a = new Equation(R.id.equation1, xmin, xmax, step);
		e.add(a);
		a = new Equation(R.id.equation2, xmin, xmax, step);
		e.add(a);
		a = new Equation(R.id.equation3, xmin, xmax, step);
		e.add(a);
		this.setData();
	}
	
	public void draw(Bitmap b) {
		Paint p = new Paint();
		Canvas c = new Canvas(b);
		int x, y;
		p.setColor(this.color);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(3);

//		System.out.println("Length = " + data.size());
		Point2D pnt = new Point2D();
		// Setup the initial point to be the same as the first point in list
		pnt.setX(data.get(0).getX());
		pnt.setY(data.get(0).getY());
		for(Iterator<Point2D> i = data.iterator(); i.hasNext();) {
			Point2D nxt = i.next();
//			System.out.println("nxt (" + Double.toString(nxt.getX()) + ", " + 
//					Double.toString(nxt.getY()) + ")");
			this.x = pnt.getX();
			this.y = pnt.getY();
//			System.out.println("pnt (" + Double.toString(this.x) + ", " + 
//					Double.toString(this.y) + ")");
			this.convert();
			x = (int)this.x;
			y = (int)this.y;
			this.x = nxt.getX();
			this.y = nxt.getY();
			this.convert();
			c.drawLine(x, y, (float)this.x, (float)this.y, p);
//			System.out.println("(" + Double.toString(x) + ", " + 
//								Double.toString(y) + ")");
			pnt = nxt;
		}
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
