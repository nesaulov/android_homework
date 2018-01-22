package ru.esaulov.sport;

/**
 * Created by nesaulov on 22/01/2018.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listSport = getListView();
        ArrayAdapter<ru.esaulov.sport.Sport> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ru.esaulov.sport.Sport.sports);
        listSport.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intent = new Intent(CategoryActivity.this, SportActivity.class);
        intent.putExtra(SportActivity.EXTRA_SPORTID, (int) id);
        startActivity(intent);
    }
}

