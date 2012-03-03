package cmcculloch1.csc212;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
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
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            openContextMenu(findViewById(R.layout.main));
        }
        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.equation1:
              Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.equation1) +
                          " context menu option",
                          Toast.LENGTH_SHORT).show();
              return true;
        default:
              return super.onContextItemSelected(item);
        }
    }
}