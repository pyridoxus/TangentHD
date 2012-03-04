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
import android.widget.ImageView;
import android.widget.Toast;

public class TangentHDActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView graph = (ImageView) findViewById(R.id.exampleScreen);
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
        CharSequence text = "TangentHD";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        int m_id = item.getItemId();

        switch (m_id) {
            case R.id.m_equation1:
            	// Will do something else here in case statement in future
            	text = "Selected equation " + m_id;
                toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            case R.id.m_equation2:
            	// Will do something else here in case statement in future
            	text = "Selected equation " + m_id;
                toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            case R.id.m_equation3:
            	// Will do something else here in case statement in future
            	text = "Selected equation " + m_id;
                toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}