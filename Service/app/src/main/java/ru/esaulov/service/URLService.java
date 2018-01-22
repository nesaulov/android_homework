package ru.esaulov.service;

/**
 * Created by nesaulov on 23/01/2018.
 */

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

public class URLService extends IntentService {

    public URLService() {
        super("URLService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urls = intent.getStringExtra("urls");
        if (urls == null) {
            Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(urls);
        int tokenCount = tokenizer.countTokens();
        int index = 0;
        String[] targets = new String[tokenCount];
        while (tokenizer.hasMoreTokens()) {
            targets[index++] = tokenizer.nextToken();
        }
        File saveDir = getFilesDir();
        getPagesAndSave(saveDir, targets);
    }

    private void getPagesAndSave(File saveDir, String[] targets) {
        for (String target : targets) {
            Log.d("target: ", target);
            URL url = null;
            try {
                url = new URL(target);
                Log.d("url: ", url.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String fileName = target.replaceAll("/", "-").replaceAll(":", "-");
            File file = new File(saveDir, fileName);
            PrintWriter writer = null;
            BufferedReader reader = null;
            try {
                writer = new PrintWriter(file);
                assert url != null;
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }

            } catch (Exception e) {
                Toast.makeText(this, "fail " + e, Toast.LENGTH_LONG).show();
                Log.d("LOG", e.toString());

            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        Log.d("LOG", e.toString());
                        Toast.makeText(this, "fail " + e, Toast.LENGTH_LONG).show();

                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        Log.d("LOG", e.toString());
                        Toast.makeText(this, "fail " + e, Toast.LENGTH_LONG).show();

                    }
                }
            }
        }
    }
}