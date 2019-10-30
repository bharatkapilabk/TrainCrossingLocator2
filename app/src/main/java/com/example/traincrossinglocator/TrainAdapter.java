package com.example.traincrossinglocator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.MyViewHolder> {
    Context context;

    public TrainAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TrainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trains,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainAdapter.MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView i1,i2,i3,i4,i5,i6;
        TextView t1,t2,t3;

        MyViewHolder(final View itemView) {
            super(itemView);
            i1 = itemView.findViewById(R.id.i1);
            i2 = itemView.findViewById(R.id.i2);
            i3 = itemView.findViewById(R.id.i3);
            i4 = itemView.findViewById(R.id.i4);
            i5 = itemView.findViewById(R.id.i5);
            i6 = itemView.findViewById(R.id.i6);
            t1=  itemView.findViewById(R.id.t1);
            t2=  itemView.findViewById(R.id.t2);
            t3=  itemView.findViewById(R.id.t3);
        }
    }
}
