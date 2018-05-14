package com.iteso.is699367.halp_3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.is699367.halp_3.beans.Tasks;

import java.util.ArrayList;

public class AdapterTasks extends RecyclerView.Adapter<AdapterTasks.ViewHolder> {
    private ArrayList<Tasks> tasks;
    private Context context;
    private int fragment;

    AdapterTasks(int fragment, Context context, ArrayList<Tasks> tasks){
        this.fragment = fragment;
        this.tasks = tasks;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mName;
        TextView mDuedate;
        TextView mClass;
        ImageView mImage;

        ViewHolder(View v){
            super(v);
            mName = v.findViewById(R.id.task_name);
            mDuedate = v.findViewById(R.id.task_due_date);
            mClass = v.findViewById(R.id.task_class);
            mImage = v.findViewById(R.id.task_check);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mName.setText(tasks.get(holder.getAdapterPosition()).getName());
        holder.mDuedate.setText(tasks.get(holder.getAdapterPosition()).getDuedate());
        holder.mClass.setText(tasks.get(holder.getAdapterPosition()).getClas());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
