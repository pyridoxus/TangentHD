package cmcculloch1.csc212;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class GraphAttributes extends View {

	private int[] sizeRatio = new int[3];
	private int[] offsetX = new int[3];
	private int[] offsetY = new int[3];
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

	private void init_res(Context context, AttributeSet attrs) {
		String s;
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.GraphAttributes);

		s = a.getString(R.styleable.GraphAttributes_sizeRatio1);
        if (s != null) {
            sizeRatio[0] = Integer.parseInt(s);
            System.out.println(Integer.toString(sizeRatio[0]));
        }
        s = a.getString(R.styleable.GraphAttributes_offsetX1);
        if (s != null) {
        	offsetX[0] = Integer.parseInt(s);
            System.out.println(Integer.toString(offsetX[0]));
        }
        s = a.getString(R.styleable.GraphAttributes_offsetY1);
        if (s != null) {
        	offsetY[0] = Integer.parseInt(s);
            System.out.println(Integer.toString(offsetY[0]));
        }
        s = a.getString(R.styleable.GraphAttributes_startX1);
        if (s != null) {
        	startX[0] = Float.parseFloat(s);
            System.out.println(Float.toString(startX[0]));
        }
        s = a.getString(R.styleable.GraphAttributes_endX1);
        if (s != null) {
        	endX[0] = Float.parseFloat(s);
            System.out.println(Float.toString(endX[0]));
        }
        s = a.getString(R.styleable.GraphAttributes_name1);
        if (s != null) {
            name[0] = s;
            System.out.println(name[0]);
        }
        
		s = a.getString(R.styleable.GraphAttributes_sizeRatio2);
        if (s != null) {
            sizeRatio[1] = Integer.parseInt(s);
            System.out.println(Integer.toString(sizeRatio[1]));
        }
        s = a.getString(R.styleable.GraphAttributes_offsetX2);
        if (s != null) {
        	offsetX[1] = Integer.parseInt(s);
            System.out.println(Integer.toString(offsetX[1]));
        }
        s = a.getString(R.styleable.GraphAttributes_offsetY2);
        if (s != null) {
        	offsetY[1] = Integer.parseInt(s);
            System.out.println(Integer.toString(offsetY[1]));
        }
        s = a.getString(R.styleable.GraphAttributes_startX2);
        if (s != null) {
        	startX[1] = Float.parseFloat(s);
            System.out.println(Float.toString(startX[1]));
        }
        s = a.getString(R.styleable.GraphAttributes_endX2);
        if (s != null) {
        	endX[1] = Float.parseFloat(s);
            System.out.println(Float.toString(endX[1]));
        }
        s = a.getString(R.styleable.GraphAttributes_name2);
        if (s != null) {
            name[1] = s;
            System.out.println(name[1]);
        }
        
		s = a.getString(R.styleable.GraphAttributes_sizeRatio3);
        if (s != null) {
            sizeRatio[2] = Integer.parseInt(s);
            System.out.println(Integer.toString(sizeRatio[2]));
        }
        s = a.getString(R.styleable.GraphAttributes_offsetX3);
        if (s != null) {
        	offsetX[2] = Integer.parseInt(s);
            System.out.println(Integer.toString(offsetX[2]));
        }
        s = a.getString(R.styleable.GraphAttributes_offsetY3);
        if (s != null) {
        	offsetY[2] = Integer.parseInt(s);
            System.out.println(Integer.toString(offsetY[2]));
        }
        s = a.getString(R.styleable.GraphAttributes_startX3);
        if (s != null) {
        	startX[2] = Float.parseFloat(s);
            System.out.println(Float.toString(startX[2]));
        }
        s = a.getString(R.styleable.GraphAttributes_endX3);
        if (s != null) {
        	endX[2] = Float.parseFloat(s);
            System.out.println(Float.toString(endX[2]));
        }
        s = a.getString(R.styleable.GraphAttributes_name3);
        if (s != null) {
            name[2] = s;
            System.out.println(name[2]);
        }
        
        a.recycle();
	}
}
