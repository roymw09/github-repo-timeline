package com.example.timeline_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<AppModel> dataModelArrayList;

    public Adapter(Context ctx, ArrayList<AppModel> dataModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        holder.name.setText(dataModelArrayList.get(position).getName());
        holder.description.setText(dataModelArrayList.get(position).getDescription());
        holder.createdAt.setText(dataModelArrayList.get(position).getCreated_At());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public void updateList(ArrayList<AppModel> updateList) {
        dataModelArrayList = updateList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, createdAt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            createdAt = itemView.findViewById(R.id.createdOnTextView);
        }
    }
}
