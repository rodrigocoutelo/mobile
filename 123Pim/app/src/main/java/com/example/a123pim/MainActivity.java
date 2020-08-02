package com.example.a123pim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonNext;
    TextView textCount;
    EditText editTextTypeSomething;
    private final String STATE_COUNT = "STATE_COUNT";
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            count = 0;
        } else {
            count = savedInstanceState.getInt(STATE_COUNT);
        }

        this.buttonNext = this.findViewById(R.id.buttonNext);
        this.textCount = this.findViewById(R.id.textCount);
        this.editTextTypeSomething = this.findViewById(R.id.editTextTextTypeSomething);

        buttonNext.setOnClickListener(this);
        textCount.setText(String.valueOf(count));

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_COUNT, count);
        super.onSaveInstanceState(savedInstanceState);
    }

    public boolean checkPim(int number) {

        boolean check = false;

        if (number%4==0) {
            check = true;
        }

        return check;
    }

    @Override
    public void onClick(View view) {
        count = count + 1;
        if (this.checkPim(count)) {
            textCount.setText("PIM");
        } else {
            textCount.setText(String.valueOf(count));
        }
    }

}