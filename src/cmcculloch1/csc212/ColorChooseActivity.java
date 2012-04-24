package cmcculloch1.csc212;

import cmcculloch1.csc212.R;
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
	int[] colors;
	String[] list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(), "OnCreate()");
		super.onCreate(savedInstanceState);
		colors = new int[10];
		getColors();
	    setContentView(R.layout.colormenu);
	    Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	    		this, R.array.color_array,
	    		android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    int c = getIntent().getIntExtra(getPackageName() + ".itemColor", 0);
	    int d;
	    for(d = 0; d < colors.length; d++) if(c == colors[d]) break;
	    spinner.setAdapter(adapter);
	    spinner.setSelection(d);
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
	
	private void getColors() {
		colors[0] = getResources().getInteger(R.color.magenta); 
		colors[1] = getResources().getInteger(R.color.red); 
		colors[2] = getResources().getInteger(R.color.orange); 
		colors[3] = getResources().getInteger(R.color.yellow); 
		colors[4] = getResources().getInteger(R.color.green); 
		colors[5] = getResources().getInteger(R.color.cyan); 
		colors[6] = getResources().getInteger(R.color.blue); 
		colors[7] = getResources().getInteger(R.color.violet); 
		colors[8] = getResources().getInteger(R.color.black);
		list = getResources().getStringArray(R.array.color_array);
	}
}
