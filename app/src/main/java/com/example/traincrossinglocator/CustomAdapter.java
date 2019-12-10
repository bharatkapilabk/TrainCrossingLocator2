package com.example.traincrossinglocator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<String> Name = new ArrayList<String>();
    // private ArrayList<String> Status = new ArrayList<String>();
    private ArrayList<String> Time = new ArrayList<String>();

    public CustomAdapter(Context mContext, ArrayList<String> name, ArrayList<String> time) {
        this.mContext = mContext;
        Name = name;
//        Status = status;
        Time = time;
    }

    @Override
    public int getCount() {
        return Name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout, null);
            holder = new viewHolder();
            holder.name = convertView.findViewById(R.id.tvName);
//            holder.status = convertView.findViewById(R.id.tvStatus);
            holder.time = convertView.findViewById(R.id.tvTime);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.name.setText(Name.get(position));
        holder.time.setText(Time.get(position));
//        holder.Amount.setText(Amount.get(position));

        return convertView;
    }



    public class viewHolder {
        TextView time,name;
    }

}