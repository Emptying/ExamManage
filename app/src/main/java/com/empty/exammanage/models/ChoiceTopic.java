package com.empty.exammanage.models;

/**
 * Created by wallds on 2016/6/11.
 */
public class ChoiceTopic {
    public int id = 0;
    public String topicName = null;
    public String[] option = null;
    public String answer = null;

    public ChoiceTopic(){

    }

    public ChoiceTopic(int id, String topicName, String[] option, String answer) {
        this.id = id;
        this.topicName = topicName;
        this.option = option;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String[] getOption() {
        return option;
    }

    public void setOption(String[] option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
