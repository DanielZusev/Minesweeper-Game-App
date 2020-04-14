package com.example.minesweeper_game_app.logic;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.minesweeper_game_app.CellView;

public class CellAdapter extends BaseAdapter {

    private static final String TAG = "Cell Adapter";
    private Board mBoard;
    private Context mContext;

    public CellAdapter(Context context, Board board) {
        this.mBoard = board;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        Log.d(TAG, "Count returned " + mBoard.getBoardSize());
        return mBoard.getBoardSize();
    }

    @Override
    public Object getItem(int position) {
        return mBoard.getCell(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        CellView cellView;

        cellView = (CellView)convertView;
        if(cellView == null) {
            Log.d(TAG, "View created " + position);
            cellView = new CellView(mContext);
        }

        Cell cell = mBoard.getCell(position);
        cellView.setText(cell.getmState().toString());
        Log.d(TAG, "View returned " + position);
        return cellView;
    }
}
