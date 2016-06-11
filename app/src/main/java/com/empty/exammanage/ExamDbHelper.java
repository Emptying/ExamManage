package com.empty.exammanage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wallds on 2016/5/28.
 */
public class ExamDbHelper extends SQLiteOpenHelper{
    private static ExamDbHelper instance = new ExamDbHelper(MyApplication.getInstance().getApplicationContext());
    private static final String DB_NAME = "test.db";
    private static final int version = 1;

    public ExamDbHelper(Context context){
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE topic_choose (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "exam_id INTEGER DEFAULT 0," +
                "title VARCHAR(20) DEFAULT ''," +
                "question VARCHAR(255) DEFAULT ''," +
                "answers VARCHAR(255) DEFAULT ''," +
                "answer_id INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static ExamDbHelper getInstance() {
        return instance;
    }
}
