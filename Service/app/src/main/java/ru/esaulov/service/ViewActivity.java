package ru.esaulov.service;

/**
 * Created by nesaulov on 23/01/2018.
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        File saveDir = getFilesDir();

        if (saveDir.exists()) {
            File dir = new File(saveDir, "URLService");

            dir = saveDir;

            if (dir.exists()) {

                String[] files = dir.list();

                ArrayAdapter<String> dataAdapter =

                        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, files);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(dataAdapter);

                spinner.setOnItemSelectedListener(

                        new AdapterView.OnItemSelectedListener() {

                            @Override

                            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {

                                //open file

                                Object itemAtPosition = adapterView.getItemAtPosition(pos);

                                File file = new File(getFilesDir(), itemAtPosition.toString());

                                FileReader fileReader = null;

                                BufferedReader bufferedReader = null;

                                try {

                                    fileReader = new FileReader(file);

                                    bufferedReader = new BufferedReader(fileReader);

                                    StringBuilder sb = new StringBuilder();

                                    String line = bufferedReader.readLine();

                                    while (line != null) {

                                        sb.append(line);

                                        line = bufferedReader.readLine();

                                    }

                                    WebView webView = findViewById(R.id.webview);

                                    webView.loadData(sb.toString(), "text/html", "utf-8");
                                    Log.d("LOG_DATA", sb.toString());

                                } catch (FileNotFoundException e) {

                                } catch (IOException e) {
                                }

                            }

                            @Override

                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }

                        });
            }
        }
    }
}