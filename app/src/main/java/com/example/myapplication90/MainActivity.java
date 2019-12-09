package com.example.myapplication90;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {





    SharedPreferences sharedPreferences;
    int highscore= 0;//Integer.parseInt(sharedPreferences.getString("highschore1","0"));




//preferences.edit().putInt(SELECTED_POSITION, position).commit();

    public void changehighscore(){
        highscore=Integer.parseInt(sharedPreferences.getString("highschore1","0"));
        if(score>highscore)

        try {



            sharedPreferences.edit().putString("highschore1",Integer.toString(score)).apply();
        }
        catch (Exception ex){
            ex.printStackTrace();

        }


    }

    private static final String TAG = "hello";
    Button button0;
    Button button1;
    Button button2;
    int sum;
    Button button3;
    TextView pointtextview;
    TextView resulttextview;
    Button startButton;
    int score=0;
    int locationofincorrectanswer;
    TextView sumtextView ;
    int noofquestions=0;
    RelativeLayout gamelayout;
    TextView timertextview;
    Button palyagainbutton;
    RelativeLayout  playagainlayout;
    TextView finalscore;

    ArrayList<Integer> answer = new ArrayList<Integer>();

    public void playagain(View v){
        score = 0;
        noofquestions=0;
        timertextview.setText("30s");
        pointtextview.setText("0/0");
        playagainlayout.setVisibility(RelativeLayout.INVISIBLE);
        generateQuestions();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {

                timertextview.setText(String.valueOf(l/1000)+"s");


            }

            @Override
            public void onFinish() {
                timertextview.setText("0s");
                changehighscore();

                finalscore.setText("Your Final Score is:"+Integer.toString(score)+"/"+Integer.toString(noofquestions)+"\nHigh Score:"+sharedPreferences.getString("highschore1","0"));
                playagainlayout.setVisibility(RelativeLayout.VISIBLE);

            }
        }.start();


    }


    public void generateQuestions(){

        Random rand = new Random();
        int a= rand.nextInt(21);
        int b= rand.nextInt(32);
        sum=a+b;


        sumtextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationofincorrectanswer=rand.nextInt(4);
        Log.i(TAG, "generateQuestions: "+Integer.toString(locationofincorrectanswer));


        answer.clear();
        int incorrectAnswer;
        for(int i=0;i<4;i++){
            if(i==locationofincorrectanswer){
                answer.add(sum);
                Log.i(TAG, "generateQuestions: "+Integer.toString(sum));
                Log.i(TAG, "generateQuestions: "+Integer.toString(a)+"  :"+Integer.toString(b));

            }else{

                incorrectAnswer=rand.nextInt(52);
                while (incorrectAnswer==sum){
                    incorrectAnswer=rand.nextInt(52);
                }
                answer.add(incorrectAnswer);


            }



        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));




    }
    public void chooseanswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationofincorrectanswer))) {
            score++;


            resulttextview.setText("correct");

        }
        else {
            resulttextview.setText("incorrect");
            score=score-1;
        }
        noofquestions++;
        pointtextview.setText(Integer.toString(score)+"/"+Integer.toString(noofquestions));
        generateQuestions();

    }



    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
            gamelayout.setVisibility(RelativeLayout.VISIBLE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
       // changehighscore();


        pointtextview=(TextView)findViewById(R.id.PointtextView);
        resulttextview=(TextView)findViewById(R.id.resulttextview) ;

        button0=(Button)findViewById(R.id.button2);
        button1=(Button)findViewById(R.id.button3);
        button2=(Button)findViewById(R.id.button4);
        button3=(Button)findViewById(R.id.button5);
        sumtextView=(TextView)findViewById(R.id.SumtextView);
        gamelayout=(RelativeLayout)findViewById(R.id.gamelayout) ;
        startButton=(Button)findViewById(R.id.startbutton);
        timertextview =(TextView)findViewById(R.id.TimertextView);
        palyagainbutton=(Button)findViewById(R.id.playagain);
        playagainlayout=(RelativeLayout)findViewById(R.id.playagainlayout);
        finalscore = (TextView)findViewById(R.id.finalscore);
        sharedPreferences=this.getSharedPreferences("com.example.myapplication90",Context.MODE_PRIVATE);



        playagain(findViewById(R.id.playagain));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override //reconfigure display properties on screen rotation
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            // handle change here
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            // or here
        }
    }

}
