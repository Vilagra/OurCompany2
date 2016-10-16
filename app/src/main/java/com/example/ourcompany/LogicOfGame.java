package com.example.ourcompany;

/**
 * Created by Vilagra on 12.10.2016.
 */

public class LogicOfGame {
    private int correct;
    private int wrong;
    String curentCorect;
    private int inARow;



    public void setCurentCorect(String curentCorect) {
        this.curentCorect = curentCorect;
    }

    public String checkAnswer(String s){
        if(s.equals(curentCorect)){
            correct++;
            inARow++;
            switch (inARow){
                case 3: return "Шикардос, 3 подряд верных";
                case 5: return "5 верных подряд, да ты по ходу свой(я)";
                default: return "Верно, так держать";
            }
        }
        else{
            wrong++;
            inARow=0;
            if(curentCorect.equals("Джонни")){
                return "не верно, ну конечно это же Джонни человек-позитив, человек-зажигалка дарит всегда только положительные эмоции";
            }
            if(curentCorect.equals("Олежка")&&StoreForStatic.dataOfGame.getLanguge().equals("English")){
                return "не верно, это же Олежка, так как кекешка:)))";
            }
            return "нет,ну вы что, это же "+ curentCorect;
        }
    }

    public String getRusult(){
        int percent = 100*correct/(wrong+correct);
        return "Вы на "+ percent+ "% член этой компании:)";
    }
}
