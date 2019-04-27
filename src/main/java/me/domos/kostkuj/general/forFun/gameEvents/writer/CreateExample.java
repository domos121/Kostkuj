package me.domos.kostkuj.general.forFun.gameEvents.writer;

import java.util.Random;

public class CreateExample {

    private Random r = new Random();
    private String example;
    private int answer;

    public CreateExample(EWriterDificluty dificluty){
            int num = generateNumber();
            example = num + "";
            answer = num;
        for (int i = 0; dificluty.toInt() > i; i++){
            num = generateNumber();
            if (num >= 0){
                example = example + "+" + num;
            }else example = example + num;
            answer = answer + num;
        }

    }

    private int generateNumber(){
       int number = r.nextInt(20);
       if (r.nextBoolean()){
           return number * -1;
       } else return number;
    }

    public int getAnswer() {
        return answer;
    }

    public String getExample() {
        return example;
    }
}
