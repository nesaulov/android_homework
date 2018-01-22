package ru.esaulov.handler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int counter = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserAttention();
    }

    public void onClick(View view) {
        counter = 0;
        getUserAttention();
    }

    private void getUserAttention() {
        handler.post(task);
    }

    Runnable task = new Runnable() {
        @Override
        public void run() {
            ImageView imageView = findViewById(R.id.image);
            if (counter % 2 == 0) {
                imageView.setVisibility(View.INVISIBLE);
            } else {
                imageView.setVisibility(View.VISIBLE);
            }
            counter++;
            if(counter < 8) {
                handler.postDelayed(this, 400);
            }
        }
    };
}