package com.example.calculator;

import android.view.View;
import android.widget.AdapterView;

public interface SimpleListener extends AdapterView.OnItemSelectedListener {
    @Override
    void onItemSelected(AdapterView<?> parent, View view, int position, long id);

    @Override
    default void onNothingSelected(AdapterView<?> parent) {};
}
