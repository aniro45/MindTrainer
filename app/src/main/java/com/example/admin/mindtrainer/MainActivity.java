package com.example.admin.mindtrainer;


import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {


    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    int finalScore;

    ImageButton startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    Button nextButton;

    Button beginnerLevelButton;
    Button moderateLevelButton;
    Button veteranLevelButton;

    ImageButton backButton;
    ImageButton infoButton;

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
    ConstraintLayout infoConstraintLayout;
    ConstraintLayout difficultyChooseConstraintLayout;
    ConstraintLayout changeLogConstraintLayout;

/*..................................................................Above This line, All the Variable Are declared....................................................................................*/

    ArrayList<Integer> answers = new ArrayList<>();



   public void cancelFromChangelogButtonClicked(View view){

           changeLogConstraintLayout.setVisibility(View.INVISIBLE);
           infoConstraintLayout.setVisibility(View.VISIBLE);

   }

    public void whatsNewClicked(View view) {

        changeLogConstraintLayout.setVisibility(View.VISIBLE);
        infoConstraintLayout.setVisibility(View.INVISIBLE);

    }

    public void beginnerDifficultyClicked(View view){

        beginnerLevelButton.setPaintFlags(1);
         playAgain(findViewById(R.id.playAgainButton));

    }

    public void moderateDifficultyClicked(View v){

        moderateLevelButton.setPaintFlags(1);
        playAgain(findViewById(R.id.playAgainButton));

    }

    public void veteranDifficultyClicked(View view){

        veteranLevelButton.setPaintFlags(1);
        playAgain(findViewById(R.id.playAgainButton));

    }


    public void quitButtonClicked(View view){
        Toast.makeText(this, "Thank you....", Toast.LENGTH_SHORT).show();
           System.exit(0);

    }

    public void mainMenuClicked(View view){

        finalConstraintLayout.setVisibility(View.INVISIBLE);
        startConstraintLayout.setVisibility(View.VISIBLE);
        beginnerLevelButton.setPaintFlags(0);
        moderateLevelButton.setPaintFlags(0);
        veteranLevelButton.setPaintFlags(0);

        finalScore = 0;
        numberOfQuestions = 0;
        resultTextView.setText("");
        score = 0;
        timerTextView.setText(valueOf("30s"));
        pointsTextView.setText("0/0");
        sumTextView.setTextSize(30);
        resultTextView.setTextSize(38);
        difficultyChooseConstraintLayout.setVisibility(View.INVISIBLE);


    }

       public void infoButtonClicked(View view){

            startConstraintLayout.setVisibility(View.INVISIBLE);
            infoConstraintLayout.setVisibility(View.VISIBLE);


       }

    public void backButtonClicked(View view){

        infoConstraintLayout.setVisibility(View.INVISIBLE);
        startConstraintLayout.setVisibility(View.VISIBLE);
    }


    public String calculateFinalScore(){

        finalScore = score/numberOfQuestions;

        finalScore = (score*1000/numberOfQuestions);

        return (Integer.toString(finalScore));
    }


    public void nextButton(View view){


        // nextButton.setVisibility(View.INVISIBLE);

        gameConstraintLayout.setVisibility(View.INVISIBLE);
        finalConstraintLayout.setVisibility(View.VISIBLE);

        //  Log.i("Score is :" , valueOf(score*1000/numberOfQuestions));

        finalScoreTextView.animate().rotation(1800).setDuration(1000);
        //  finalScoreTextView.setText(valueOf(score*1000/numberOfQuestions));
        //  finalScoreTextView.setText(valueOf((score/numberOfQuestions)*1000));
        finalScoreTextView.setText(valueOf(calculateFinalScore()));
        finalScoreTextView.setTextColor(Color.YELLOW);
        sumTextView.setTextColor(Color.BLACK);



        finalScoreTextView.setTextColor(Color.RED);
        finalAnsweredTextView.setText("Answered :" + numberOfQuestions);
        finalCorrectTextView.setText("Correct :" + score);
        finalInCorrectTextView.setText("Incorrect :" +(numberOfQuestions - score));

    }

    public void playAgain(View view){

        resultTextView.setText("");
        score = 0;
        numberOfQuestions=0;
        timerTextView.setText(valueOf("30s"));
        pointsTextView.setText("0/0");
        sumTextView.setTextSize(30);
        resultTextView.setTextSize(38);

        if (beginnerLevelButton.getPaintFlags() == 1) {
            generateQuestion(21, 21);

        } else if (moderateLevelButton.getPaintFlags() == 1) {
            generateQuestion(41, 41);

        } else if (veteranLevelButton.getPaintFlags() == 1){
            generateQuestion(81, 81);
        }


     /*   if (beginnerLevelButton.isPressed() == true) {
            generateQuestion(21, 21);
        } else if (moderateLevelButton.isPressed() == true) {

            generateQuestion(41, 41);

        } else if (veteranLevelButton.isPressed() == true) {

            generateQuestion(81, 81);
        }
*/

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

                timerTextView.setText(valueOf("0s"));
                pointsTextView.setText(valueOf("0/0"));
                sumTextView.setTextColor(Color.RED);
                sumTextView.setTextSize(25);
                sumTextView.setText(valueOf("Time Over...!!!"));
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                nextButton.setVisibility(View.VISIBLE);
                resultTextView.setTextSize(25);
                resultTextView.setTextColor(Color.BLACK);
                resultTextView.setText(valueOf("Tap 'NEXT' to see the Final Score"));
                }
        }.start();

    }



              public void chooseAnswer(View view) {

                  if (view.getTag().equals(Integer.toString(locationOfCorrectAnswer))) {

                      score++;
                      Log.i("tag", (String) view.getTag());
                      resultTextView.setTextColor(Color.BLUE);
                      resultTextView.setText(valueOf("Correct...!!!"));


                  } else {
                      resultTextView.setTextColor(Color.RED);
                      resultTextView.setText(valueOf("Wrong...!!!"));


                      Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                      vibrator.vibrate(90);

                  }

                  numberOfQuestions++;
                  pointsTextView.setText(score + "/" + numberOfQuestions);

                  if (beginnerLevelButton.getPaintFlags() == 1) {
                      generateQuestion(21, 21);

                  } else if (moderateLevelButton.getPaintFlags() == 1) {
                      generateQuestion(41, 41);

                  } else if (veteranLevelButton.getPaintFlags() == 1){
                      generateQuestion(81, 81);
                  }

             /*     if (beginnerLevelButton.isPressed() == true) {
                      generateQuestion(21, 21);
                  } else if (moderateLevelButton.isPressed() == true) {

                      generateQuestion(41, 41);

                  } else if (veteranLevelButton.isPressed() == true) {

                      generateQuestion(81, 81);
                  }
              */
    }


    public void generateQuestion(int x,int y){

        Random rand = new Random();

        int a= rand.nextInt(x);
        int b = rand.nextInt(y);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswers;

        for(int i=0;i<4;i++){
            if(locationOfCorrectAnswer == i){

                answers.add(a+b);

            }else{


                incorrectAnswers = rand.nextInt(x+y+2);

                while(incorrectAnswers == a+b){

                    incorrectAnswers = rand.nextInt(x+y+2);
                }
                answers.add(incorrectAnswers);
            }

        }

        button0.setText(valueOf(answers.get(0)));
        button1.setText(valueOf(answers.get(1)));
        button2.setText(valueOf(answers.get(2)));
        button3.setText(valueOf(answers.get(3)));

    }

    public void startGame(View view) {


        startConstraintLayout.setVisibility(View.INVISIBLE);
        difficultyChooseConstraintLayout.setVisibility(View.VISIBLE);
        finalConstraintLayout.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        startButton = findViewById(R.id.startButton);
        playAgainButton =findViewById(R.id.playAgainButton);
        nextButton = findViewById(R.id.nextButton);
        infoButton  = findViewById(R.id.infoButton);

        beginnerLevelButton =findViewById(R.id.beginnerLevelButton);
        moderateLevelButton =  findViewById(R.id.moderateLevelButton);
        veteranLevelButton = findViewById(R.id.veteranLevelButton);



        backButton = (findViewById(R.id.backFromInfoToStartPage));
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        timerTextView = findViewById(R.id.timerTextView);
        pointsTextView = findViewById(R.id.pointsTextView);


        //  All the Methods for the Final Score card..............................................................
        finalConstraintLayout = findViewById(R.id.finalConstraintLayout);
        finalScoreCardTextView =findViewById(R.id.finalScoreCardTextView);
        finalScoreTextView = findViewById(R.id.finalScoreTextView);
        finalAnsweredTextView = findViewById(R.id.finalAnswered);
        finalCorrectTextView =  findViewById(R.id.finalCorrect);
        finalInCorrectTextView = findViewById(R.id.finalIncorrect);
        infoConstraintLayout= findViewById(R.id.infoConstraintLayout);
        difficultyChooseConstraintLayout = findViewById(R.id.difficultyChooseConsraintLayout);
        changeLogConstraintLayout = findViewById(R.id.changeLogConstraintLayout);
        //...........................................................................................................


        startConstraintLayout =  findViewById(R.id.startConstraintLayout);
        gameConstraintLayout =  findViewById(R.id.gameConstraintLayout);
        // mainConstraintLayout = findViewById(R.id.mainConstraintLayout);


    }
}