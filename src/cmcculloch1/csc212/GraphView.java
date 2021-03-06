//This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.
package cmcculloch1.csc212;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

// DISCLAIMER:

// This project is a good example of needing to plan ahead. There are some
// bad (or at least less efficient or messy) ways of setting up objects in
// both this View and the activity associated with it. I suspect there is a
// much better way to set up objects. First, I must better understand the way
// the Android objects are set up, when things occur, what objects exist, etc.
// Another example is that most of the code in the entire project was set in
// stone before the introduction to intents, bundles, and shared preferences.
// Ideally, this project should be rewritten with those concepts in mind.
// There are other notes sprinkled through this and other files in the project.
public class GraphView extends View {
	Bitmap bg = null;	// Background bitmap holds more static image of graph
	GraphAttributes gAttr;	// Attributes of the 3 equations
	PowerEq powerEq = null;			// y = x^3
	ParabolaEq parabolaEq = null;	// y = |x^2 - 1|
	AstroidEq astroidEq = null;		// x^(2/3) + y^(2/3) = 1
	Equation theEquation = null;	// The current equation object
	Grid grid = null;				// The background grid
	LeftSecant leftSecant = null;		// Secant left of point of interest
	RightSecant rightSecant = null;		// Secant right of point of interest
	GraphPoint leftPoint = null;	// Point left of point of interest
	GraphPoint rightPoint = null;	// Point right of point of interest
	GraphPoint centerPoint = null;	// Point of interest
	TangentResolver tangent = null;	// Graph the secant lines at limit
	int currentEq = 0;				// Index of current equation
	
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
//		System.out.println("Inside setAttributes()");
		this.gAttr = gAttr;
		buildGraphObjects();
		return getSecantRanges();
	}
	
	private void buildGraphObjects() {
		// This can be done any time, because it does not rely on the
		// existence of the bitmaps. This is done just after we get the
		// attributes inflated.
//		System.out.println("Inside buildGraphObjects()");
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
		leftSecant = new LeftSecant(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq),
				0, gAttr.getSecantMid(currentEq),
				gAttr.getStepX(currentEq));
		leftSecant.setColor(Color.MAGENTA);
		rightSecant = new RightSecant(gAttr.getSizeRatio(currentEq),
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

		tangent = new TangentResolver(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		presetTangent();
	}
	
	private void presetSecants() {
		// Set the end points of line segment of secant to present positions
		// that use indices within the equation data, and not recalculating
		// everything.
		int idx = 0;
		leftSecant.setP(theEquation.getData(idx));
		rightSecant.setP(theEquation.getData(gAttr.getSecantEnd(currentEq) - 1));
		idx = gAttr.getSecantMid(currentEq);
		leftSecant.setQ(theEquation.getData(idx));
		rightSecant.setQ(theEquation.getData(idx));
	}
	
	private void presetPoints() {
		int idx = 0;
		leftPoint.setP(theEquation.getData(idx));
		rightPoint.setP(theEquation.getData(gAttr.getSecantEnd(currentEq) - 1));
		centerPoint.setP(theEquation.getData(gAttr.getSecantMid(currentEq)));
	}
	
	private void presetTangent() {
		tangent.setLeftOffset(gAttr.getLeftOffset(currentEq));
		tangent.setLeftSlope(gAttr.getLeftSlope(currentEq));
		tangent.setRightOffset(gAttr.getRightOffset(currentEq));
		tangent.setRightSlope(gAttr.getRightSlope(currentEq));
		tangent.setMidPoint(theEquation.getData(gAttr.getSecantMid(currentEq)));
		tangent.setState(0);	// Clear the tangent lines
		tangent.setEquation(currentEq);	// Some equations cause different color
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// Tried to think of way to put all GraphObjectBase objects into an
		// array to clean up the code a bit. However, I don't always use all
		// objects at the same time as I do in this function. That would mean
		// creating XML resources to name the objects (essentially that is
		// enumeration) so that I can access them individually from the array.
		// I ran out of time to try implementing that idea.
		super.onDraw(canvas);
		if(bg == null) {
			// First time this is called, so create everything according to
			// the size of the layout.
			createBMPs();
			if(bg != null) {
				powerEq.setBmp(bg);
				parabolaEq.setBmp(bg);
				astroidEq.setBmp(bg);
				grid.setBmp(bg);
				leftSecant.setBmp(bg);
				rightSecant.setBmp(bg);
				leftPoint.setBmp(bg);
				rightPoint.setBmp(bg);
				centerPoint.setBmp(bg);
				tangent.setBmp(bg);
				setInternalEquation();
	//			testInterpolation();
	//			testEquation(powerEq);
	//			testEquation(parabolaEq);
	//			testEquation(astroidEq);
	//			testGrid(grid);
	//			testSecant(leftSecant);
	//			testSecant(rightSecant);
	//			System.out.println("Initializing everything in draw...");
			}
		}
		grid.draw(canvas);
		theEquation.draw(canvas);
		leftSecant.draw(canvas);
		rightSecant.draw(canvas);
		leftPoint.draw(canvas);
		rightPoint.draw(canvas);
		tangent.draw(canvas);
		centerPoint.draw(canvas);
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
		// Original idea was to have one bitmap contain the grid and equation,
		// blit this bitmap to another bitmap, then draw secants. However, I
		// ran out of time trying to figure it out. Android or Java bitmaps do
		// not seem to behave as intuitively as I originally thought.
		// Only using one bitmap for now.
		bg = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
								Bitmap.Config.ARGB_8888);
	}
	
	private void init() {
		System.out.println("Inside init()");
	}
	
