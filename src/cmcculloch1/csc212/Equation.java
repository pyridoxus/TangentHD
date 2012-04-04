package cmcculloch1.csc212;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Equation {
	protected ArrayList<Point2D> data;
	protected float sizeRatio;
	protected float offsetX;
	protected float offsetY;
	protected float startX;
	protected float endX;
	protected float stepX;
	protected String name;
	protected Bitmap bmp;
	protected Paint paint;
	
	public Equation(Bitmap bmp, float sizeRatio, float offsetX, float offsetY,
					float startX, float endX, float stepX, String name) {
		this.bmp = bmp;
		this.sizeRatio = sizeRatio;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.startX = startX;
		this.endX = endX;
		this.stepX = stepX;
		this.name = name;
		this.data = new ArrayList<Point2D>();
		this.paint = new Paint();
	}

	public void draw(Canvas canvas) {
		// Override me.
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

	public float getStartX() {
		return startX;
	}

	public void setStartX(float startX) {
		this.startX = startX;
	}

	public float getEndX() {
		return endX;
	}

	public void setEndX(float endX) {
		this.endX = endX;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point2D getData(int idx) {
		return data.get(idx);
	}
}
