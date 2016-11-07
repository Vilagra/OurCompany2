package com.example.ourcompany.entity;

/**
 * Created by Vilagra on 02.11.2016.
 */

public class Question {
    private String question;
    private Person answer;
    private String description;

    public Question(String question, Person answer, String description) {
        this.question = question;
        this.answer = answer;
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public Person getAnswer() {
        return answer;
    }

    public String getDescription() {
        return description;
    }
}
