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

public class RightSecant extends Secant {

	public RightSecant(double sizeRatio, double offsetX, double offsetY,
			double startX, double endX, double stepX) {
		super(sizeRatio, offsetX, offsetY, startX, endX, stepX);
		// TODO Auto-generated constructor stub
	}

	public void draw(Canvas canvas) {
		super.draw(canvas, false);
	}

}
