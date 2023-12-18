package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String name;

    private final String[] types = {"Цельсий", "Фаренгейт", "Кельвин", "Реомюр", "Ранкин"};
    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                types
        );

        binding.spinner.setAdapter(adapter);

        binding.spinner.setOnItemSelectedListener((SimpleListener) (parent, view, position, id) -> {
            name = parent.getSelectedItem().toString();
            if(binding.input.length() != 0){
                result(name);
            }
        });

        binding.input.addTextChangedListener((SimpleWatcher) (s, start, before, count) -> {
            result(name);
        });
    }

    @SuppressLint("SetTextI18n")
    private void result(String name){
        float inCels = toCelsium(name, Float.parseFloat(binding.input.getText().toString()));
        float[] results = fromCelsium(inCels);

        binding.celsiy.setText(inCels + "°C");
        binding.farengate.setText(results[0] + "°F");
        binding.kelvin.setText(results[1] + "K");
        binding.reomur.setText(results[2] + "°Ré");
        binding.rankin.setText(results[3] + "°R");
    }


    private float toCelsium(String name, float num){

        if (types[1].equals(name))
            return ((num - 32) * 5 / 9);

        if (types[2].equals(name))
            return (num - 273.15f);

        if (types[3].equals(name))
            return (num * 5 / 4);

        if (types[4].equals(name))
            return ((num - 491.67f) * 5 / 9);

        return num;
    }

    private float[] fromCelsium(float num){
        return new float[]{(num * 9 / 5 + 32), (num + 273.15f), (num * 4 / 5), (num * 9 / 5 + 495.67f)};
    }

}