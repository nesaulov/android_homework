package ru.esaulov.sport;

/**
 * Created by nesaulov on 22/01/2018.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class SportActivity extends AppCompatActivity {
    public static final String EXTRA_SPORTID = "sportId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        int sportId = (Integer)getIntent().getExtras().get(EXTRA_SPORTID);
        Sport sport = Sport.sports[sportId];

        ImageView imageView = findViewById(R.id.photo);
        imageView.setImageResource(sport.getImageResourceId());
        imageView.setContentDescription(sport.getName());

        TextView name = findViewById(R.id.name);
        name.setText(sport.getName());

        TextView description = findViewById(R.id.description);
        description.setText(sport.getDescription());
    }
}
