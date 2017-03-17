package com.android.leleyouba.ybshop.mine.bean;

/**
 * Created by xalo on 2017/2/24.
 */

public class VerifyQuestionBean {
    private String question;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public VerifyQuestionBean(String answer, String question) {
        this.answer = answer;
        this.question = question;
    }

    public VerifyQuestionBean() {
    }
}
