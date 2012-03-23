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
	float x, y;	// Graph coordinates
	GraphPoint gp;	// Object to convert from graph coordinate system to bmp 
	
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
		gp.setH(getHeight());
		gp.setW(getWidth());
		gp.setResolution(32);
		Canvas c = new Canvas(bg);
		c.drawColor(getResources().getColor(R.color.white));
		drawGrid();
		fg = Bitmap.createBitmap(bg);
	}
	
	private void drawGrid() {
		Paint p = new Paint();
		Canvas c = new Canvas(bg);
		p.setColor(getResources().getColor(R.color.black));
		gp.y = 0;
		for(x = -3; x < 4; x++) {
			gp.x = x;
			gp.convert(x, 0);
			c.drawLine(gp.x, 0, gp.x, gp.getH(), p);
		}
		for(y = -3; y < 4; y++) {
			gp.y = y;
			gp.convert(0, y);
			c.drawLine(0, gp.y, gp.getW(), gp.y, p);
		}
	}
	
}
