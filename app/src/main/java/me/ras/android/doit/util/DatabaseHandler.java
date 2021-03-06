package me.ras.android.doit.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import me.ras.android.doit.model.ToDoModel;

import static java.util.Objects.nonNull;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "toDoDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE_QUERY = "" +
            "CREATE TABLE " + TODO_TABLE + " ( \n" +
            ID + " INTEGER PRIMARY KEY, \n" +
            TASK + " TEXT, \n" +
            STATUS + " INTEGER \n" +
            ") \n";
    private static final String DROP_TODO_TABLE_QUERY = "DROP TABLE IF EXISTS " + TODO_TABLE;

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TODO_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Drop the older tables
        sqLiteDatabase.execSQL(DROP_TODO_TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel toDoModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK, toDoModel.getTask());
        contentValues.put(STATUS, 0);
        db.insert(TODO_TABLE, null, contentValues);
    }

    @SuppressLint("NewApi")
    public List<ToDoModel> getAllTasks() {
        List<ToDoModel> toDoModels = new ArrayList<>();
        db.beginTransaction();
        try (Cursor cursor = db.query(TODO_TABLE, null, null, null, null, null, null);) {
            if (nonNull(cursor)) {
                if (cursor.moveToFirst()) {
                    do {
                        ToDoModel toDoModel = new ToDoModel();
                        toDoModel.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        toDoModel.setTask(cursor.getString(cursor.getColumnIndex(TASK)));
                        toDoModel.setStatus(cursor.getInt(cursor.getColumnIndex(STATUS)));
                        toDoModels.add(toDoModel);
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
        }
        return toDoModels;
    }

    public void updateStatus(int id, int status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(STATUS, status);
        db.update(TODO_TABLE, contentValues, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void updateTask( int id, String task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK, task);
        db.update(TODO_TABLE, contentValues, ID + "=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id) {
        db.delete(TODO_TABLE, ID + "=?", new String[]{String.valueOf(id)});
    }
}
