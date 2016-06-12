package com.empty.exammanage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.empty.exammanage.models.ChoiceTopic;

public class ChoiceActivity extends AppCompatActivity {
    public ChoiceTopic[] choiceTopics = null;
    public Button buttonPrev;
    public Button buttonNext;
    public TextView tvChoiceTopic;
    public TextView tvAnswer;
    public RadioButton[] radioOption = new RadioButton[4];
    public RadioGroup radioGroup;
    public LinearLayout answerLayout;
    public int currentTopic = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        choiceTopics = ChoiceTopicHelper.findAllTopic();
        answerLayout = (LinearLayout)findViewById(R.id.AnswerLayout);
        buttonPrev = (Button) findViewById(R.id.btnPrevChoice);
        buttonNext = (Button) findViewById(R.id.btnNextChoice);
        radioGroup = (RadioGroup) findViewById(R.id.rgChoice);
        radioOption[0] = (RadioButton) findViewById(R.id.radioA);
        radioOption[1] = (RadioButton) findViewById(R.id.radioB);
        radioOption[2] = (RadioButton) findViewById(R.id.radioC);
        radioOption[3] = (RadioButton) findViewById(R.id.radioD);

        tvAnswer = (TextView) findViewById(R.id.tvAnswer);


        tvChoiceTopic = (TextView) findViewById(R.id.tvChoiceTopic);

        if (buttonPrev == null || buttonNext == null) {
            return;
        }
        showChoiceTopic(currentTopic);
        updateButtonState();
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevTopic();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answerCompare()||(answerLayout.getVisibility())==0){
                    nextTopic();
                    radioGroup.clearCheck();
                }else {
                    answerLayout.setVisibility(View.VISIBLE);
                }

            }
        });

//                if (radioOption[selected].getText().toString().equals(choiceTopics[currentTopic].getAnswer())) {
//                    nextTopic();
//                }
//                else {
//                    tvAnswer.setText(choiceTopics[currentTopic].getAnswer());
//                }
    }
    public void prevTopic() {
        if (currentTopic > 0) {
            currentTopic--;
            showChoiceTopic(currentTopic);
        }
        updateButtonState();
        //radioGroup.clearCheck();
    }
    public void nextTopic() {
        if (currentTopic + 1 < choiceTopics.length) {
            currentTopic++;
            showChoiceTopic(currentTopic);
        }
        updateButtonState();
        //radioGroup.clearCheck();
    }
    public int getSelectedIndex(){
        int selected = 0;
        if (radioGroup != null) {

            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.radioA:
                    selected = 0;
                    break;
                case R.id.radioB:
                    selected = 1;
                    break;
                case R.id.radioC:
                    selected = 2;
                    break;
                case R.id.radioD:
                    selected = 3;
                    break;
                default:
                    break;
            }
        }

        return selected;
    }
    public void updateButtonState() {
        boolean prev = true;
        boolean next = true;
        if (choiceTopics == null){
            prev = next = false;
        }
        if (currentTopic == 0) {
            prev = false;
        }
        if (currentTopic == choiceTopics.length - 1){
            next = false;
        }
        buttonPrev.setEnabled(prev);
        buttonNext.setEnabled(next);
    }
    public void showChoiceTopic(int index) {
        answerLayout.setVisibility(View.INVISIBLE);
        if (choiceTopics != null && index >= 0 && index < choiceTopics.length) {
            String[] option = choiceTopics[index].getOption();
            tvChoiceTopic.setText( choiceTopics[index].getTopicName());
            if (option != null) {
                for (int i = 0; i < 4; i++){
                    if (radioOption[i] != null){
                        radioOption[i].setText(option[i]);
                    }
                }
            }
        }
        tvAnswer.setText(choiceTopics[index].getAnswer());
    }
    public boolean answerCompare(){
/*        String i= radioOption[getSelectedIndex()].getText().toString();
        String j=tvAnswer.getText().toString();*/

        return radioOption[getSelectedIndex()].getText().toString().equals(tvAnswer.getText().toString());
    }

}
