package ru.esaulov.player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("С Новым годом!");

        final Button btnStart = (Button) findViewById(R.id.buttonStart);
        final Button btnStop = (Button) findViewById(R.id.buttonStop);

        btnStart.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, PlayerService.class));
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, PlayerService.class));
            }
        });
    }
}