package com.example.app.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.db.DB;

public class fragment_list extends Fragment {

    TableLayout table_layout;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        table_layout = (TableLayout) view.findViewById(R.id.tableLayout);
        this.view = view;
        carregaDados();
    }

    private void carregaDados() {
        resetTable();
        Cursor cursor = DB.INSTANCE.carregaDados();
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    int cols = cursor.getColumnCount();
                    TableRow row = new TableRow(view.getContext());
                    row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    for (int j = 0; j < cols; j++) {
                        TextView tv = new TextView(view.getContext());
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(18);
                        tv.setPadding(getColumnPadding(j), 5, 0, 5);

                        tv.setText(cursor.getString(j));
                        row.addView(tv);

                    }
                    table_layout.addView(row);
                } while (cursor.moveToNext());
            }
        }
    }

    private int getColumnPadding(int j) {
        switch (j) {
            case 0:
            default:
                return 20;
            case 1:
                return 165;
            case 2:
                return 260;
            case 3:
                return 200;
        }
    }

    private void resetTable() {
        if (table_layout.getChildCount() > 1) {
            int count = table_layout.getChildCount();
            for (int i = 1; i < count; i++) {
                View child = table_layout.getChildAt(i);
                if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        carregaDados();
    }

}