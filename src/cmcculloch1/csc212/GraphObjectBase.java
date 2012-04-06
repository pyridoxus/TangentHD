package cmcculloch1.csc212;

import android.graphics.Bitmap;
import android.graphics.Paint;

public class GraphObjectBase {
	protected float sizeRatio;
	protected float offsetX;
	protected float offsetY;
	protected Bitmap bmp;
	protected Paint paint;
	public GraphObjectBase(Bitmap bmp, float sizeRatio, float offsetX,
							float offsetY) {
		this.bmp = bmp;
		this.sizeRatio = sizeRatio;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.paint = new Paint();
	}
	public float getSizeRatio() {
		return sizeRatio;
	}
	public void setSizeRatio(float sizeRatio) {
		this.sizeRatio = sizeRatio;
	}
	public float getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}
	public float getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}
	public Bitmap getBmp() {
		return bmp;
	}
	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}
	public Paint getPaint() {
		return paint;
	}
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
}
