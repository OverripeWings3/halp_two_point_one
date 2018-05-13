package com.iteso.is699367.halp_two_point_one.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Halp.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHandler dataBaseHandler;

    // Table names
    static final String TABLE_TASKS = "tasks";
    static final String TABLE_CLASSES = "classes";
    static final String TABLE_FLASHCARDS = "flashcards";

    // Columns Tasks
    static final String KEY_TASKS_ID = "idTask";
    static final String KEY_TASKS_NAME = "name";
    static final String KEY_TASKS_CLASS = "idClass";
    static final String KEY_TASKS_DUEDAE = "dueDate";
    static final String KEY_TASKS_DONE = "done";


    // Columns Classes
    static final String KEY_CLASSES_ID = "idClass";
    static final String KEY_CLASSES_NAME = "name";

    // Columns Flashcards
    static final String KEY_FLASHCARDS_ID = "idFlashcard";
    static final String KEY_FLASHCARDS_FRONT = "front";
    static final String KEY_FLASHCARDS_BACK = "back";


    private DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context) {
        if (dataBaseHandler == null) {
            dataBaseHandler = new DataBaseHandler(context);
        }
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_TASKS_ID + " INTEGER PRIMARY KEY,"
                + KEY_TASKS_NAME + " TEXT,"
                + KEY_TASKS_CLASS + " INTEGER,"
                + KEY_TASKS_DUEDAE + " TEXT,"
                + KEY_TASKS_DONE + " INTEGER)";
        db.execSQL(CREATE_TASKS_TABLE);

        String CREATE_CLASSES_TABLE = "CREATE TABLE " + TABLE_CLASSES + "("
                + KEY_CLASSES_ID + " INTEGER PRIMARY KEY,"
                + KEY_CLASSES_NAME + " TEXT)";
        db.execSQL(CREATE_CLASSES_TABLE);

        String CREATE_FLASHCARDS_TABLE = "CREATE TABLE " + TABLE_FLASHCARDS + "("
                + KEY_FLASHCARDS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FLASHCARDS_FRONT + " TEXT,"
                + KEY_FLASHCARDS_BACK + " TEXT)";
        db.execSQL(CREATE_FLASHCARDS_TABLE);


        db.execSQL("INSERT INTO " + TABLE_CLASSES + " (" + KEY_CLASSES_ID + "," + KEY_CLASSES_NAME + ") VALUES (1, 'Math')");
        db.execSQL("INSERT INTO " + TABLE_CLASSES + " (" + KEY_CLASSES_ID + "," + KEY_CLASSES_NAME + ") VALUES (2, 'Science')");
        db.execSQL("INSERT INTO " + TABLE_CLASSES + " (" + KEY_CLASSES_ID + "," + KEY_CLASSES_NAME + ") VALUES (3, 'History')");
        db.execSQL("INSERT INTO " + TABLE_CLASSES + " (" + KEY_CLASSES_ID + "," + KEY_CLASSES_NAME + ") VALUES (4, 'Spanish')");
        db.execSQL("INSERT INTO " + TABLE_CLASSES + " (" + KEY_CLASSES_ID + "," + KEY_CLASSES_NAME + ") VALUES (5, 'Social Studies')");
        db.execSQL("INSERT INTO " + TABLE_TASKS + " (" + KEY_TASKS_ID + "," + KEY_TASKS_NAME  + "," + KEY_TASKS_CLASS  + "," + KEY_TASKS_DUEDAE  + "," + KEY_TASKS_DONE + ") VALUES (1, 'HomeWork 1', 1, 'TODAY', 1)");
        db.execSQL("INSERT INTO " + TABLE_TASKS + " (" + KEY_TASKS_ID + "," + KEY_TASKS_NAME  + "," + KEY_TASKS_CLASS  + "," + KEY_TASKS_DUEDAE  + "," + KEY_TASKS_DONE + ") VALUES (2, 'Proyect', 4, 'TOMORROW', 0)");
        db.execSQL("INSERT INTO " + TABLE_TASKS + " (" + KEY_TASKS_ID + "," + KEY_TASKS_NAME  + "," + KEY_TASKS_CLASS  + "," + KEY_TASKS_DUEDAE  + "," + KEY_TASKS_DONE + ") VALUES (3, 'Study for test', 3, '28/05/18', 0)");
        db.execSQL("INSERT INTO " + TABLE_TASKS + " (" + KEY_TASKS_ID + "," + KEY_TASKS_NAME  + "," + KEY_TASKS_CLASS  + "," + KEY_TASKS_DUEDAE  + "," + KEY_TASKS_DONE + ") VALUES (4, 'WW2 Paper', 3, '30/05/18', 0)");
        db.execSQL("INSERT INTO " + TABLE_TASKS + " (" + KEY_TASKS_ID + "," + KEY_TASKS_NAME  + "," + KEY_TASKS_CLASS  + "," + KEY_TASKS_DUEDAE  + "," + KEY_TASKS_DONE + ") VALUES (5, 'My humps', 1, '30/05/18', 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
