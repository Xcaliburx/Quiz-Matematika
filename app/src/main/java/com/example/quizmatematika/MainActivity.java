package com.example.quizmatematika;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizmatematika.Adapter.UserScoreAdapter;
import com.example.quizmatematika.Model.UserScore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT_DATA = "extra_object";

    private EditText etName;
    private Button btnPlay;
    private Button btnView;
    private TextView tvText1;
    private TextView tvText2;

    public ArrayList<UserScore> users;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Bundle temp = data.getExtras();
            if(temp != null) {
                boolean check = false;
                int idx = -1;
                UserScore userPlayed = (UserScore) temp.getSerializable("User");
                for(int a = 0; a < users.size(); a++){
                    if(users.get(a).getName().contentEquals(userPlayed.getName())){
                        idx = a;
                        check = true;
                    }
                }
                if(check){
                    users.get(idx).setScore(users.get(idx).getScore() + userPlayed.getScore());
                }else {
                    users.add(userPlayed);
                }
            }else{
                Toast.makeText(MainActivity.this, "Ga ada data", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(MainActivity.this, "Result Code Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        btnPlay = findViewById(R.id.btn_play);
        btnView = findViewById(R.id.btn_view);
        initProducts();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                    UserScore user = new UserScore();
                    user.setName(etName.getText().toString());
                    user.setScore(0);
                    intent.putExtra(PlayActivity.EXTRA_OBJECT_USER, user);
                    startActivityForResult(intent, 1);
//                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Please input your name", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(users.size() == 0){
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra(ViewActivity.EXTRA_OBJECT_LIST, users);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validate(){
        if(etName.getText().toString().equals("")) return false;
        else return true;
    }

    public void initProducts(){
        users = new ArrayList<>();

        UserScore user1 = new UserScore();
        user1.setName("Budi");
        user1.setScore(100);

        UserScore user2 = new UserScore();
        user2.setName("Andi");
        user2.setScore(150);

        UserScore user3 = new UserScore();
        user3.setName("Joni");
        user3.setScore(50);

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }


}