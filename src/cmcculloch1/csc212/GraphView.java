package cmcculloch1.csc212;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {
	Bitmap bg = null;	// Background bitmap holds more static image of graph
	Bitmap fg = null;	// Foreground bitmap holds more dynamic image of secants
	GraphAttributes gAttr;	// Attributes of the 3 equations
	PowerEq powerEq = null;			// y = x^3
	ParabolaEq parabolaEq = null;	// y = |x^2 - 1|
	AstroidEq astroidEq = null;		// x^(2/3) + y^(2/3) = 1
	Equation theEquation = null;	// The current equation object
	Grid grid = null;				// The background grid
	Secant leftSecant = null;		// Secant left of point of interest
	Secant rightSecant = null;		// Secant right of point of interest
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

	public void setAttributes(GraphAttributes gAttr) {
		System.out.println("Inside setAttributes()");
		this.gAttr = gAttr;
		buildGraphObjects();
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
			leftSecant.setBmp(fg);
			rightSecant.setBmp(fg);
			setInternalEquation();
//			testInterpolation();
//			testEquation(powerEq);
//			testEquation(parabolaEq);
//			testEquation(astroidEq);
//			testGrid(grid);
			testSecant(leftSecant);
			testSecant(rightSecant);
		}
		if(fullRedraw == true) {
			grid.draw(canvas);
			theEquation.draw(canvas);
			fullRedraw = false;
		}
		fg = bg.copy(bg.getConfig(), true);
		leftSecant.draw(canvas);
		rightSecant.draw(canvas);
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
		fg = Bitmap.createBitmap(bg);
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

	public void setEquation(int eq) {
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
		fullRedraw = true;
		this.invalidate();
	}
}
