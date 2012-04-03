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
		testInterpolation();
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
	}
	
	private void testInterpolation() {
		GraphInterpolate interpolateTest = 
				new GraphInterpolate(gAttr.getSizeRatio(0),			// 32
									 gAttr.getOffsetX(0),			// 0
									 gAttr.getOffsetY(0),			// 0
									 this.getMeasuredWidth(),		// ?
									 this.getMeasuredHeight());		// ?
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
}
