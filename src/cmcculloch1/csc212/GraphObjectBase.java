package cmcculloch1.csc212;

import android.graphics.Bitmap;
import android.graphics.Paint;

public class GraphObjectBase {
	protected Bitmap bmp;
	protected Paint paint;
	public GraphObjectBase() {
		this.bmp = null;
		this.paint = new Paint();
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
