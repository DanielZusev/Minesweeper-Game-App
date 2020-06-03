package com.example.minesweeper_game_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.minesweeper_game_app.logic.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;


public class EndActivity extends AppCompatActivity {
    private static final int EASY = 5;
    private static final int HARD = 6;
    private static final int EXTREME = 7;
    boolean endStatus;
    ImageView imageView;
    int boardSize;
    int playersTime;
    int playersLevel;
    private SharedPreferences sharedPreferences;
    private FragmentTransaction fragmentTransaction;
    private Button submitButton;
    private EditText editText;
    private TextView textView;
    private ArrayList<Player> list;
    private ImageView animation;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        imageView = findViewById(R.id.end_screen_image);
        endStatus = getIntent().getBooleanExtra("END_STATUS", false);
        boardSize = getIntent().getIntExtra("BOARD_SIZE", 0);
        playersTime = getIntent().getIntExtra("TIME",0);
        submitButton = findViewById(R.id.submit_button);
        editText = findViewById(R.id.text_box);
        textView = findViewById(R.id.new_record_text);
        animation = findViewById(R.id.animation);

        newRecordAppearance(false);
        sharedPreferences = getSharedPreferences("DB", MODE_PRIVATE);
        playersLevel = sharedPreferences.getInt("level",0);
        sharedPreferences = getSharedPreferences("Scores", MODE_PRIVATE);
        if (endStatus) {
            setVictory();
            checkNewRecord();
        }
        else {
            setGameOver();
        }

    }

    public void setVictory() {
        imageView.setImageResource(R.drawable.victory);
        animation.setBackground(getDrawable(R.drawable.win_animation));
    }

    public void setGameOver() {
        imageView.setImageResource(R.drawable.game_over);
        animation.setBackground(getDrawable(R.drawable.game_over_animation));
    }

    public void endGame(View v) {
        finishAffinity();
        System.exit(0);
    }

    public void startNewGame(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("BOARD_SIZE", boardSize);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("BOARD_SIZE", boardSize);
        startActivity(intent);
        finish();
    }

    public void checkNewRecord(){
        switch (this.playersLevel){
            case EASY: updateRecords("EASY");break;
            case HARD: updateRecords("HARD");break;
            case EXTREME: updateRecords("EXTREME");break;
        }
    }

    public void updateRecords(final String level){
        list = retrieveList(level);
        player = new Player();
        player.setTime(playersTime);
        if(list == null || list.isEmpty() || list.size() < 3){
            newRecordAppearance(true);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = editText.getText().toString();
                    if(validateName(name)){
                        player.setName(name);
                        list.add(player);
                        storeList(list, level);
                        newRecordAppearance(false);
                        onStart();
                    }
                }
            });
        }
        else if (list.get(list.size() - 1).compareTo(player) > 0){
            newRecordAppearance(true);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Player player = new Player();
                    String name = editText.getText().toString();
                    if(validateName(name)){
                        player.setName(name);
                        player.setTime(playersTime);
                        list.add(player);
                        Collections.sort(list);
                        if (list.size() > 3){
                            list.remove(3);
                        }
                        storeList(list, level);
                        newRecordAppearance(false);
                        onStart();
                    }
                }
            });
        }
        else if (list.get(list.size() - 1).compareTo(player) <= 0){
            return;
        }
    }
    private ArrayList<Player> retrieveList(String level){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Player>>() {}.getType();
        String json = sharedPreferences.getString(level,null);
        ArrayList<Player> list = gson.fromJson(json,type);
        if(list == null){
            list = new ArrayList<>();
        }
        return list;
    }

    private void storeList(ArrayList<Player> list, String level){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Player>>() {}.getType();
        String json = gson.toJson(list,type);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(level,json);
        editor.apply();
    }

    private boolean validateName(String name){
        if (name == null){
            Toast.makeText(this, "Invalid Name Added", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            name.trim();
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void newRecordAppearance(boolean isAppeared){
        if (isAppeared){
            submitButton.setVisibility(View.VISIBLE);
            editText.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            animation.setVisibility(View.INVISIBLE);
        }else {
            submitButton.setVisibility(View.GONE);
            editText.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            animation.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (animation.getVisibility() == View.VISIBLE) {
            ((AnimationDrawable) animation.getBackground()).start();
        }
    }
}

