package ar.gob.demo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ar.gob.demo.R;
import java.util.List;

public class ProcedureAdapter extends ArrayBaseAdapter {

    private static int rowLayout = R.layout.row_procedure;
    private ViewHolder holder;

    public ProcedureAdapter(Context context, List<String> items) {
        super(context, rowLayout, items);
    }

    @Override
    public View getViewR(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(rowLayout, null, false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) rowView.findViewById(R.id.tv_row_title);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.tvTitle.setText(getItem(position).toString());
        return rowView;
    }

    public class ViewHolder {
        TextView tvTitle;
    }

}
