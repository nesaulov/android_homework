package ru.esaulov.callintent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCall = (Button) findViewById(R.id.btnCall);
        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnSend = (Button) findViewById(R.id.btnSend);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:12345678");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                if(isIntentSafe(callIntent))
                    startActivity(callIntent);
                else
                    Toast.makeText(getApplicationContext(), "Your phone has no app to dial!", Toast.LENGTH_SHORT).show();
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo:37.422219,-122.08364?z=14");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                if(isIntentSafe(mapIntent))
                    startActivity(mapIntent);
                else
                    Toast.makeText(getApplicationContext(), "Your phone has no app to view map!", Toast.LENGTH_LONG).show();
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://vk.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

                if(isIntentSafe(webIntent))
                    startActivity(webIntent);
                else
                    Toast.makeText(getApplicationContext(), "Your phone has no app to open webpage!", Toast.LENGTH_LONG).show();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto","androiddev@gmail.com", null));

                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"androiddev@mail.ru"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello!");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "An email from Android App!");

                if(isIntentSafe(emailIntent))
                    startActivity(emailIntent);
                else
                    Toast.makeText(getApplicationContext(), "Your phone has no app to send email!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isIntentSafe(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }
}