//	private void testInterpolation() {
//		GraphInterpolate interpolateTest = 
//				new GraphInterpolate(gAttr.getSizeRatio(0),
//									 gAttr.getOffsetX(0),
//									 gAttr.getOffsetY(0));
//		interpolateTest.setBmp(bg);
//		Point2D temp = new Point2D();
////		System.out.println("Printing conversions...");
//		interpolateTest.graphToBmp(0, 0);
//		temp.setX(interpolateTest.getInterpX());
//		temp.setY(interpolateTest.getInterpY());
////		System.out.println("(" + Double.toString(temp.getX()) + ", " +
////								Double.toString(temp.getY()) + ")");
//		interpolateTest.graphToBmp(1, 1);
//		temp.setX(interpolateTest.getInterpX());
//		temp.setY(interpolateTest.getInterpY());
//		System.out.println("(" + Double.toString(temp.getX()) + ", " +
//								Double.toString(temp.getY()) + ")");
//		interpolateTest.graphToBmp(-1, -1);
//		temp.setX(interpolateTest.getInterpX());
//		temp.setY(interpolateTest.getInterpY());
//		System.out.println("(" + Double.toString(temp.getX()) + ", " +
//								Double.toString(temp.getY()) + ")");
//	}

//	private void testEquation(Equation eq) {
//		System.out.println("Inside testEquation");
//		System.out.println(eq.toString());
//	}
//	
//	private void testGrid(Grid g) {
//		System.out.println(g.toString());
//	}
//	
//	private void testSecant(Secant s) {
//		System.out.println(s.toString());
//	}
//	
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

		leftSecant = new LeftSecant(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq),
				0, gAttr.getSecantMid(currentEq), gAttr.getStepX(currentEq));
		leftSecant.setColor(Color.MAGENTA);
		leftSecant.setBmp(bg);

		rightSecant = new RightSecant(gAttr.getSizeRatio(currentEq),
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
		
		tangent = new TangentResolver(gAttr.getSizeRatio(currentEq),
				gAttr.getOffsetX(currentEq), gAttr.getOffsetY(currentEq));
		tangent.setBmp(bg);
		presetTangent();
		
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
		tangent.resolve();
		if(progress == gAttr.getSecantMid(currentEq)) tangent.setState(1);
		else tangent.clearState(1);
		this.invalidate();
	}

	public void setRightSecant(int progress) {
		int idx = (int)rightSecant.getStartX() + progress;
		if(idx >= rightSecant.getEndX()) idx = (int)rightSecant.getEndX() - 1;
		rightSecant.setP(theEquation.getData(idx));
		// Set the right point while we are here...
		rightPoint.setP(theEquation.getData(idx));
		tangent.resolve();
		if(progress == 0) tangent.setState(2);
		else tangent.clearState(2);
		this.invalidate();
	}
	
	public int getCurrentEquationIdx() {
		return currentEq;
	}
}
