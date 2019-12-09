package com.example.traincrossinglocator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewTrainsAdapter extends RecyclerView.Adapter<ViewTrainsAdapter.ViewHolder> {

    public List<Train> trainsList;

    public ViewTrainsAdapter(List<Train> trainsList) {
        this.trainsList = trainsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameTv.setText(trainsList.get(position).getName());
        holder.timeTv.setText(trainsList.get(position).getDeparture());
    }

    @Override
    public int getItemCount() {
        return trainsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView nameTv, timeTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            nameTv = mView.findViewById(R.id.nameTv);
            timeTv = mView.findViewById(R.id.timeTv);
        }
    }
}