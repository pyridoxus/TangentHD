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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;

public class Grid extends GraphInterpolate {
	private float borderE, borderS, borderN, borderW;
	
	public Grid(double sizeRatio, double offsetX, double offsetY) {
		super(sizeRatio, offsetX, offsetY);
		paint.setStyle(Style.STROKE);
	}

	public void draw(Canvas canvas) {
		paint.setColor(Color.BLACK);
		canvas.drawPaint(paint);
//		System.out.println("Drawing grid...");
		
		borderE = (float)(bmp.getWidth() / 2.0 / sizeRatio + offsetX);
		float temp = borderE % 1;
		borderE = borderE - temp + 1;
		borderW = -borderE;
		borderN = (float)(bmp.getHeight() / 2.0 / sizeRatio + offsetY);
		temp = borderN % 1;
		borderN = borderN - temp + 1;
		borderS = -borderN;
		paint.setColor(Color.LTGRAY);
		drawX(canvas);
		drawY(canvas);
	}

	public void drawX(Canvas canvas) {
		Point2D s = new Point2D();
		Point2D t = new Point2D();
		for(float x = borderW; x <= borderE; x += 1.0 ) {
			graphToBmp(x, borderN);
			s.setX(getInterpX());
			s.setY(getInterpY());
			graphToBmp(x, borderS);
			t.setX(getInterpX());
			t.setY(getInterpY());
			if(x == 0) paint.setStrokeWidth(2);
			else paint.setStrokeWidth(1);
			canvas.drawLine((float)s.getX(), (float)s.getY(),
					(float)t.getX(), (float)t.getY(), paint);
		}
	}
	
	public void drawY(Canvas canvas) {
		Point2D s = new Point2D();
		Point2D t = new Point2D();
		for(float y = borderS; y <= borderN; y += 1.0 ) {
			graphToBmp(borderW, y);
			s.setX(getInterpX());
			s.setY(getInterpY());
			graphToBmp(borderE, y);
			t.setX(getInterpX());
			t.setY(getInterpY());
			if(y == 0) paint.setStrokeWidth(2);
			else paint.setStrokeWidth(1);
			canvas.drawLine((float)s.getX(), (float)s.getY(),
					(float)t.getX(), (float)t.getY(), paint);
		}
	}
	
	@Override
	public String toString() {
		String s;
		s = "--- Grid Info ---\n";
		s += "grid@" + this.hashCode() + "\n";
		s += "sizeRatio = " + Double.toString(sizeRatio) + "\n";
		s += "offsetX = " + Double.toString(offsetX) + "\n";
		s += "offsetY = " + Double.toString(offsetY) + "\n";
		s += "--- End Grid Info ---\n";
		
		return s;
	}
}
