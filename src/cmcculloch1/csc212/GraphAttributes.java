package cmcculloch1.csc212;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class GraphAttributes extends View {

	private float[] sizeRatio = new float[3];
	private float[] offsetX = new float[3];
	private float[] offsetY = new float[3];
	private float[] startX = new float[3];
	private float[] endX = new float[3];
	private float[] stepX = new float[3];
	private String[] name = new String[3];
	private int[] seekLeftStart = new int[3];
	private int[] seekLeftEnd = new int[3];
	private int[] seekRightStart = new int[3];
	private int[] seekRightEnd = new int[3];
	private float[] pointX = new float[3];
	private float[] pointY = new float[3];
	
	public GraphAttributes(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GraphAttributes(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_res(context, attrs);
	}

	public GraphAttributes(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_res(context, attrs);
	}

	public float getSizeRatio(int idx) {
		return this.sizeRatio[idx];
	}
	
	public float getOffsetX(int idx) {
		return this.offsetX[idx];
	}
	
	public float getOffsetY(int idx) {
		return this.offsetY[idx];
	}
	
	public float getStartX(int idx) {
		return this.startX[idx];
	}
	
	public float getEndX(int idx) {
		return this.endX[idx];
	}
	
	public float getStepX(int idx) {
		return this.stepX[idx];
	}
	
	public String getName(int idx) {
		return this.name[idx];
	}
	
	public int getSeekLeftStart(int idx) {
		return this.seekLeftStart[idx];
	}
	
	public int getSeekLeftEnd(int idx) {
		return this.seekLeftEnd[idx];
	}
	
	public int getSeekRighttStart(int idx) {
		return this.seekRightStart[idx];
	}
	
	public int getSeekRightEnd(int idx) {
		return this.seekRightEnd[idx];
	}

	public float getPointX(int idx) {
		return this.pointX[idx];
	}
	
	public float getPointY(int idx) {
		return this.pointY[idx];
	}
	
	private void init_res(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.GraphAttributes);
		//TODO: Need to add color schemes later. Schemes will be stored in XML.

		// First equation (y = x ^ 3)
		sizeRatio[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_sizeRatio1));
		offsetX[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_offsetX1));
		offsetY[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_offsetY1));
		startX[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_startX1));
		endX[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_endX1));
		stepX[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_stepX1));
		name[0] = a.getString(R.styleable.GraphAttributes_name1);
		seekLeftStart[0] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekLeftStart1));
		seekLeftEnd[0] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekLeftEnd1));
		seekRightStart[0] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekRightStart1));
		seekRightEnd[0] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekRightEnd1));
		pointX[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_pointX1));
		pointY[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_pointY1));
        
		// Second equation (y = | x ^ 2 - 1 |)
		sizeRatio[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_sizeRatio2));
		offsetX[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_offsetX2));
		offsetY[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_offsetY2));
		startX[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_startX2));
		endX[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_endX2));
		stepX[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_stepX2));
		name[1] = a.getString(R.styleable.GraphAttributes_name2);
		seekLeftStart[1] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekLeftStart2));
		seekLeftEnd[1] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekLeftEnd2));
		seekRightStart[1] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekRightStart2));
		seekRightEnd[1] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekRightEnd2));
		pointX[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_pointX2));
		pointY[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_pointY2));
        
		// Third equation (x ^ (2/3) + y ^ (2/3) = 1)
		sizeRatio[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_sizeRatio3));
		offsetX[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_offsetX3));
		offsetY[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_offsetY3));
		startX[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_startX3));
		endX[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_endX3));
		stepX[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_stepX3));
		name[2] = a.getString(R.styleable.GraphAttributes_name3);
		seekLeftStart[2] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekLeftStart3));
		seekLeftEnd[2] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekLeftEnd3));
		seekRightStart[2] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekRightStart3));
		seekRightEnd[2] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_seekRightEnd3));
		pointX[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_pointX3));
		pointY[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_pointY3));
        
        a.recycle();
	}
}
