package com.example.admin.mindtrainer;

import android.annotation.SuppressLint;
import android.graphics.Camera;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Parcel;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    int locationOfCorrectAnswer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    Button nextButton;
    TextView sumTextView;
    TextView resultTextView;
    TextView timerTextView;
    TextView pointsTextView;

    TextView finalScoreTextView;
    TextView finalScoreCardTextView;
    TextView finalAnsweredTextView;
    TextView finalCorrectTextView;
    TextView finalInCorrectTextView;

    ConstraintLayout startConstraintLayout;
    ConstraintLayout gameConstraintLayout;
    ConstraintLayout mainConstraintLayout;
    ConstraintLayout finalConstraintLayout;


    int score=0;
    int numberOfQuestions=0;

    int finalScore;


    ArrayList<Integer> answers = new ArrayList<Integer>();






     public String calculateFinalScore(){

         finalScore = score/numberOfQuestions;

         finalScore = (score*1000/numberOfQuestions);

         return (Integer.toString(finalScore));
     }


    public void nextButton(View view){


       // nextButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(View.INVISIBLE);
        finalConstraintLayout.setVisibility(View.VISIBLE);
        startConstraintLayout.setVisibility(View.INVISIBLE);

      //  Log.i("Score is :" , valueOf(score*1000/numberOfQuestions));


      //  finalScoreTextView.setText(valueOf(score*1000/numberOfQuestions));
     //  finalScoreTextView.setText(valueOf((score/numberOfQuestions)*1000));
       finalScoreTextView.setText(valueOf(calculateFinalScore()));
       finalScoreTextView.setTextColor(Color.YELLOW);



      finalScoreTextView.animate().rotation(1800).setDuration(1000);




        finalScoreTextView.setTextColor(Color.RED);
        finalAnsweredTextView.setText("Answered :" + numberOfQuestions);
       finalCorrectTextView.setText("Corret :" + score);
       finalInCorrectTextView.setText("Incorrect :" +(numberOfQuestions - score));

        }

         public void playAgain(View view){

               resultTextView.setText("");
              score = 0;
                numberOfQuestions=0;
                timerTextView.setText("30s");
                pointsTextView.setText("0/0");
                sumTextView.setTextSize(30);
                resultTextView.setTextSize(38);
                generateQuestion();
                nextButton.setVisibility(View.INVISIBLE);


                button0.setEnabled(true);
              button1.setEnabled(true);
              button2.setEnabled(true);
              button3.setEnabled(true);

              gameConstraintLayout.setVisibility(View.VISIBLE);


              new CountDownTimer(30000 + 100, 1000) {
                  @Override
                  public void onTick(long millisUntilFinished) {

                      timerTextView.setText(String.valueOf(millisUntilFinished/1000 + "s"));

                  }

                  @Override
                  public void onFinish() {

                     timerTextView.setText("0s");
                     pointsTextView.setText("0/0");
                      sumTextView.setTextColor(Color.RED);
                      sumTextView.setTextSize(25);
                      sumTextView.setText("Time Over...!!!");
                      button0.setEnabled(false);
                      button1.setEnabled(false);
                      button2.setEnabled(false);
                      button3.setEnabled(false);
                      nextButton.setVisibility(View.VISIBLE);
                      resultTextView.setTextSize(25);
                      resultTextView.setTextColor(Color.BLACK);
                    resultTextView.setText("Tap 'NEXT' to see the Final Score");

                  }
              }.start();

              }


       public void chooseAnswer(View view){

           if(view.getTag().equals(Integer.toString(locationOfCorrectAnswer))){

               score++;
               Log.i("tag" , (String) view.getTag());
               resultTextView.setTextColor(Color.BLUE);
               resultTextView.setText("Correct...!!!");



           }else {
               resultTextView.setTextColor(Color.RED);
               resultTextView.setText("Wrong...!!!");

               Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                  vibrator.vibrate(90);

           }

              numberOfQuestions++;
             pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
             generateQuestion();

    }


    public void generateQuestion(){

         Random rand = new Random();

         int a= rand.nextInt(21);
         int b = rand.nextInt(21);

         sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

         locationOfCorrectAnswer = rand.nextInt(4);

         answers.clear();

         int incorrectAnswers;

         for(int i=0;i<4;i++){
             if(locationOfCorrectAnswer == i){

                 answers.add(a+b);

             }else{


                 incorrectAnswers = rand.nextInt(41);

                 while(incorrectAnswers == a+b){

                     incorrectAnswers = rand.nextInt(41);
                 }
                 answers.add(incorrectAnswers);
             }

      }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText((Integer.toString(answers.get(1))));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

     }

    public void startGame(View view) {

       startConstraintLayout.setVisibility(View.INVISIBLE);
       gameConstraintLayout.setVisibility(View.VISIBLE);
       finalConstraintLayout.setVisibility(View.INVISIBLE);
       playAgain(findViewById(R.id.playAgainButton));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        startButton = (Button) findViewById(R.id.startButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
         pointsTextView = (TextView) findViewById(R.id.pointsTextView);

         //  All the Methods for the Final Score card..............................................................
        finalConstraintLayout = (ConstraintLayout) findViewById(R.id.finalConstraintLayout);
         finalScoreCardTextView = (TextView) findViewById(R.id.finalScoreCardTextView);
         finalScoreTextView = (TextView) findViewById(R.id.finalScoreTextView);
         finalAnsweredTextView = (TextView) findViewById(R.id.finalAnswered);
         finalCorrectTextView = (TextView) findViewById(R.id.finalCorrect);
         finalInCorrectTextView = (TextView) findViewById(R.id.finalIncorrect);
        //...........................................................................................................


        startConstraintLayout = (ConstraintLayout) findViewById(R.id.startConstraintLayout);
        gameConstraintLayout = (ConstraintLayout) findViewById(R.id.gameConstraintLayout);
       // mainConstraintLayout = (ConstraintLayout) findViewById(R.id.mainConstraintLayout);


        }
}
