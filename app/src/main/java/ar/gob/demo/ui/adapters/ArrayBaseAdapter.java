package ar.gob.demo.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class ArrayBaseAdapter<T> extends ArrayAdapter<T> {

	public ArrayBaseAdapter(Context context, int resource) {
		super(context, resource);
	}

	public ArrayBaseAdapter(Context context, int resource, List<T> listGroupPackages) {
		super(context, resource, listGroupPackages);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getViewR(position, convertView, parent);
	}

	public abstract View getViewR(int position, View convertView, ViewGroup parent);

}
