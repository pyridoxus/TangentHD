package cmcculloch1.csc212;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class GraphAttributes extends View {

	private int sizeRatio;
	
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
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.GraphAttributes);
        String s = a.getString(R.styleable.GraphAttributes_sizeRatio);
        if (s != null) {
            sizeRatio = Integer.parseInt(s);
            System.out.println(Integer.toString(sizeRatio));
        }
        a.recycle();
	}
}
