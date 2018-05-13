package com.iteso.is699367.halp_3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.mDuedate.setText(tasks.get(holder.getAdapterPosition()).getDueDate());
        holder.mClass.setText(tasks.get(holder.getAdapterPosition()).getClas().getName());


        /*switch(tasks.get(holder.getAdapterPosition()).getDone()){
            case 0:
                holder.mImage.setImageResource(R.drawable.ic_check_box_outline_blank); break;
            case 1:
                holder.mImage.setImageResource(R.drawable.ic_check_box); break;

        }
        Bitmap bitmap = ((BitmapDrawable)holder.mImage.getDrawable()).getBitmap();
        holder.mImage.setImageBitmap(bitmap);*/





    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
