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
	private int[] secantMid = new int[3];
	private int[] secantEnd = new int[3];
	private float[] leftSlope = new float[3];
	private float[] leftOffset = new float[3];
	private float[] rightSlope = new float[3];
	private float[] rightOffset = new float[3];
	
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

	public int getSecantMid(int idx) {
		return this.secantMid[idx];
	}
	
	public int getSecantEnd(int idx) {
		return this.secantEnd[idx];
	}
	
	public float getLeftSlope(int idx) {
		return leftSlope[idx];
	}

	public void setLeftSlope(float[] leftSlope) {
		this.leftSlope = leftSlope;
	}

	public float getLeftOffset(int idx) {
		return leftOffset[idx];
	}

	public void setLeftOffset(float[] leftOffset) {
		this.leftOffset = leftOffset;
	}

	public float getRightSlope(int idx) {
		return rightSlope[idx];
	}

	public void setRightSlope(float[] rightSlope) {
		this.rightSlope = rightSlope;
	}

	public float getRightOffset(int idx) {
		return rightOffset[idx];
	}

	public void setRightOffset(float[] rightOffset) {
		this.rightOffset = rightOffset;
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
		secantMid[0] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_secantMid1));
		secantEnd[0] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_secantEnd1));
		leftSlope[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_leftSlope1));
		leftOffset[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_leftOffset1));
		rightSlope[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_rightSlope1));
		rightOffset[0] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_rightOffset1));
        
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
		secantMid[1] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_secantMid2));
		secantEnd[1] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_secantEnd2));
		leftSlope[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_leftSlope2));
		leftOffset[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_leftOffset2));
		rightSlope[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_rightSlope2));
		rightOffset[1] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_rightOffset2));
        
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
		secantMid[2] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_secantMid3));
		secantEnd[2] = Integer.parseInt(
				a.getString(R.styleable.GraphAttributes_secantEnd3));
		leftSlope[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_leftSlope3));
		leftOffset[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_leftOffset3));
		rightSlope[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_rightSlope3));
		rightOffset[2] = Float.parseFloat(
				a.getString(R.styleable.GraphAttributes_rightOffset3));
        
        a.recycle();
	}
}
