package cmcculloch1.csc212;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class TangentHDActivity extends Activity {
	private GraphView graph;
	private SeekBar leftSeekBar;
	private SeekBar rightSeekBar;
	private TextView equationName;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        graph = (GraphView) findViewById(R.id.graphView);
        equationName = (TextView) findViewById(R.id.textView3);
        Point2D p = new Point2D(graph.setAttributes(
        				(GraphAttributes)findViewById(R.id.graphAttributes)));
        int leftSeekBarRange = (int)p.getX();
        int rightSeekBarRange = (int)p.getY();
        registerForContextMenu(graph);
        setupLeftSeekBar(leftSeekBarRange);
        setupRightSeekBar(rightSeekBarRange);
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
            	getColor();
            	// Will do something else here in case statement in future
            	toast = Toast.makeText(context,
            			getString(R.string.s_graphColor), duration);
                toast.show();
                return true;
            case R.id.m_leftColor:
            	getColor();
            	// Will do something else here in case statement in future
            	toast = Toast.makeText(context,
            			getString(R.string.s_leftColor), duration);
                toast.show();
                return true;
            case R.id.m_rightColor:
            	getColor();
            	// Will do something else here in case statement in future
            	toast = Toast.makeText(context,
            			getString(R.string.s_rightColor), duration);
                toast.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = getApplicationContext();
        String name;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,
    			getString(R.string.app_name), duration);
        int m_id = item.getItemId();
        int param = 0;

        switch (m_id) {
            case R.id.equation1:
            	name = getString(R.string.equation1);
            	toast = Toast.makeText(context, name, duration);
                equationName.setText(name);
            	param = 0; // I don't like doing this, but work around it later
            break;
            case R.id.equation2:
            	name = getString(R.string.equation2);
            	toast = Toast.makeText(context, name, duration);
                equationName.setText(name);
            	param = 1;
            break;
            case R.id.equation3:
            	name = getString(R.string.equation3);
            	toast = Toast.makeText(context, name, duration);
                equationName.setText(name);
            	param = 2;
            break;
            case R.id.equation_exit:
            break;
            default:
                return super.onContextItemSelected(item);
        }
        setSeekBarRanges(graph.setEquation(param));
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
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		leftSeekBarCB(R.id.seekBar1, progress);        		
        	}

        	@Override
        	public void onStartTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}

        	@Override
        	public void onStopTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}
        });
    }
    
    private void leftSeekBarCB(int ID, int progress) {
    	graph.setLeftSecant(progress);
    	System.out.println("Left Seekbar: " + Integer.toString(ID) +
    						" progress: " + progress);
    }

    private void setupRightSeekBar(int rightSeekBarRange) {
    	rightSeekBar = (SeekBar)findViewById(R.id.seekBar2);
    	rightSeekBar.setMax(rightSeekBarRange);
    	rightSeekBar.setOnSeekBarChangeListener(
        				new SeekBar.OnSeekBarChangeListener() {
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		rightSeekBarCB(R.id.seekBar2, progress);        		
        	}

        	@Override
        	public void onStartTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}

        	@Override
        	public void onStopTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}
        });
    }
    
    private void rightSeekBarCB(int ID, int progress) {
    	graph.setRightSecant(progress);
    	System.out.println("Right Seekbar: " + Integer.toString(ID) +
    						" progress: " + progress);
    }

    private void getColor() {
    	// Create an intent to start an Activity
        Intent intent = new Intent(getApplicationContext(),
               ColorChooseActivity.class);
        // Make the intent request
        startActivity(intent);    	
    }
}