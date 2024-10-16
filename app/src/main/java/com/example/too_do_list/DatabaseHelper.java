package com.example.too_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DEADLINE = "deadline";
    private static final String COLUMN_REMINDER_TIME = "reminderTime";
    private static final String COLUMN_PRIORITY = "priority";
    private static final String COLUMN_PROGRESS = "progress";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TodoTask.TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_DEADLINE + " INTEGER, "
                + COLUMN_REMINDER_TIME + " INTEGER, "
                + COLUMN_PRIORITY + " INTEGER, "
                + COLUMN_PROGRESS + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TodoTask.TABLE_NAME);
        onCreate(db);
    }

    public long insertTodoTask(TodoTask task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getName());
        values.put(COLUMN_DESCRIPTION, task.getDescription());
        values.put(COLUMN_DEADLINE, task.getDeadline());
        values.put(COLUMN_REMINDER_TIME, task.getReminderTime());
        values.put(COLUMN_PRIORITY, task.getPriority().getValue());
        values.put(COLUMN_PROGRESS, task.getProgress());
        long id = db.insert(TodoTask.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<TodoTask> getAllTodoTasks() {
        List<TodoTask> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TodoTask.TABLE_NAME, null, null, null, null, null, COLUMN_DEADLINE + " ASC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                TodoTask task = new TodoTask();
                task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                task.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                task.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                task.setDeadline(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DEADLINE)));
                task.setReminderTime(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_REMINDER_TIME)));
                int priorityValue = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY));
                task.setPriority(TodoTask.Priority.fromValue(priorityValue));
                task.setProgress(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PROGRESS)));
                tasks.add(task);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return tasks;
    }

    public TodoTask getTodoTaskById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TodoTask.TABLE_NAME, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                TodoTask task = new TodoTask();
                task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                task.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                task.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                task.setDeadline(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DEADLINE)));
                task.setReminderTime(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_REMINDER_TIME)));
                int priorityValue = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY));
                task.setPriority(TodoTask.Priority.fromValue(priorityValue));
                task.setProgress(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PROGRESS)));
                cursor.close();
                db.close();
                return task;
            }
            cursor.close();
        }
        db.close();
        return null;
    }

    public void updateTodoTask(TodoTask task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getName());
        values.put(COLUMN_DESCRIPTION, task.getDescription());
        values.put(COLUMN_DEADLINE, task.getDeadline());
        values.put(COLUMN_REMINDER_TIME, task.getReminderTime());
        values.put(COLUMN_PRIORITY, task.getPriority().getValue());
        values.put(COLUMN_PROGRESS, task.getProgress());
        db.update(TodoTask.TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(task.getId())});
        db.close();
    }

    public void deleteTodoTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TodoTask.TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
