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

import android.graphics.Color;
import android.graphics.Paint.Style;

public class ParabolaEq extends Equation {

	//TODO: Need to add color schemes later. Schemes will be stored in XML.
	public ParabolaEq(double sizeRatio, double offsetX,	double offsetY,
			double startX, double endX, double stepX, String name) {
		super(sizeRatio, offsetX, offsetY, startX, endX, stepX, name);
		double x, y;
		for(x = startX; x <= endX; x += stepX) {
			y = Math.abs(x * x - 1.0);
			this.data.add(new Point2D(x, y));
		}
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(2);
		paint.setStyle(Style.STROKE);
	}
}
