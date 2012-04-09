package cmcculloch1.csc212;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

// This project is a good example of needing to plan ahead. There are some
// bad (or at least less efficient or messy) ways of setting up objects in
// both this View and the activity associated with it. I suspect there is a
// much better way to set up objects. First, I must better understand the way
// the Android objects are set up, when things occur, what objects exist, etc.
public class GraphView extends View {
	Bitmap bg = null;	// Background bitmap holds more static image of graph
	GraphAttributes gAttr;	// Attributes of the 3 equations
	PowerEq powerEq = null;			// y = x^3
	ParabolaEq parabolaEq = null;	// y = |x^2 - 1|
	AstroidEq astroidEq = null;		// x^(2/3) + y^(2/3) = 1
	Equation theEquation = null;	// The current equation object
	Grid grid = null;				// The background grid
	Secant leftSecant = null;		// Secant left of point of interest
	Secant rightSecant = null;		// Secant right of point of interest
	GraphPoint leftPoint = null;	// Point left of point of interest
	GraphPoint rightPoint = null;	// Point right of point of interest
	GraphPoint centerPoint = null;	// Point of interest
	int currentEq = 0;				// Index of current equation
	boolean fullRedraw = true;		// If true, do full redraw, else do partial
	
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

	public Point2D setAttributes(GraphAttributes gAttr) {
		System.out.println("Inside setAttributes()");
		this.gAttr = gAttr;
		buildGraphObjects();
		return getSecantRanges();
	}
	
