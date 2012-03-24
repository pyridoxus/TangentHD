package cmcculloch1.csc212;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class EquationManager {
	private ArrayList<Equation> e;
	private int id;	// ID of the currently used equation
//	private double xmin, xmax, step;
	public EquationManager(double xmin, double xmax, double step) {
		e = new ArrayList<Equation>();
		init(xmin, xmax, step);
		setId(0);	// Must be set when selecting equation
	}

	public EquationManager() {
		e = new ArrayList<Equation>();
		setId(0);	// Must be set when selecting equation
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void init(double xmin,  double xmax, double step) {
		e.clear();
//		this.xmin = xmin;
//		this.xmax = xmax;
//		this.step = step;
		Equation a = new Equation(R.id.equation1, xmin, xmax, step);
		e.add(a);
		a = new Equation(R.id.equation2, xmin, xmax, step);
		e.add(a);
		a = new Equation(R.id.equation3, xmin, xmax, step);
		e.add(a);
	}
	
	@SuppressWarnings("unchecked")
	public void draw(Bitmap b) {
		ArrayList<Point2D> data = new ArrayList<Point2D>();
		Paint p = new Paint();
		Canvas c = new Canvas(b);
		GraphPoint gp = new GraphPoint();
		int x, y;
//		p.setColor(getResources().getColor(R.color.black));
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(2);

		switch(this.id){
			case R.id.equation1:
				data = (ArrayList<Point2D>)e.get(0).getPoints();
			break;
			case R.id.equation2:
				data = (ArrayList<Point2D>)e.get(1).getPoints();
			break;
			case R.id.equation3:
				data = (ArrayList<Point2D>)e.get(2).getPoints();
			break;
		}
		Iterator<Point2D> i = data.iterator();
		Point2D pnt = new Point2D();
		// Setup the initial point to be the same as the first point in list
		pnt.setX(data.get(0).getX());
		pnt.setY(data.get(0).getY());
		while(i.hasNext()) {
			Point2D nxt = (Point2D) i.next();
			gp.x = pnt.getX();
			gp.y = pnt.getY();
			gp.convert();
			x = (int)gp.x;
			y = (int)gp.y;
			gp.x = nxt.getX();
			gp.y = nxt.getY();
			gp.convert();
			c.drawLine(x, y, (float)gp.x, (float)gp.y, p);
		}
	}
}
