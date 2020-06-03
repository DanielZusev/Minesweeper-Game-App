package com.example.minesweeper_game_app.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.minesweeper_game_app.R;
import com.example.minesweeper_game_app.logic.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class RecordsFragment extends Fragment {
    private static final int SCORES = 9;
    private TextView easyName1,easyName2,easyName3,hardName1,hardName2,hardName3,extremeName1,extremeName2,extremeName3,
                    easyScore1,easyScore2,easyScore3,hardScore1,hardScore2,hardScore3,extremeScore1,extremeScore2,extremeScore3;
    private SharedPreferences sharedPreferences;
    private TextView[] names,scores;
    private TableLayout tableLayout;

    public RecordsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_records, container, false);
        this.tableLayout = view.findViewById(R.id.record_table);
        names = new TextView[9];
        scores = new TextView[9];

        names[0] = (easyName1 = view.findViewById(R.id.easy_first_name));
        names[1] = (easyName2 = view.findViewById(R.id.easy_second_name));
        names[2] = (easyName3 = view.findViewById(R.id.easy_third_name));
        names[3] = (hardName1 = view.findViewById(R.id.hard_first_name));
        names[4] = (hardName2 = view.findViewById(R.id.hard_second_name));
        names[5] = (hardName3 = view.findViewById(R.id.hard_third_name));
        names[6] = (extremeName1 = view.findViewById(R.id.extreme_first_name));
        names[7] = (extremeName2 = view.findViewById(R.id.extreme_second_name));
        names[8] = (extremeName3 = view.findViewById(R.id.extreme_third_name));

        scores[0] = (easyScore1 = view.findViewById(R.id.easy_first_time));
        scores[1] = (easyScore2 = view.findViewById(R.id.easy_second_time));
        scores[2] = (easyScore3 = view.findViewById(R.id.easy_third_time));
        scores[3] = (hardScore1 = view.findViewById(R.id.hard_first_time));
        scores[4] = (hardScore2 = view.findViewById(R.id.hard_second_time));
        scores[5] = (hardScore3 = view.findViewById(R.id.hard_third_time));
        scores[6] = (extremeScore1 = view.findViewById(R.id.extreme_first_time));
        scores[7] = (extremeScore2 = view.findViewById(R.id.extreme_second_time));
        scores[8] = (extremeScore3 = view.findViewById(R.id.extreme_third_time));

        sharedPreferences = this.getActivity().getSharedPreferences("Scores", MODE_PRIVATE);

        String easyJson = sharedPreferences.getString("EASY", null);
        String hardJson = sharedPreferences.getString("HARD", null);
        String extremeJson = sharedPreferences.getString("EXTREME", null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Player>>() {}.getType();
        ArrayList<Player> all,easy,hard,extreme;
        easy = gson.fromJson(easyJson,type);
        hard = gson.fromJson(hardJson,type);
        extreme = gson.fromJson(extremeJson,type);

        all = new ArrayList<>();
        if (easy == null) easy = new ArrayList<>();
        if (hard == null) hard = new ArrayList<>();
        if (extreme == null) extreme = new ArrayList<>();

        Collections.sort(easy);
        Collections.sort(hard);
        Collections.sort(extreme);

//        all.addAll(easy);
//        all.addAll(hard);
//        all.addAll(extreme);

//        for(int i=0; i < all.size() && i < 9; i++){
//            names[i].setText(all.get(i).getName());
//            scores[i].setText("" + all.get(i).getTime());
//        }

        for (int j = 0, i = 0; i< easy.size() && j < 3; i++ , j++){
            names[i].setText(easy.get(j).getName());
            scores[i].setText("" + easy.get(j).getTime());
        }
        for (int j = 0, i = 3; i< hard.size()+3 && j < 3; i++ , j++){
            names[i].setText(hard.get(j).getName());
            scores[i].setText("" + hard.get(j).getTime());
        }
        for (int j = 0, i = 6; i< extreme.size()+6 && j < 3; i++ , j++){
            names[i].setText(extreme.get(j).getName());
            scores[i].setText("" + extreme.get(j).getTime());
        }
        return view;
    }
}