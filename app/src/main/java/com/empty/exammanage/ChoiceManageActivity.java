package com.empty.exammanage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class ChoiceManageActivity extends AppCompatActivity {
    EditText editTopic;
    EditText[] editOption = new EditText[4];
    Button btnAddChoice;
    ListView lvChoice;
    Spinner spinner;
    ChoiceTopic choiceTopic = new ChoiceTopic(0, "", null, "");
    SimpleAdapter adapter2;
    ArrayList<Map<String, Object>> list = new ArrayList<>();
    Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_manage);
        init();
    }

    private void init() {
        select();
        editTopic = (EditText) findViewById(R.id.editTopic);
        editOption[0] = (EditText) findViewById(R.id.editA);
        editOption[1] = (EditText) findViewById(R.id.editB);
        editOption[2] = (EditText) findViewById(R.id.editC);
        editOption[3] = (EditText) findViewById(R.id.editD);
        btnAddChoice = (Button) findViewById(R.id.btnAddChoice);
        lvChoice = (ListView) findViewById(R.id.lvChoice);

        //标准答案选择
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] src = getResources().getStringArray(R.array.select);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ChoiceManageActivity.this, android.R.layout.simple_spinner_item, android.R.id.text1, src);
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
                    if (!choiceTopic.getTopicName().isEmpty()) {
                        //插入到选择题的表中
                        ChoiceTopicHelper.insertTopic(choiceTopic);

                        addStatus = "添加成功";
                        RefreshListView();
                    } else {
                        addStatus = "题目不能为空";
                    }
                    Toast.makeText(ChoiceManageActivity.this, addStatus, Toast.LENGTH_SHORT).show();
                }
            });

        }


        //预览列表
        adapter2 = new SimpleAdapter(
                ChoiceManageActivity.this,
                list,
                R.layout.choice_item,
                new String[]{"ID", "Topic"},
                new int[]{R.id.tvItemID, R.id.tvItemTopic}
        );
        lvChoice.setAdapter(adapter2);

    }

    //获取编辑框的内容
    private void getContent() {
        String[] strOption = {"A:", "B:", "C:", "D:"};
        //得到题目和四个备选答案
        if (editTopic != null) {
            choiceTopic.setTopicName(editTopic.getText().toString());
        }
        for (int i = 0; i < editOption.length; i++) {
            if (editOption[i] != null) {
                strOption[i] += editOption[i].getText().toString();
            }
        }
        choiceTopic.setOption(strOption);
        //得到标准答案 get answer

        if (spinner != null) {
            choiceTopic.setAnswer(strOption[spinner.getSelectedItemPosition()]);
        }
    }

    //题目获取
    private void select() {
        ChoiceTopic[] choiceTopics = ChoiceTopicHelper.findAllTopic();
        if (choiceTopics != null) {
            for (ChoiceTopic choiceTopic : choiceTopics) {
                map = new HashMap<>();
                map.put("ID", choiceTopic.getId());
                //
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
