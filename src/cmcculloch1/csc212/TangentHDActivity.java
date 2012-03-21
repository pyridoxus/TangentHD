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
import android.widget.Toast;

public class TangentHDActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        GraphView graph = (GraphView) findViewById(R.id.graphView);
        registerForContextMenu(graph);
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
}