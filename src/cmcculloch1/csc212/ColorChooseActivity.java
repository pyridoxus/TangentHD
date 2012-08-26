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

import cmcculloch1.csc212.R;
import android.app.Activity;
import android.content.SharedPreferences;
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
	int item;
	int choice;
	private String APP_STATE_NAME;
	private String KEY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(), "OnCreate()");
		super.onCreate(savedInstanceState);
		colors = new int[10];
		getColors();
	    setContentView(R.layout.colormenu);
        APP_STATE_NAME = getResources().getString(R.string.appStateName);
        KEY = getResources().getString(R.string.key);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	    		this, R.array.color_array,
	    		android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    int c = getIntent().getIntExtra(getPackageName() + ".itemColor", 0);
	    item = getIntent().getIntExtra(getPackageName() + ".item", -1);
	    choice = 0;
	    int d;
	    for(d = 0; d < colors.length; d++) if(c == colors[d]) break;
	    spinner.setAdapter(adapter);
	    spinner.setSelection(d);
	    spinner.setOnItemSelectedListener(new ColorOnItemSelectedListener());
        Log.i(getClass().getSimpleName(), "initial choice: " + d);
	}

    @Override
	protected void onPause() {
        Log.i(getClass().getSimpleName(), "OnPause()");
		super.onPause();
		putColor();
	}

	public class ColorOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	    	choice = pos;
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
		colors[8] = getResources().getInteger(R.color.white);
		list = getResources().getStringArray(R.array.color_array);
	}
	
	private void putColor() {
        Log.i(getClass().getSimpleName(), "In putColor()");
        // Get a SharedPreferences file
        SharedPreferences state = getSharedPreferences(APP_STATE_NAME,MODE_PRIVATE);
          
        // Get a SharedPreferences editor
        SharedPreferences.Editor editor = state.edit();
   
        Log.i(getClass().getSimpleName(), "item = " + item);
        Log.i(getClass().getSimpleName(), "choice = " + choice);
        Log.i(getClass().getSimpleName(), "color = " + colors[choice]);
        // Load the editor with the data
        switch(item) {
        	case 0:
        		editor.putInt(KEY + ".equationColor", colors[choice]);
        	break;
        	case 1:
        		editor.putInt(KEY + ".leftColor", colors[choice]);
        	break;
        	case 2:
        		editor.putInt(KEY + ".rightColor", colors[choice]);
        	break;
        }
		editor.putInt(KEY + ".invalidate", 1);
        // Commit the editor additions
        editor.commit();
    }
}
