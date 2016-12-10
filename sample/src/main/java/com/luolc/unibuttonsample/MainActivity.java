package com.luolc.unibuttonsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luolc.unibutton.UniButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UniButton key1 = (UniButton) findViewById(R.id.btn_1);
        key1.setText("1");
        key1.setTopLeftRadius(40);
    }
}
