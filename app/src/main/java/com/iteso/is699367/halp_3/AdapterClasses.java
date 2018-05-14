package com.iteso.is699367.halp_3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.iteso.is699367.halp_3.beans.Classes;

import java.util.ArrayList;

public class AdapterClasses extends RecyclerView.Adapter<AdapterClasses.ViewHolder> {
    private ArrayList<Classes> classes;
    private Context context;
    private int fragment;

    AdapterClasses(int fragment, Context context, ArrayList<Classes> classes){
        this.fragment = fragment;
        this.classes = classes;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mName;
        TextView mRoom;
        TextView mTime;
        TextView mTeacher;

        ViewHolder(View v){
            super(v);
            mName = v.findViewById(R.id.class_name);
            mRoom = v.findViewById(R.id.class_room);
            mTime = v.findViewById(R.id.class_time);
            mTeacher = v.findViewById(R.id.class_teacher);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_iem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mName.setText(classes.get(holder.getAdapterPosition()).getName());
        holder.mRoom.setText(classes.get(holder.getAdapterPosition()).getRoom());
        holder.mTime.setText(classes.get(holder.getAdapterPosition()).getTime());
        holder.mTeacher.setText(classes.get(holder.getAdapterPosition()).getTeacher());


    }

    @Override
    public int getItemCount() {
        return classes.size();
    }
}
