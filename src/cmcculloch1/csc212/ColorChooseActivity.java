package cmcculloch1.csc212;

import cmcculloch1.csc212.R;
import cmcculloch1.csc212.ColorChooseActivity.ColorOnItemSelectedListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ColorChooseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.colormenu);

	    Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.color_array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    int c = getIntent().getIntExtra(getPackageName() + ".itemColor", 0);
	    Log.i(getClass().getSimpleName(), "Color: " + c);
	    spinner.setAdapter(adapter);
	    spinner.setSelection(c);
	    spinner.setOnItemSelectedListener(new ColorOnItemSelectedListener());
	}

	public class ColorOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	      Toast.makeText(parent.getContext(), "The color is " +
	          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	
}
