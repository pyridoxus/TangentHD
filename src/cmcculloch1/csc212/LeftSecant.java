package cmcculloch1.csc212;

import android.graphics.Canvas;

public class LeftSecant extends Secant {

	public LeftSecant(double sizeRatio, double offsetX, double offsetY,
			double startX, double endX, double stepX) {
		super(sizeRatio, offsetX, offsetY, startX, endX, stepX);
		// TODO Auto-generated constructor stub
	}

	public void draw(Canvas canvas) {
		super.draw(canvas, true);
	}
}
