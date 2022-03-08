package com.example.quizmatematika.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizmatematika.Model.UserScore;
import com.example.quizmatematika.R;

import java.util.ArrayList;

public class UserScoreAdapter extends RecyclerView.Adapter<UserScoreAdapter.UserViewHolder>{

    private ArrayList<UserScore> users;

    public UserScoreAdapter(ArrayList<UserScore> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_score_layout, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserScore user = users.get(position);
        holder.tvName.setText(user.getName());
        holder.tvScore.setText(String.valueOf(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addProduct(UserScore user){
        users.add(user);
        this.notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvScore;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_number);
        }
    }
}
