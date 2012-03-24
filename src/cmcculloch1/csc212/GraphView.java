package cmcculloch1.csc212;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {
	Bitmap bg;	// Background bitmap to hold the more static image of graph
	Bitmap fg;	// Foreground bitmap to hold the more dynamic image of secants
	GraphPoint gp;	// Object to convert from graph coordinate system to bmp
	EquationManager eqMan;	// Manage the data representing the graphs
	
	public GraphView(Context context) {
		// TODO Auto-generated method stub
		super(context);
		init();
	}

	public GraphView(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		super(context, attrs);
		init();
	}

	public GraphView(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated method stub
		super(context, attrs, defStyle);
		init();
	}

	// This function is called when the user selects to use a different equation
	public void selectEquation(int i) {
		eqMan.setId(i);
		createBMPs();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		gp.setH(getHeight());
		gp.setW(getWidth());
		double xmin = -gp.getW() / 2 / gp.getResolution();
		double xmax = -xmin;
		int steps = getResources().getInteger(R.integer.graphStep);
		double step = 1.0 / (double)steps;
		System.out.println("xmin: " + Double.toString(xmin) +
				" xmax: " + Double.toString(xmax) + 
				" step: " + Double.toString(step));
		eqMan.init(xmin, xmax, step);
		createBMPs();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(fg, 0, 0, null);
		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measuredHeight = measureHeight(heightMeasureSpec);
		int measuredWidth = measureWidth(widthMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
	}
	
	private void init() {
		gp = new GraphPoint();
		gp.setResolution(getResources().getInteger(R.integer.resolution));
		eqMan = new EquationManager();
		selectEquation(R.id.equation1);
	}
	
	private int measureWidth(int widthMeasureSpec) {
       int specSize = MeasureSpec.getSize(widthMeasureSpec) -
	   this.getPaddingLeft() - this.getPaddingRight();
       return specSize;
	}

	private int measureHeight(int heightMeasureSpec) {
       int specSize = MeasureSpec.getSize(heightMeasureSpec);
       return specSize;
	}
	
	private void createBMPs() {
		bg = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
								Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bg);
		c.drawColor(getResources().getColor(R.color.white));
		drawGrid();
		drawEquation();
		fg = Bitmap.createBitmap(bg);
	}
	
	private void drawEquation() {
		eqMan.draw(bg);
	}

	private void drawGrid() {
		double x, y;	// Graph coordinates
		Paint p = new Paint();
		Canvas c = new Canvas(bg);
		p.setColor(getResources().getColor(R.color.black));
		p.setStyle(Paint.Style.STROKE);
		double xres = gp.getW() / gp.getResolution();
		double yres = gp.getH() / gp.getResolution();
		p.setStrokeWidth(2);
		gp.y = 0;
		for(x = -xres; x < xres; x++) {
			gp.x = x;
			gp.convert();
			c.drawLine((float)gp.x, 0, (float)gp.x, gp.getH(), p);
		}
		gp.x = 0;
		for(y = -yres; y < yres; y++) {
			gp.y = y;
			gp.convert();
			c.drawLine(0, (float)gp.y, gp.getW(), (float)gp.y, p);
		}
		p.setStrokeWidth(4);
		// Draw x axis
		c.drawLine(0, gp.getH() / 2, gp.getW(), gp.getH() / 2, p);

		// Draw y axis
		c.drawLine(gp.getW() / 2, 0, gp.getW() / 2, gp.getH(), p);
	}
	
}
