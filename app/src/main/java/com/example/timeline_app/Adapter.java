package com.example.timeline_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    private Context context;

    public Adapter(Context ctx, ArrayList<AppModel> dataModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
        this.context = ctx;
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
        holder.forks.setText(context.getString(R.string.forks_text, dataModelArrayList.get(position).getForks()));
        holder.open_issues.setText(context.getString(R.string.openIssues_text, dataModelArrayList.get(position).getOpen_issues()));
        holder.watchers.setText(context.getString(R.string.watchers_text, dataModelArrayList.get(position).getWatchers()));
        holder.url = dataModelArrayList.get(position).getUrl();
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
        TextView name, description, createdAt, forks, open_issues, watchers;
        String url;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            createdAt = itemView.findViewById(R.id.createdOnTextView);
            forks = itemView.findViewById(R.id.forksOnTextView);
            open_issues = itemView.findViewById(R.id.openIssuesTextView);
            watchers = itemView.findViewById(R.id.watchersTextView);

            itemView.setOnClickListener(this::openUrl);
        }

        // open a repository when a recyclerView item is clicked
        private void openUrl(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        }
    }
}
