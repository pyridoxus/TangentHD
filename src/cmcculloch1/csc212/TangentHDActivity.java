package cmcculloch1.csc212;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class TangentHDActivity extends Activity {
	private GraphView graph;
	private SeekBar leftSeekBar;
	private SeekBar rightSeekBar;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        graph = (GraphView) findViewById(R.id.graphView);
        graph.setAttributes((GraphAttributes)findViewById(R.id.graphAttributes));
        registerForContextMenu(graph);
        setupLeftSeekBar();
        setupRightSeekBar();
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
            	// Will do something else here in case statement in future
            	toast = Toast.makeText(context,
            			getString(R.string.s_graphColor), duration);
                toast.show();
                return true;
            case R.id.m_leftColor:
            	// Will do something else here in case statement in future
            	toast = Toast.makeText(context,
            			getString(R.string.s_leftColor), duration);
                toast.show();
                return true;
            case R.id.m_rightColor:
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
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,
    			getString(R.string.app_name), duration);
        int m_id = item.getItemId();
        int param = 0;

        switch (m_id) {
            case R.id.equation1:
            	toast = Toast.makeText(context,
            			getString(R.string.equation1), duration);
            	param = 0; // I don't like doing this, but work around it later
            break;
            case R.id.equation2:
            	toast = Toast.makeText(context,
            			getString(R.string.equation2), duration);
            	param = 1;
            break;
            case R.id.equation3:
            	toast = Toast.makeText(context,
            			getString(R.string.equation3), duration);
            	param = 2;
            break;
            case R.id.equation_exit:
            break;
            default:
                return super.onContextItemSelected(item);
        }
        graph.setEquation(param);
        toast.show();
        return true;
    }
    
    private void setupLeftSeekBar() {
    	leftSeekBar = (SeekBar)findViewById(R.id.seekBar1);
        leftSeekBar.setOnSeekBarChangeListener(
        				new SeekBar.OnSeekBarChangeListener() {
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		TangentHDActivity.leftSeekBarCB(R.id.seekBar1, progress);        		
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
    
    private static void leftSeekBarCB(int ID, int progress) {
    	System.out.println("Left Seekbar: " + Integer.toString(ID) +
    						" progress: " + progress);
    }

    private void setupRightSeekBar() {
    	rightSeekBar = (SeekBar)findViewById(R.id.seekBar2);
    	rightSeekBar.setOnSeekBarChangeListener(
        				new SeekBar.OnSeekBarChangeListener() {
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		TangentHDActivity.rightSeekBarCB(R.id.seekBar2, progress);        		
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
    
    private static void rightSeekBarCB(int ID, int progress) {
    	System.out.println("Right Seekbar: " + Integer.toString(ID) +
    						" progress: " + progress);
    }

}