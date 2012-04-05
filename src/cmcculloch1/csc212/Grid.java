package cmcculloch1.csc212;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Grid {
	//TODO: It looks as though some of this stuff can be put into a parent
	//class... the secant lines and points will also need the following data...
	protected float sizeRatio;
	protected float offsetX;
	protected float offsetY;
	protected Bitmap bmp;
	protected Paint paint;

	public Grid(Bitmap bmp, float sizeRatio, float offsetX, float offsetY) {
		this.bmp = bmp;
		this.sizeRatio = sizeRatio;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.paint = new Paint();
	}

	public void draw(Canvas canvas) {
	}
}
