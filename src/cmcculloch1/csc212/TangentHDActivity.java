package cmcculloch1.csc212;

import cmcculloch1.csc212.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class TangentHDActivity extends Activity {
	private GraphView graph;
	private SeekBar leftSeekBar;
	private SeekBar rightSeekBar;
	private ImageView equationName;
	private String APP_STATE_NAME;
	private String KEY;
	private int left, right, initPrefs, invalidate;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(), "OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        graph = (GraphView) findViewById(R.id.graphView);
        equationName = (ImageView) findViewById(R.id.imageView3);
        Point2D p = new Point2D(graph.setAttributes(
        				(GraphAttributes)findViewById(R.id.graphAttributes)));
        int leftSeekBarRange = (int)p.getX();
        int rightSeekBarRange = (int)p.getY();
        registerForContextMenu(graph);
        setupLeftSeekBar(leftSeekBarRange);
        setupRightSeekBar(rightSeekBarRange);
        // Get SharedPreferences name from XML string resource
        APP_STATE_NAME = getResources().getString(R.string.appStateName);
        KEY = getResources().getString(R.string.key);
        setEquation(getTangentHDPreferences());
        if(this.initPrefs == 1) {
        	leftSeekBar.setProgress(this.left);
            rightSeekBar.setProgress(this.right);
        }
    }
    
    
    @Override
	protected void onPause() {
        Log.i(getClass().getSimpleName(), "OnPause()");
		super.onPause();
		putTangentHDPreferences();
	}

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getClass().getSimpleName(), "onResume");
    	getTangentHDPreferences();
        if(this.invalidate == 1) {
        	this.invalidate = 0;
        	graph.invalidate();
        }
    }
  
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(getClass().getSimpleName(), "onDestroy");
    }
  
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(getClass().getSimpleName(), "onRestart");
    }
  
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(getClass().getSimpleName(), "onStart");
    }
  
    @Override
    protected void onStop() {
        super.onStop();
       Log.i(getClass().getSimpleName(), "onStop");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,
    			getString(R.string.app_name), duration);
        int m_id = item.getItemId();

        switch (m_id) {
            case R.id.m_graphColor:
            	getColor(0, graph.theEquation.getColor());
            	// Will do something else here in case statement in future
            	toast = Toast.makeText(context,
            			getString(R.string.s_graphColor), duration);
                toast.show();
                graph.invalidate();
                return true;
            case R.id.m_leftColor:
            	getColor(1, graph.leftSecant.getColor());
            	// Will do something else here in case statement in future
            	toast = Toast.makeText(context,
            			getString(R.string.s_leftColor), duration);
                toast.show();
                graph.invalidate();
                return true;
            case R.id.m_rightColor:
            	getColor(2, graph.rightSecant.getColor());
            	// Will do something else here in case statement in future
            	toast = Toast.makeText(context,
            			getString(R.string.s_rightColor), duration);
                toast.show();
                graph.invalidate();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int m_id = item.getItemId();
        if(setEquation(m_id) == false) return super.onContextItemSelected(item);
        return true;
    }
    
    private boolean setEquation(int idx) {
        Context context = getApplicationContext();
        String name;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,
    			getString(R.string.app_name), duration);
        int param = 0;

        switch (idx) {
            case R.id.equation1:
            	name = getString(R.string.equation1);
            	toast = Toast.makeText(context, name, duration);
                equationName.setBackgroundResource(R.drawable.snapshot_parabola_crop);
            	param = 0; // I don't like doing this, but work around it later
            break;
            case R.id.equation2:
            	name = getString(R.string.equation2);
            	toast = Toast.makeText(context, name, duration);
                equationName.setBackgroundResource(R.drawable.snapshot_abs_crop);
            	param = 1;
            break;
            case R.id.equation3:
            	name = getString(R.string.equation3);
            	toast = Toast.makeText(context, name, duration);
                equationName.setBackgroundResource(R.drawable.snapshot_astroid_crop);
            	param = 2;
            break;
            case R.id.equation_reset:
            break;
            default:
                return false; 
        }
        setSeekBarRanges(graph.setEquation(param));
        if(this.initPrefs == 1) {
            SharedPreferences state = getSharedPreferences(APP_STATE_NAME,MODE_PRIVATE);
            if(state != null) {
    	        // Get data
		        int leftColor = state.getInt(KEY + ".leftColor", 0);
		        int rightColor = state.getInt(KEY + ".rightColor", 0);
		        graph.theEquation.setColor(state.getInt(KEY + ".equationColor", 0));
		        graph.leftSecant.setColor(leftColor);
		        graph.leftPoint.setColor(leftColor);
		        graph.rightSecant.setColor(rightColor);
		        graph.rightPoint.setColor(rightColor);
		        leftSeekBar.setBackgroundColor(leftColor);
		        rightSeekBar.setBackgroundColor(rightColor);
		        TextView t = (TextView) findViewById(R.id.textView1);
		        t.setTextColor(leftColor);
		        t = (TextView) findViewById(R.id.textView2);
		        t.setTextColor(rightColor);
		        
//    			this.invalidate = state.getInt(KEY + ".invalidate", 1);

//    	        eq = state.getInt(KEY + ".equation", 0);
//    	        Log.i(getClass().getSimpleName(), "left = " + left);
//    	        Log.i(getClass().getSimpleName(), "right = " + right);
//    	        Log.i(getClass().getSimpleName(), "eq = " + eq);
            }
        }
        toast.show();
        return true;
    }
    
    private void setSeekBarRanges(Point2D p) {
    	leftSeekBar.setMax((int)p.getX());
    	leftSeekBar.setProgress(0);
    	rightSeekBar.setMax((int)p.getY());
    	rightSeekBar.setProgress((int)p.getY());
    }
    
    private void setupLeftSeekBar(int leftSeekBarRange) {
    	leftSeekBar = (SeekBar)findViewById(R.id.seekBar1);
    	leftSeekBar.setMax(leftSeekBarRange);
        leftSeekBar.setOnSeekBarChangeListener(
        				new SeekBar.OnSeekBarChangeListener() {
        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		leftSeekBarCB(R.id.seekBar1, progress);        		
        	}

        	public void onStartTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}

        	public void onStopTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}
        });
        // Make background visible by removing the progress drawable
        leftSeekBar.setProgressDrawable(null);
    }
    
    private void leftSeekBarCB(int ID, int progress) {
    	graph.setLeftSecant(progress);
//    	System.out.println("Left Seekbar: " + Integer.toString(ID) +
//    						" progress: " + progress);
    }

    private void setupRightSeekBar(int rightSeekBarRange) {
    	rightSeekBar = (SeekBar)findViewById(R.id.seekBar2);
    	rightSeekBar.setMax(rightSeekBarRange);
    	rightSeekBar.setOnSeekBarChangeListener(
        				new SeekBar.OnSeekBarChangeListener() {
        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		rightSeekBarCB(R.id.seekBar2, progress);        		
        	}

        	public void onStartTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}

        	public void onStopTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}
        });
        // Make background visible by removing the progress drawable
        rightSeekBar.setProgressDrawable(null);
    }
    
    private void rightSeekBarCB(int ID, int progress) {
    	graph.setRightSecant(progress);
//    	System.out.println("Right Seekbar: " + Integer.toString(ID) +
//    						" progress: " + progress);
    }

    private void getColor(int i, int c) {
    	// Create an intent to start an Activity
        Intent intent = new Intent(getApplicationContext(),
               ColorChooseActivity.class);
        Log.i(getClass().getSimpleName(), "Sending color: " + c);
        intent.putExtra(getPackageName() + ".itemColor", c); // Make intent request
        intent.putExtra(getPackageName() + ".item", i);
        startActivity(intent);
    }
    
 // Put state in SharedPreferences
    private void putTangentHDPreferences()
    {
        Log.i(getClass().getSimpleName(), "In putTangentHDPreferences()");
        // Get a SharedPreferences file
        SharedPreferences state = getSharedPreferences(APP_STATE_NAME,MODE_PRIVATE);
          
        // Get a SharedPreferences editor
        SharedPreferences.Editor editor = state.edit();
   
        int left = leftSeekBar.getProgress();
        int right = rightSeekBar.getProgress();
        int eq = graph.getCurrentEquationIdx();
        Log.i(getClass().getSimpleName(), "left = " + left);
        Log.i(getClass().getSimpleName(), "right = " + right);
        Log.i(getClass().getSimpleName(), "eq = " + eq);
        // Load the editor with the data
        editor.putInt(KEY + ".leftSeekBar", left);
        editor.putInt(KEY + ".rightSeekBar", right);
        editor.putInt(KEY + ".equation", eq);
        editor.putInt(KEY + ".initPrefs", 1);	// Make prefs always read
		editor.putInt(KEY + ".invalidate", 0);
		editor.putInt(KEY + ".leftColor", graph.leftSecant.getColor());
		editor.putInt(KEY + ".rightColor", graph.rightSecant.getColor());
		editor.putInt(KEY + ".equationColor", graph.theEquation.getColor());
        // Commit the editor additions
        editor.commit();
    }

    // Get state in SharedPreferences
    private int getTangentHDPreferences()
    {
        Log.i(getClass().getSimpleName(), "In getTangentHDPreferences()");
        // Get a SharedPreferences file
        SharedPreferences state = getSharedPreferences(APP_STATE_NAME,MODE_PRIVATE);
        int eq = -1;
        if(state != null) {
	        // Get data
			this.initPrefs = state.getInt(KEY + ".initPrefs", 0);
	        if(this.initPrefs == 1) {
		        this.left = state.getInt(KEY + ".leftSeekBar", -1);
		        this.right = state.getInt(KEY + ".rightSeekBar", -1);
		        int leftColor = state.getInt(KEY + ".leftColor", 0);
		        int rightColor = state.getInt(KEY + ".rightColor", 0);
		        graph.theEquation.setColor(state.getInt(KEY + ".equationColor", 0));
		        graph.leftSecant.setColor(leftColor);
		        graph.leftPoint.setColor(leftColor);
		        graph.rightSecant.setColor(rightColor);
		        graph.rightPoint.setColor(rightColor);
		        leftSeekBar.setBackgroundColor(leftColor);
		        rightSeekBar.setBackgroundColor(rightColor);
		        TextView t = (TextView) findViewById(R.id.textView1);
		        t.setTextColor(leftColor);
		        t = (TextView) findViewById(R.id.textView2);
		        t.setTextColor(rightColor);
	        }
			this.invalidate = state.getInt(KEY + ".invalidate", 1);

	        eq = state.getInt(KEY + ".equation", 0);
	        Log.i(getClass().getSimpleName(), "left = " + left);
	        Log.i(getClass().getSimpleName(), "right = " + right);
	        Log.i(getClass().getSimpleName(), "eq = " + eq);
        }
        switch(eq) {
        	case -1:
        	case 0:
        		return R.id.equation1;
        	case 1:
        		return R.id.equation2;
        	case 2:
        		return R.id.equation3;
        }
        // Clearing out preferences for debugging purposes
//        SharedPreferences.Editor editor = state.edit();
//        editor.clear();
//        editor.commit();
//        setEquation(R.id.equation1);
        // Return the index to the equation to use
        return eq;
    }

}