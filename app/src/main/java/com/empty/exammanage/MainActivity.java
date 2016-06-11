package com.empty.exammanage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnTest,btnManage;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //题目测试
        btnTest = (Button)findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //题型对话框
                topicDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            //选择题
                            case 0:
                                startChoice();
                                break;
                            //判断题
                            case 1:
                                break;
                            //多选题
                            case 2:
                                break;
                        }
                    }
                });

            }
        });
        //题目管理
        btnManage = (Button)findViewById(R.id.btnManage);
        btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //题目选择对话框
                topicDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startTopicManage(which);
                    }
                });

            }
        });
    }

    private void startChoice(){
        intent = new Intent(MainActivity.this,ChoiceActivity.class);
        startActivity(intent);
    }

    private void startTopicManage(int topicWhich){
        intent = new Intent(MainActivity.this,TopicManageActivity.class);
        intent.putExtra("topic",topicWhich);
        startActivity(intent);
    }

    private void topicDialog(DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder dialog;
        String [] item = getResources().getStringArray(R.array.Questions_item);
        dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("请选择题型");
        dialog.setItems(item,onClickListener);
        dialog.show();
    }


}
