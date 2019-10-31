package com.example.traincrossinglocator;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.MyViewHolder> {
    Context context;
    DataModel dataModel;

    public TrainAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TrainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trains,parent,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.c1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Context context=view.getContext();
        Intent intent=new Intent(context,Map.class);
        LatLng latlng1=new LatLng(31.342205,75.576007);
        intent.putExtra("latlong",latlng1);
        context.startActivity(intent);
    }
});
myViewHolder.c2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context,Map.class);
        LatLng latlng2=new LatLng(31.3380797,75.5816489);
        intent.putExtra("latlong",latlng2);
        context.startActivity(intent);

    }
});
myViewHolder.c3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context,Map.class);
        LatLng latlng3=new LatLng(31.3393936,75.5797917);
        intent.putExtra("latlong",latlng3);
        context.startActivity(intent);

    }
});
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView i1,i2,i3,i4,i5,i6;
        TextView t1,t2,t3;
        CardView c1,c2,c3;

        MyViewHolder(final View itemView) {
            super(itemView);
            i1 = itemView.findViewById(R.id.i1);
            i2 = itemView.findViewById(R.id.i2);
            i3 = itemView.findViewById(R.id.i3);
            i4 = itemView.findViewById(R.id.i4);
            i5 = itemView.findViewById(R.id.i5);
            i6 = itemView.findViewById(R.id.i6);
            t1=  itemView.findViewById(R.id.t1);
            t1.setText(dataModel.Status);
            t2=  itemView.findViewById(R.id.t2);
            t3=  itemView.findViewById(R.id.t3);
            c1=  itemView.findViewById(R.id.c1);
            c2=  itemView.findViewById(R.id.c2);
            c3=  itemView.findViewById(R.id.c3);
        }
    }
}