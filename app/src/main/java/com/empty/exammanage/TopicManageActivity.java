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

import com.empty.exammanage.models.ChoiceTopic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopicManageActivity extends AppCompatActivity {
    //choice


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int which = getIntent().getIntExtra("topic", 0);
        int result = 0;
        //选择管理界面
        switch (which) {
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
        switch (result) {
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

    private class ChoiceManage {
        EditText editTopic;
        EditText[] editOption = new EditText[4];

        Button btnAddChoice = (Button) findViewById(R.id.btnAddChoice);
        ListView lvChoice = (ListView) findViewById(R.id.lvChoice);
        Spinner spinner;
        String Answer;
        String[] strOption = {"A:", "B:", "C:", "D:"};
        String Topic;
        SimpleAdapter adapter2;
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;

        public ChoiceManage() {
            init();
            //select();
        }

        private void init() {
            select();
            editTopic = (EditText) findViewById(R.id.editTopic);
            editOption[0] = (EditText) findViewById(R.id.editA);
            editOption[1] = (EditText) findViewById(R.id.editB);
            editOption[2] = (EditText) findViewById(R.id.editC);
            editOption[3] = (EditText) findViewById(R.id.editD);

            //标准答案选择
            spinner = (Spinner) findViewById(R.id.spinner);
            String[] src = getResources().getStringArray(R.array.select);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(TopicManageActivity.this, android.R.layout.simple_spinner_item, android.R.id.text1, src);
            if (spinner != null) {
                spinner.setAdapter(adapter);
            }


            //添加题目
            if (btnAddChoice != null) {
                btnAddChoice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getContent();
                        String addStatus;
                        if (Topic != null) {
                            //插入到选择题的表中
                            ChoiceTopicHelper.insertTopic(new ChoiceTopic(
                                    0,
                                    Topic,
                                    strOption,
                                    Answer));

                            addStatus = "添加成功";
                            RefreshListView();
                        } else {
                            addStatus = "题目不能为空";
                        }
                        Toast.makeText(TopicManageActivity.this, addStatus, Toast.LENGTH_SHORT).show();
                    }
                });

            }


            //预览列表
            adapter2 = new SimpleAdapter(
                    TopicManageActivity.this,
                    list,
                    R.layout.choice_item,
                    new String[]{"ID", "Topic"},
                    new int[]{R.id.tvItemID, R.id.tvItemTopic}
            );
            lvChoice.setAdapter(adapter2);

        }

        //获取编辑框的内容
        private void getContent() {
            //得到题目和四个备选答案
            if (editTopic != null) {
                Topic = editTopic.getText().toString();
            }
            for (int i = 0; i < editOption.length; i++) {
                if (editOption[i] != null) {
                    strOption[i] += editOption[i].getText().toString();
                }
            }
            //得到标准答案 get answer

            if (spinner != null) {
                Answer = strOption[spinner.getSelectedItemPosition()];
            }
        }

        //题目获取
        private void select() {
            ChoiceTopic[] choiceTopics = ChoiceTopicHelper.FindallTopic();
            if (choiceTopics != null){
                for (ChoiceTopic choiceTopic:choiceTopics) {
                    map = new HashMap<>();
                    map.put("ID", choiceTopic.getId());
                    map.put("Topic", choiceTopic.getTopicName());
                    list.add(map);
                }
            }
        }

        //刷新列表
        private void RefreshListView() {
            if (!list.isEmpty()) {
                list.clear();
            }
            select();
            adapter2.notifyDataSetChanged();
        }
    }


    private void trueOrFalseManage() {

    }

    private void multipleManage() {

    }
}
