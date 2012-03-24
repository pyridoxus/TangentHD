package cmcculloch1.csc212;

import java.util.ArrayList;

public class Equation {
	private ArrayList<Point2D> data;
	
	// Create the data for the equation referred as id using the domain given
	// by xmin and xmax. The resolution is the interval between used values
	// while counting from xmin to xmax.
	public Equation(int id, double xmin, double xmax, double resolution) {
		double x, y;
		data = new ArrayList<Point2D>();
		y = 0;
		System.out.println("id: " + Integer.toString(id));
		for(x = xmin; x < xmax; x += resolution) {
			switch (id) {
				case R.id.equation1:
					y = Math.pow(x, 3);
				break;
				case R.id.equation2:
//					x ^ (2/3) + y ^ (2/3) = 1
//					y ^ (2/3) = 1 - x ^ (2/3)
//					y = (1 - x ^ (2/3))  ^ (3/2)
					
					y = Math.pow(1 - Math.pow(x, 2 / 3), 3 / 2);
				break;
				case R.id.equation3:
					y = Math.abs(x * x - 1);
				break;
			}
			System.out.println("(" + Double.toString(x) + ", " + 
							Double.toString(y) + ")");
			
			data.add(new Point2D(x, y));
		}
	}

	public Object getPoints(){
		return data.clone();
	}
}
