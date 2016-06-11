package com.empty.exammanage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cws10 on 2016/6/11.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TBNAME_CHOICE="choiceTable";
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "
                +TBNAME_CHOICE
                +"(ID integer primary key autoincrement,"
                +"Topic String default '',"
                +"A String default '',"
                +"B String default '',"
                +"C String default '',"
                +"D String default '',"
                +"Answer String default '')"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
