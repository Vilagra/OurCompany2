package com.example.ourcompany;

import android.content.Context;

import com.example.ourcompany.entity.Person;
import com.example.ourcompany.entity.Question;


/**
 * Created by Vilagra on 12.10.2016.
 */

public class LogicOfGame {
    private int correct;
    private int wrong;
    Question question;
    Person player;
    Context ctx;
    Location location;
    private int inARow;

    public LogicOfGame(Person player, Context ctx,Location location) {
        this.player = player;
        this.ctx = ctx;
        this.location=location;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String checkAnswer(String s){
        if(s.equals(question.getAnswer().getNameByLocation(location))){
            correct++;
            inARow++;
            switch (inARow){
                case 3: return ctx.getString(R.string.threeInARow);
                case 5: return ctx.getString(R.string.fiveInARow);
                default: return ctx.getString(R.string.horosh);
            }
        }
        else{
            wrong++;
            inARow=0;
            if (player.getNameByLocation(location).equals(question.getAnswer().getNameByLocation(location))){
                return ctx.getString(R.string.answerItself)+" "+question.getAnswer().getNameByLocation(location);
            }
            return ctx.getString(R.string.mistake)+ " "+question.getAnswer().getNameByLocation(location);
        }
    }

    public Person getPlayer() {
        return player;
    }

    public String getRusult(){
        int percent = Math.round(100*correct/(wrong+correct));
        return player.getNameByLocation(location)+", "+ctx.getString(R.string.youOn)+" " +percent+ ctx.getString(R.string.member);
    }
}
