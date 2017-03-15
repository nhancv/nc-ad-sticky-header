package com.nhancv.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btNonKeepHeader = (Button) findViewById(R.id.btNonKeepHeader);
        Button btKeepHeader = (Button) findViewById(R.id.btKeepHeader);

        btNonKeepHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StyleNonKeepActivity.class));
            }
        });

        btKeepHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StyleKeepActivity.class));
            }
        });
    }
}
