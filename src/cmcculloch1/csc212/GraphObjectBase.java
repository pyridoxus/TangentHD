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

import android.graphics.Bitmap;
import android.graphics.Paint;

public class GraphObjectBase {
	protected Bitmap bmp = null;
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
	public void setColor(int color) {
		paint.setColor(color);
	}
	public int getColor() {
		return paint.getColor();
	}
}
