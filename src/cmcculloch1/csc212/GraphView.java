package cmcculloch1.csc212;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {
	Bitmap bg = null;	// Background bitmap holds more static image of graph
	Bitmap fg = null;	// Foreground bitmap holds more dynamic image of secants
	GraphAttributes gAttr;	// Attributes of the 3 equations
	PowerEq powerEq = null;			// y = x^3
	ParabolaEq parabolaEq = null;	// y = |x^2 - 1|
	AstroidEq astroidEq = null;		// x^(2/3) + y^(2/3) = 1
	
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
		this.gAttr = gAttr;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		createBMPs();
		powerEq.setBmp(bg);
		parabolaEq.setBmp(bg);
		astroidEq.setBmp(bg);
		testInterpolation();
		testEquation(powerEq);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
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
		powerEq = new PowerEq(gAttr.getSizeRatio(0), gAttr.getOffsetX(0),
				gAttr.getOffsetY(0), gAttr.getStartX(0), gAttr.getEndX(0),
							gAttr.getStepX(0), gAttr.getName(0));
		parabolaEq = new ParabolaEq(gAttr.getSizeRatio(1), gAttr.getOffsetX(1),
				gAttr.getOffsetY(1), gAttr.getStartX(1), gAttr.getEndX(1),
							gAttr.getStepX(1), gAttr.getName(1));
		astroidEq = new AstroidEq(gAttr.getSizeRatio(2), gAttr.getOffsetX(2),
				gAttr.getOffsetY(2), gAttr.getStartX(2), gAttr.getEndX(2),
							gAttr.getStepX(2), gAttr.getName(2));
	}
	
	private void testInterpolation() {
		GraphInterpolate interpolateTest = 
				new GraphInterpolate(gAttr.getSizeRatio(0),
									 gAttr.getOffsetX(0),
									 gAttr.getOffsetY(0));
		interpolateTest.setBmp(bg);
		Point2D temp;
		System.out.println("Printing conversions...");
		temp = interpolateTest.graphToBmp(new Point2D(0, 0));
		System.out.println("(" + Double.toString(temp.getX()) + ", " +
								Double.toString(temp.getY()) + ")");
		temp = interpolateTest.graphToBmp(new Point2D(1, 1));
		System.out.println("(" + Double.toString(temp.getX()) + ", " +
								Double.toString(temp.getY()) + ")");
		temp = interpolateTest.graphToBmp(new Point2D(-1, -1));
		System.out.println("(" + Double.toString(temp.getX()) + ", " +
								Double.toString(temp.getY()) + ")");
	}

	private void testEquation(Equation eq) {
		System.out.println("Inside testEquation");
		System.out.println(eq.toString());
	}
}
