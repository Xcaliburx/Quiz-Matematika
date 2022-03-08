package com.example.quizmatematika;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizmatematika.Adapter.UserScoreAdapter;
import com.example.quizmatematika.Model.UserScore;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT_LIST = "extra_object";
    private RecyclerView rvUser;
    public UserScoreAdapter userAdapter;
    private ArrayList<UserScore> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        rvUser = findViewById(R.id.rv_user);
        users = (ArrayList<UserScore>) getIntent().getSerializableExtra(EXTRA_OBJECT_LIST);
        userAdapter = new UserScoreAdapter(users);
        rvUser.setAdapter(userAdapter);
        rvUser.setLayoutManager(new LinearLayoutManager(this));
    }
}