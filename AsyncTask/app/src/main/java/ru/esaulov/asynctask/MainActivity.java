package ru.esaulov.asynctask;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void doBlur(View view) {
        BlurImageTask task = new BlurImageTask();
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        task.execute(bitmap);
    }

    public void doInvert(View view) {
        InvertImageTask task = new InvertImageTask();
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        task.execute(bitmap);
    }

    private class InvertImageTask extends AsyncTask<Bitmap, Integer, Bitmap> {
        protected Bitmap doInBackground(Bitmap... bitmap) {
            Bitmap input = bitmap[0];
            Bitmap result = input.copy(input.getConfig(), true);
            int width = input.getWidth();
            int height = input.getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int pixel = input.getPixel(j, i);
                    int a = pixel & 0xff000000;
                    a = a | (~pixel & 0x00ffffff);
                    result.setPixel(j, i, a);
                }
                int progress = 100 * (i + 1) / height;
                publishProgress(progress);
            }
            return result;
        }

        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        protected void onPostExecute(Bitmap result) {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(result);
            progressBar.setProgress(0);
        }
    }

    private class BlurImageTask extends AsyncTask<Bitmap, Integer, Bitmap> {
        protected Bitmap doInBackground(Bitmap... bitmap) {
            Bitmap input = bitmap[0];
            Bitmap result = input.copy(input.getConfig(), true);
            int width = bitmap[0].getWidth();
            int height = bitmap[0].getHeight();
            int level = 7;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int pixel = bitmap[0].getPixel(j, i);
                    int a = pixel & 0xff000000;
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = pixel & 0xff;
                    r = (r + level) / 2;
                    g = (g + level) / 2;
                    b = (b + level) / 2;
                    int gray = a | (r << 16) | (g << 8) | b;
                    result.setPixel(j, i, gray);
                }
                int progress = 100 * (i + 1) / height;
                publishProgress(progress);
            }
            return result;
        }

        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        protected void onPostExecute(Bitmap result) {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(result);
            progressBar.setProgress(0);
        }
    }

}