package com.iteso.is699367.halp_two_point_one.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.is699367.halp_two_point_one.beans.Tasks;
import com.iteso.is699367.halp_two_point_one.beans.Classes;

import java.util.ArrayList;

public class TaskControl {
    public void addTask(Tasks task, DataBaseHandler dh) {
        long inserted;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_TASKS_NAME, task.getName());
        values.put(DataBaseHandler.KEY_TASKS_CLASS, task.getClas().getId());
        values.put(DataBaseHandler.KEY_TASKS_DUEDAE, task.getDueDate());
        values.put(DataBaseHandler.KEY_TASKS_DONE, task.getDone());

        // Inserting Product
        inserted = db.insert(DataBaseHandler.TABLE_TASKS, null, values);

        // Closing database connection
        try {
            db.close();
        } catch (Exception e) {
            Log.v("HALP", e.getMessage());
        }
    }

    public ArrayList<Tasks> getTasks(DataBaseHandler dh) {
        ArrayList<Tasks> tasks = new ArrayList<>();
        String selectQuery = "SELECT  S." + DataBaseHandler.KEY_TASKS_ID + ","
                + "S." + DataBaseHandler.KEY_TASKS_NAME + ","
                + "S." + DataBaseHandler.KEY_TASKS_DONE + ","
                + "S." + DataBaseHandler.KEY_TASKS_DUEDAE + ","
                + "C." + DataBaseHandler.KEY_CLASSES_ID + ","
                + "C." + DataBaseHandler.KEY_CLASSES_NAME + " FROM "
                + DataBaseHandler.TABLE_TASKS + " S, "
                + DataBaseHandler.TABLE_CLASSES + " C WHERE S."
                + DataBaseHandler.KEY_TASKS_CLASS + " = C." + DataBaseHandler.KEY_CLASSES_ID;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            Tasks task = new Tasks();
            task.setId(cursor.getInt(0));
            task.setName(cursor.getString(1));
            task.setDone(cursor.getInt(2));
            task.setDueDate(cursor.getString(3));
            Classes clas = new Classes();
            clas.setId(cursor.getInt(4));
            clas.setName(cursor.getString(5));
            task.setClas(clas);
            tasks.add(task);
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.v("HALP", e.getMessage());
        }
        return tasks;
    }


}
