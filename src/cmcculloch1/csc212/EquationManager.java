package cmcculloch1.csc212;

import java.util.ArrayList;

public class EquationManager {
	private ArrayList<Equation> e;
	public EquationManager(double xmin, double xmax, double resolution) {
		e = new ArrayList<Equation>();
		Equation a = new Equation(R.id.equation1, xmin, xmax, resolution);
		e.add(a);
		a = new Equation(R.id.equation2, xmin, xmax, resolution);
		e.add(a);
		a = new Equation(R.id.equation3, xmin, xmax, resolution);
		e.add(a);
	}

}
