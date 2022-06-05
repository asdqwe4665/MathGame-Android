package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener{

    int correctAnswer;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;
    Button backButton;
    TextView textObjectView1;
    TextView textObjectView2;
    TextView textObjectScore;


    int currentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        textObjectView1 = findViewById(R.id.textView1);
        textObjectView2 = findViewById(R.id.textView2);
        textObjectScore = findViewById(R.id.textScore);
        buttonObjectChoice1 = findViewById(R.id.buttonChoiceA);
        buttonObjectChoice2 = findViewById(R.id.buttonChoiceB);
        buttonObjectChoice3 = findViewById(R.id.buttonChoiceC);
        backButton=findViewById(R.id.buttonBack);

        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);
        backButton.setOnClickListener(this);

        setQuestion();

    }

    @Override
    public void onClick(View view) {
        int answerGiven=0;
        switch (view.getId()) {

            case R.id.buttonChoiceA:

                answerGiven = Integer.parseInt("" + buttonObjectChoice1.getText());
                break;

            case R.id.buttonChoiceB:
                answerGiven = Integer.parseInt("" + buttonObjectChoice2.getText());
                break;

            case R.id.buttonChoiceC:
                answerGiven = Integer.parseInt("" + buttonObjectChoice3.getText());
                break;
            case R.id.buttonBack:
                onBackPressed();
                break;

        }

        updateScore(answerGiven);
        setQuestion();

    }

    void setQuestion(){
        int max=100;
        int min=10;
        int range = max - min + 1;
        Random random = new Random();

        int numberOne = (int)(Math.random() * range) + min;
        numberOne++;

        int numberTwo = (int)(Math.random() * range) + min;
        numberTwo++;

        correctAnswer = numberOne * numberTwo;
        int wrongAnswer1 = correctAnswer - (random.nextInt(9)+1)*10;
        int wrongAnswer2 = correctAnswer + (random.nextInt(9)+1)*10;

        textObjectView1.setText(""+numberOne);
        textObjectView2.setText(""+numberTwo);


        int buttonLayout = random.nextInt(3);
        switch (buttonLayout){
            case 0:
                buttonObjectChoice1.setText(""+correctAnswer);
                buttonObjectChoice2.setText(""+wrongAnswer1);
                buttonObjectChoice3.setText(""+wrongAnswer2);
                break;

            case 1:
                buttonObjectChoice2.setText(""+correctAnswer);
                buttonObjectChoice3.setText(""+wrongAnswer1);
                buttonObjectChoice1.setText(""+wrongAnswer2);
                break;

            case 2:
                buttonObjectChoice3.setText(""+correctAnswer);
                buttonObjectChoice1.setText(""+wrongAnswer1);
                buttonObjectChoice2.setText(""+wrongAnswer2);
                break;
        }
    }


    void updateScore(int answerGiven){

        if(isCorrect(answerGiven)){
            currentScore++;
        }else{
            currentScore = 0;
        }

        textObjectScore.setText("Score: " + currentScore);


    }

    boolean isCorrect(int answerGiven){
        boolean correctTrueOrFalse;
        if(answerGiven == correctAnswer){
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse=true;
        }else{
            Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse=false;
        }

        return correctTrueOrFalse;
    }

}
