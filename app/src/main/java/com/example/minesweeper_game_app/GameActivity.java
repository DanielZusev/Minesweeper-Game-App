package com.example.minesweeper_game_app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minesweeper_game_app.logic.CellAdapter;
import com.example.minesweeper_game_app.logic.Game;

import static java.lang.Thread.sleep;


public class GameActivity extends AppCompatActivity  implements  SensorServiceListener{


    SensorsService.SensorServiceBinder mBinder;
    boolean isBound = false;

    Thread manageThread;
    Game mGame;
    private Context gameActivity ;
    GridView mGridView;
    Button restartButton;
    ImageView flagButton;
    CellAdapter mCellAdapter;
    Chronometer timer;
    boolean isMineAdded = false;
    SensorServiceListener.ALARM_STATE alarmState = ALARM_STATE.OFF ;
    int boardSize;
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            mGame.revealBoard();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    mCellAdapter.notifyDataSetChanged();
                    try {

                        Thread.sleep(  2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    });

    Thread addMinesAndUncoverThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(alarmState== ALARM_STATE.ON) {
              isMineAdded= mGame.sensorIsActive();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(isMineAdded)
                        Toast.makeText(gameActivity, "Mine Added!", Toast.LENGTH_SHORT).show();
                        mCellAdapter.notifyDataSetChanged();
                    }
                });
                if(mGame.getmBoard().getNumOfMines()==boardSize*boardSize) {
                    t.start();
                    openEndActivity(false, boardSize);
                }
                try {
                    sleep(5000  );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameActivity = GameActivity.this;

        setContentView(R.layout.activity_game);

        boardSize = getIntent().getIntExtra("BOARD_SIZE", 0);
        mGame = new Game(boardSize, boardSize);

        mGridView = findViewById(R.id.gridView);
        restartButton = findViewById(R.id.restart_button);
        flagButton = findViewById(R.id.flag_button);

        mGridView.setNumColumns(boardSize);
        mCellAdapter = new CellAdapter(this, mGame.getmBoard());
        mGridView.setAdapter(mCellAdapter);

        timer = findViewById(R.id.chronometer);
        timer.setFormat("Time: %s");
        timer.start();
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mGame.getmBoard().CreateNewBoard(boardSize);
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                    mCellAdapter.notifyDataSetChanged();

            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Boolean clickValue = mGame.OnClick(position);
                boolean dirty = false;
                mCellAdapter.notifyDataSetChanged();

                    if (clickValue == null) {
                        return;
                    } else if (clickValue) {
                        t.start();
                        openEndActivity(false, boardSize);
                    } else {
                        t.start();
                        openEndActivity(true, boardSize);
                    }

            }
        });
        flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGame.ToggleFlagged();
                if (mGame.isFlagged())
                    flagButton.setImageResource(R.drawable.flag_on);
                else
                    flagButton.setImageResource(R.drawable.flagged);
            }
        });
    }

    public void openEndActivity(Boolean endStatus, int boardSize) {
        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra("END_STATUS", endStatus);
        intent.putExtra("BOARD_SIZE", boardSize);
        intent.putExtra("TIME", toTime(timer));
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isBound) {
            mBinder.startSensors();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isBound) {
            mBinder.stopSensors();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, SensorsService.class);
        Log.d("On start", "binding to service...");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (isBound) {
            unbindService(mConnection);
            isBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Service Connection", "bound to service");
            mBinder = (SensorsService.SensorServiceBinder) service;
            mBinder.registerListener(GameActivity.this);
            Log.d("Service Connection", "registered as listener");
            isBound = true;
            mBinder.startSensors();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
        }
    };


    @Override
    public void alarmStateChanged(ALARM_STATE state) {
        if(state == ALARM_STATE.ON){
            this.alarmState=ALARM_STATE.ON;
          manageThread=new Thread(this.addMinesAndUncoverThread);
          manageThread.start();
        }else{
            this.alarmState=ALARM_STATE.OFF;

            manageThread.interrupt();
        }

    }

 public static int toTime(Chronometer timer){
        long time = SystemClock.elapsedRealtime() - timer.getBase();
        int h = (int)(time /3600000);
        int m = (int)(time - h*3600000)/60000;
        int s = (int)(time - h*3600000- m*60000)/1000 ;
        int intTime =m*60 + s;
        return intTime;
    }

    public static String fromTime(int time){
        int h = time / 3600;
        int m = (int)(time - h*3600)/60;
        int s = (int)(time - h*3600 - m*60);
        String timer = m + ":" + s;
        return timer;
    }
}
