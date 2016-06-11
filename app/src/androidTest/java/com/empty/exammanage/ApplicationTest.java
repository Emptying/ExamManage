package com.empty.exammanage;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import android.util.Log;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {

    }
    public void testNothing(){
        //test
        //test2
        //ExamDbHelper examDbHelper = ExamDbHelper.getInstance();
        //SQLiteDatabase db = null;
        //db = examDbHelper.getWritableDatabase();
        //ContentValues contentValues = new ContentValues();
        //contentValues.put("title", "hello");
        //contentValues.put("answer", "heyyou");
        //db.insert("topic", null, contentValues);
        //db.close();
        //Button button = new Button(this);
        //button.setText("button1");


        //rl.addView(button);
        //RadioGroup radioGroup = new RadioGroup(this);
        //for (int i = 0; i < 3; i++) {
        //    RadioButton radioButton = new RadioButton(this);
//
        //    radioButton.setText(String.valueOf((char)('A' + i)) + " : heyYou 222");
        //    radioGroup.addView(radioButton);
        //}
        //radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        //    @Override
        //    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //        Toast.makeText(MainActivity.this, "checkedId: " + String.valueOf(checkedId), Toast.LENGTH_SHORT).show();
        //    }
        //});
        //RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.main);
        //if (relativeLayout != null) {
        //    relativeLayout.addView(radioGroup);
        //}
    }
    public void testDb() {
        ExamDbHelper examDbHelper = ExamDbHelper.getInstance();
        SQLiteDatabase db = null;
        db = examDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", "hello");
        contentValues.put("answer", "sssyou");
        db.insert("topic_choose", "", contentValues);
        Cursor cursor = db.query("topic", new String[]{"answer"}, "title=?", new String[]{"hello"}, null, null, null, null);

        while (cursor.moveToNext()) {
            String strValue = cursor.getString(cursor.getColumnIndex("answer"));
        }
        db.close();
    }
}