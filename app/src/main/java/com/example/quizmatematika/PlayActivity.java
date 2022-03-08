package com.example.quizmatematika;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizmatematika.Adapter.UserScoreAdapter;
import com.example.quizmatematika.Model.UserScore;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT_USER = "extra_object";

    private TextView tvScore;
    private TextView tvNum1;
    private TextView tvOperator;
    private TextView tvNum2;
    private TextView tvAnswer;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btnErase;
    private Button btnSubmit;

    UserScore user;

    Random rand = new Random();

    int num1 = rand.nextInt(200) + 50;
    int num2 = rand.nextInt(50);
    char op = randOperator();

    @Override
    public void onBackPressed() {
        UserScore userPlay = new UserScore();
        userPlay.setName(user.getName());
        userPlay.setScore(user.getScore());

        Intent intent = new Intent();
        intent.putExtra("User", userPlay);
        setResult(Activity.RESULT_OK, intent);
//        Toast.makeText(PlayActivity.this, "Data masuk " + userPlay.getName() + " " + userPlay.getScore(), Toast.LENGTH_LONG).show();
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        tvScore = findViewById(R.id.tv_score);
        tvNum1= findViewById(R.id.tv_number1);
        tvNum2 = findViewById(R.id.tv_number2);
        tvOperator = findViewById(R.id.tv_operator);
        tvAnswer = findViewById(R.id.answer);

        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnErase = findViewById(R.id.btn_erase);
        btnSubmit = findViewById(R.id.btn_submit);

        user = (UserScore) getIntent().getSerializableExtra(EXTRA_OBJECT_USER);

        tvNum1.setText(String.valueOf(num1));
        tvNum2.setText(String.valueOf(num2));
        tvOperator.setText(String.valueOf(op));

        tvScore.setText(String.valueOf(user.getScore()));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "9");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText(tvAnswer.getText().toString() + "0");
            }
        });

        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setText("");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = result(num1,num2,op);
                String result = tvAnswer.getText().toString();
                if(result.length() > 0) {
                    if (answer.equals(result)) {
                        user.setScore(user.getScore() + 10);
                    }else {
                        if (user.getScore() > 0) {
                            user.setScore(user.getScore() - 10);
                        }else{
                            user.setScore(0);
                        }
                    }
                    tvScore.setText(String.valueOf(user.getScore()));

                    num1 = rand.nextInt(200) + 50;
                    num2 = rand.nextInt(50);
                    op = randOperator();

                    tvNum1.setText(String.valueOf(num1));
                    tvNum2.setText(String.valueOf(num2));
                    tvOperator.setText(String.valueOf(op));

                    tvAnswer.setText("");
                }else{
                    Toast.makeText(PlayActivity.this, "Insert your answer", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String result(int num1, int num2, char op){
        int score;
        if(op == '+'){
            score = num1 + num2;
        }else if(op == '-'){
            score = num1 - num2;
        }else if(op == '*'){
            score = num1 * num2;
        }else if(op == '/'){
            score = num1 / num2;
        }else{
            score = 0;
        }
        String res = String.valueOf(score);
        return res;
    }

    private char randOperator(){
        Random rand = new Random();

        char op = 'a';
        int num = rand.nextInt(4);
        switch (num){
            case 0:
                op = '+';
                break;
            case 1:
                op = '-';
                break;
            case 2:
                op = '*';
                break;
            case 3:
                op = '/';
                break;
        }
        return op;
    }
}