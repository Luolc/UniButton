package com.luolc.unibuttonsample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.luolc.unibutton.SimpleUniButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleUniButton key1 = (SimpleUniButton) findViewById(R.id.btn_1);
        key1.setText("1");
//        key1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.number_key_icon_normal));
        key1.setBackgroundNormal(ContextCompat.getDrawable(this, R.drawable.number_key_icon_normal));
//        key1.setBackgroundPressed(ContextCompat.getDrawable(this, R.drawable.number_key_icon_pressed));
    }
}