	private void buildGraphObjects() {
		// This can be done any time, because it does not rely on the
		// existence of the bitmaps. This is done just after we get the
		// attributes inflated.
		System.out.println("Inside buildGraphObjects()");
		powerEq = new PowerEq(gAttr.getSizeRatio(0), gAttr.getOffsetX(0),
				gAttr.getOffsetY(0), gAttr.getStartX(0), gAttr.getEndX(0),
							gAttr.getStepX(0), gAttr.getName(0));
		parabolaEq = new ParabolaEq(gAttr.getSizeRatio(1), gAttr.getOffsetX(1),
				gAttr.getOffsetY(1), gAttr.getStartX(1), gAttr.getEndX(1),
							gAttr.getStepX(1), gAttr.getName(1));
		astroidEq = new AstroidEq(gAttr.getSizeRatio(2), gAttr.getOffsetX(2),
				gAttr.getOffsetY(2), gAttr.getStartX(2), gAttr.getEndX(2),
							gAttr.getStepX(2), gAttr.getName(2));
		setInternalEquation();
		grid = new Grid(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		leftSecant = new Secant(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq),
				0, gAttr.getSecantMid(currentEq),
				gAttr.getStepX(currentEq));
		leftSecant.setColor(Color.MAGENTA);
		rightSecant = new Secant(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq),
				gAttr.getSecantMid(currentEq), gAttr.getSecantEnd(currentEq),
				gAttr.getStepX(currentEq));
		rightSecant.setColor(Color.RED);
		presetSecants();
		leftPoint = new GraphPoint(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		leftPoint.setColor(Color.MAGENTA);
		rightPoint = new GraphPoint(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		rightPoint.setColor(Color.RED);
		centerPoint = new GraphPoint(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		centerPoint.setColor(Color.GREEN);
		presetPoints();
	}
	
	private void presetSecants() {
		// Set the end points of line segment of secant to present positions
		// that use indices within the equation data, and not recalculating
		// everything.
		int idx = 0;
		leftSecant.setP(theEquation.getData(idx));
		idx = gAttr.getSecantMid(currentEq);
		leftSecant.setQ(theEquation.getData(idx));
		rightSecant.setP(theEquation.getData(idx));
		rightSecant.setQ(theEquation.getData(gAttr.getSecantEnd(currentEq) - 1));
	}
	
	private void presetPoints() {
		int idx = 0;
		leftPoint.setP(theEquation.getData(idx));
		rightPoint.setP(theEquation.getData(gAttr.getSecantEnd(currentEq) - 1));
		centerPoint.setP(theEquation.getData(gAttr.getSecantMid(currentEq)));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(bg == null) {
			// First time this is called, so create everything according to
			// the size of the layout.
			createBMPs();
			powerEq.setBmp(bg);
			parabolaEq.setBmp(bg);
			astroidEq.setBmp(bg);
			grid.setBmp(bg);
			leftSecant.setBmp(bg);
			rightSecant.setBmp(bg);
			leftPoint.setBmp(bg);
			rightPoint.setBmp(bg);
			centerPoint.setBmp(bg);
			setInternalEquation();
//			testInterpolation();
//			testEquation(powerEq);
//			testEquation(parabolaEq);
//			testEquation(astroidEq);
//			testGrid(grid);
			testSecant(leftSecant);
			testSecant(rightSecant);
			System.out.println("Initializing everything in draw...");
		}
		if(fullRedraw == true) {
			grid.draw(canvas);
			theEquation.draw(canvas);
			fullRedraw = false;
			System.out.println("Full redraw in draw...");
		}
		grid.draw(canvas);
		theEquation.draw(canvas);
		leftSecant.draw(canvas);
		rightSecant.draw(canvas);
		leftPoint.draw(canvas);
		rightPoint.draw(canvas);
		centerPoint.draw(canvas);
		System.out.println("Regular draw...");
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measuredHeight = measureHeight(heightMeasureSpec);
		int measuredWidth = measureWidth(widthMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
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
	}
	
	private void init() {
		System.out.println("Inside init()");
	}
	
	private void testInterpolation() {
		GraphInterpolate interpolateTest = 
				new GraphInterpolate(gAttr.getSizeRatio(0),
									 gAttr.getOffsetX(0),
									 gAttr.getOffsetY(0));
		interpolateTest.setBmp(bg);
		Point2D temp = new Point2D();
		System.out.println("Printing conversions...");
		interpolateTest.graphToBmp(0, 0);
		temp.setX(interpolateTest.getInterpX());
		temp.setY(interpolateTest.getInterpY());
		System.out.println("(" + Double.toString(temp.getX()) + ", " +
								Double.toString(temp.getY()) + ")");
		interpolateTest.graphToBmp(1, 1);
		temp.setX(interpolateTest.getInterpX());
		temp.setY(interpolateTest.getInterpY());
		System.out.println("(" + Double.toString(temp.getX()) + ", " +
								Double.toString(temp.getY()) + ")");
		interpolateTest.graphToBmp(-1, -1);
		temp.setX(interpolateTest.getInterpX());
		temp.setY(interpolateTest.getInterpY());
		System.out.println("(" + Double.toString(temp.getX()) + ", " +
								Double.toString(temp.getY()) + ")");
	}

	private void testEquation(Equation eq) {
		System.out.println("Inside testEquation");
		System.out.println(eq.toString());
	}
	
	private void testGrid(Grid g) {
		System.out.println(g.toString());
	}
	
	private void testSecant(Secant s) {
		System.out.println(s.toString());
	}
	
	private void setInternalEquation() {
		switch (currentEq) {
			case 0: theEquation = powerEq; break;
			case 1: theEquation = parabolaEq; break;
			case 2: theEquation = astroidEq; break;
			default: theEquation = null;	// To cause error later
		}
	}

	// return the seekbar ranges in a Point2D
	public Point2D setEquation(int eq) {
		currentEq = eq;
		setInternalEquation();
		// Need to build new grid to the size and offset of equation
		grid = new Grid(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq),
				gAttr.getOffsetY(currentEq));
		grid.setBmp(bg);

		leftSecant = new Secant(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq),
				0, gAttr.getSecantMid(currentEq), gAttr.getStepX(currentEq));
		leftSecant.setColor(Color.MAGENTA);
		leftSecant.setBmp(bg);

		rightSecant = new Secant(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq),
				gAttr.getSecantMid(currentEq), gAttr.getSecantEnd(currentEq),
				gAttr.getStepX(currentEq));
		rightSecant.setColor(Color.RED);
		rightSecant.setBmp(bg);
		presetSecants();

		leftPoint = new GraphPoint(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		leftPoint.setColor(Color.MAGENTA);
		leftPoint.setBmp(bg);

		rightPoint = new GraphPoint(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		rightPoint.setColor(Color.RED);
		rightPoint.setBmp(bg);

		centerPoint = new GraphPoint(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		centerPoint.setColor(Color.GREEN);
		centerPoint.setBmp(bg);

		presetPoints();
		fullRedraw = true;
		this.invalidate();
		return getSecantRanges();
	}
	
	public Point2D getSecantRanges() {
		return new Point2D(leftSecant.getEndX(),
				rightSecant.getEndX() - rightSecant.getStartX());
	}
	
	public void setLeftSecant(int progress) {
		int idx = (int)leftSecant.getStartX() + progress;
		if(idx < 0) idx = 0;
		leftSecant.setP(theEquation.getData(progress));
		// Set the left point while we are here...
		leftPoint.setP(theEquation.getData(progress));
		this.invalidate();
	}

	public void setRightSecant(int progress) {
		int idx = (int)rightSecant.getStartX() + progress;
		if(idx >= rightSecant.getEndX()) idx = (int)rightSecant.getEndX() - 1;
		rightSecant.setQ(theEquation.getData(idx));
		// Set the right point while we are here...
		rightPoint.setP(theEquation.getData(idx));
		this.invalidate();
	}
}
