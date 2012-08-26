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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ColorArrayAdapter extends ArrayAdapter<CharSequence> {
	String[] list;
	int[] colors;
	
	public ColorArrayAdapter(Context context, int resource,
			int textViewResourceId, int[] colors, String[] list) {
		super(context, resource, textViewResourceId);
		this.list = list;
		this.colors = colors;
	}
	
	@Override
    public View getDropDownView(int position, View convertView,
    		ViewGroup parent){
		View v = convertView;
		if (v == null) {
			Context mContext = this.getContext();
			LayoutInflater vi = 
				(LayoutInflater)mContext.getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.colorrow, null);
		}
		TextView tv = (TextView)v.findViewById(R.id.spinnerTarget);
		tv.setText(list[position]);
		tv.setTextColor(colors[position]);
		return v;  
	}              
}
