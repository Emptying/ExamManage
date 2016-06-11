package com.empty.exammanage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class TopicManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                choiceManage();
                break;
            case R.layout.activity_true_or_false_manage:
                trueOrFalseManage();
                break;
            case R.layout.activity_multiple_manage:
                multipleManage();
                break;
        }
    }

    private void choiceManage(){
        Spinner spinner= (Spinner) findViewById(R.id.spinner);
        String [] src=getResources().getStringArray(R.array.select);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(TopicManageActivity.this,android.R.layout.simple_spinner_item,android.R.id.text1,src);
        if(adapter!=null){
            spinner.setAdapter(adapter);
        }


    }

    private void trueOrFalseManage(){

    }

    private void multipleManage(){

    }
}
