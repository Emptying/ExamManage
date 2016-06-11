package com.empty.exammanage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.empty.exammanage.models.ChoiceTopic;


/**
 * Created by emptying on 2016/6/11.
 */
public class ChoiceTopicHelper {

    public static void insertTopic(ChoiceTopic choiceTopic) {
        SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();
        String[] option = choiceTopic.getOption();

        ContentValues values = new ContentValues();
        values.put("Topic", choiceTopic.getTopicName());
        values.put("A", option[0]);
        values.put("B", option[1]);
        values.put("C", option[2]);
        values.put("D", option[3]);
        values.put("Answer", choiceTopic.getAnswer());
        db.insert(DataBaseHelper.TBNAME_CHOICE, null, values);
        db.close();
    }

    public static ChoiceTopic[] findAllTopic() {
        ChoiceTopic[] choiceTopics = null;
        int i = 0;
        SQLiteDatabase db = DataBaseHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.query(DataBaseHelper.TBNAME_CHOICE, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            choiceTopics = new ChoiceTopic[cursor.getCount()];
            while (cursor.moveToNext()) {
                choiceTopics[i] = new ChoiceTopic(
                        cursor.getInt(cursor.getColumnIndex("ID")),
                        cursor.getString(cursor.getColumnIndex("Topic")),
                        new String[]{
                                cursor.getString(cursor.getColumnIndex("A")),
                                cursor.getString(cursor.getColumnIndex("B")),
                                cursor.getString(cursor.getColumnIndex("C")),
                                cursor.getString(cursor.getColumnIndex("D"))},
                        cursor.getString(cursor.getColumnIndex("Answer"))
                );
                i++;
            }
        }
        db.close();
        if (choiceTopics == null){
            choiceTopics = new ChoiceTopic[0];
        }
        return choiceTopics;
    }
}
