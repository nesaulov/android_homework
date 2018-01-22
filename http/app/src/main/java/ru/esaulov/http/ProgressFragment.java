package ru.esaulov.http;

/**
 * Created by nesaulov on 23/01/2018.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ProgressFragment extends Fragment {

    TextView _contentView;
    String _contentText = null;

    public ProgressFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        _contentView = view.findViewById(R.id.content);

        if (_contentText != null) {
            _contentView.setText(_contentText);
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (_contentText == null) {
            new ProgressTask().execute();
        }
    }


    class ProgressTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... path) {
            String content;

            try {
                content = getContent("https://vk.com");
            } catch (IOException e) {
                content = e.getMessage();
            }

            return content;
        }

        @Override
        protected void onProgressUpdate(Void... items) {

        }

        @Override
        protected void onPostExecute(String content) {
            _contentText = content;
            _contentView.setText(_contentText);

            Toast.makeText(getActivity(), "Data loaded", Toast.LENGTH_SHORT).show();
        }

        private String getContent(String path) throws IOException {
            BufferedReader reader = null;

            try {
                URL url = new URL(path);

                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000);
                connection.connect();

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer buffer = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                return buffer.toString();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }
}