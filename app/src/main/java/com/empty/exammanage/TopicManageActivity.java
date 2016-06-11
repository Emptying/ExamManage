package com.empty.exammanage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopicManageActivity extends AppCompatActivity {
    DataBaseHelper dbHelper;
    SQLiteDatabase db;
    //choice


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper=new DataBaseHelper(this,"TopicManage",null,1);
        db=dbHelper.getWritableDatabase();
        int which=getIntent().getIntExtra("topic",0);
        int result=0;
        //选择管理界面
        switch (which){
            case 0:
                result = R.layout.activity_choice_manage;
                break;
            case 1:
                result = R.layout.activity_true_or_false_manage;
                break;
            case 2:
                result = R.layout.activity_multiple_manage;
                break;
        }
        //设置管理界面
        setContentView(result);
        //根据相应管理界面处理相应数据
        switch (result){
            case R.layout.activity_choice_manage:
                new ChoiceManage();
                break;
            case R.layout.activity_true_or_false_manage:
                trueOrFalseManage();
                break;
            case R.layout.activity_multiple_manage:
                multipleManage();
                break;
        }

    }

    private class ChoiceManage{
        EditText  editTopic,editA,editB,editC,editD;
        Button btnAddChoice=(Button)findViewById(R.id.btnAddChoice);
        ListView lvChoice=(ListView)findViewById(R.id.lvChoice);
        Spinner spinner;
        String Answer;
        String A,B,C,D;
        String Topic;
        SimpleAdapter adapter2;
        ArrayList<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map;
        public ChoiceManage(){
            init();
            //select();
        }

        private void init(){
            select();
            editTopic = (EditText)findViewById(R.id.editTopic);
            editA = (EditText)findViewById(R.id.editA);
            editB = (EditText)findViewById(R.id.editB);
            editC = (EditText)findViewById(R.id.editC);
            editD = (EditText)findViewById(R.id.editD);
            //标准答案选择
            spinner= (Spinner) findViewById(R.id.spinner);
            String [] src=getResources().getStringArray(R.array.select);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(TopicManageActivity.this,android.R.layout.simple_spinner_item,android.R.id.text1,src);
            if(spinner!=null){
                spinner.setAdapter(adapter);
            }


            //添加题目
            if(btnAddChoice!=null){
                btnAddChoice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getContent();
                        String addStatus;
                        if(Topic!=null){
                            //插入到选择题的表中
                            add();
                            addStatus="添加成功";
                            RefreshListView();
                        }else {
                            addStatus="题目不能为空";
                        }
                        Toast.makeText(TopicManageActivity.this, addStatus, Toast.LENGTH_SHORT).show();
                    }
                });

            }



            //预览列表
            adapter2=new SimpleAdapter(
                    TopicManageActivity.this,
                    list,
                    R.layout.choice_item,
                    new String[]{"ID","Topic"},
                    new int[]{R.id.tvItemID, R.id.tvItemTopic}
            );
            lvChoice.setAdapter(adapter2);

        }
        //获取编辑框的内容
        private void getContent(){
            //得到题目和四个备选答案
            if(editTopic!=null){
                Topic=editTopic.getText().toString();
            }
            if(editA!=null){
                A="A:"+editA.getText().toString();
            }
            if(editB!=null){
                B="B:"+editB.getText().toString();
            }
            if(editC!=null){
                C="C:"+editC.getText().toString();
            }
            if(editD!=null){
                D="D:"+editD.getText().toString();
            }

            //得到标准答案

            if(spinner!=null) {
                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        Answer = A;
                        break;
                    default:
                        break;
                }

            }

        }

        //添加题目
        private void add(){

            ContentValues values=new ContentValues();
            values.put("Topic",Topic);
            values.put("A",A);
            values.put("B",B);
            values.put("C",C);
            values.put("D",D);
            values.put("Answer",Answer);
            db.insert(DataBaseHelper.TBNAME_CHOICE,null,values);

        }

        //题目获取
        private void select(){
            Cursor cursor=db.query(DataBaseHelper.TBNAME_CHOICE,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                map=new HashMap<>();
                map.put("ID",cursor.getInt(cursor.getColumnIndex("ID")));
                map.put("Topic",cursor.getString(cursor.getColumnIndex("Topic")));
/*            map.put("A",cursor.getString(cursor.getColumnIndex("A")));
            map.put("B",cursor.getString(cursor.getColumnIndex("B")));
            map.put("C",cursor.getString(cursor.getColumnIndex("C")));
            map.put("D",cursor.getString(cursor.getColumnIndex("D")));*/
                list.add(map);

            }


        }
        //刷新列表
        private void RefreshListView(){
            if(!list.isEmpty()){
                list.clear();
            }
            select();
            adapter2.notifyDataSetChanged();
        }



    }



    private void trueOrFalseManage(){

    }

    private void multipleManage(){

    }
}
