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
	private String[] name = new String[3];
	
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
	
	public String getName(int idx) {
		return this.name[idx];
	}
	
	private void init_res(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.GraphAttributes);

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
		name[0] = a.getString(R.styleable.GraphAttributes_name1);
        
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
		name[1] = a.getString(R.styleable.GraphAttributes_name2);
        
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
		name[2] = a.getString(R.styleable.GraphAttributes_name3);
        
        a.recycle();
	}
}
