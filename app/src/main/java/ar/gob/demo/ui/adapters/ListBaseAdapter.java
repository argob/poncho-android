package ar.gob.demo.ui.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class ListBaseAdapter extends BaseAdapter {

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getViewR(position, convertView, parent);
	}

	public abstract View getViewR(int position, View convertView, ViewGroup parent);

}
