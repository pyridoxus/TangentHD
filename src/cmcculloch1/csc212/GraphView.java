package cmcculloch1.csc212;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {
	Bitmap bg;	// Background bitmap to hold the more static image of graph
	Bitmap fg;	// Foreground bitmap to hold the more dynamic image of secants

	public GraphView(Context context) {
		// TODO Auto-generated method stub
		super(context);
	}

	public GraphView(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		super(context, attrs);
	}

	public GraphView(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated method stub
		super(context, attrs, defStyle);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		createBMPs();
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
		System.out.println("H: " + Integer.toString(getMeasuredHeight()));
		System.out.println("W: " + Integer.toString(getMeasuredWidth()));
		bg = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
								Bitmap.Config.ARGB_8888);
		fg = Bitmap.createBitmap(bg);
	}
}
