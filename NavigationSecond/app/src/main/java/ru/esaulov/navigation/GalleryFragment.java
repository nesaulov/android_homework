package ru.esaulov.navigation;

/**
 * Created by nesaulov on 22/01/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GalleryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView systemRecycler = (RecyclerView)inflater.inflate(
                R.layout.system_recycler, container, false);

        String[] systemNames = new String[System.systems.length];
        for (int i = 0; i < systemNames.length; i++) {
            systemNames[i] = System.systems[i].getName();
        }

        int[] mediaImages = new int[System.systems.length];
        for (int i = 0; i < mediaImages.length; i++) {
            mediaImages[i] = System.systems[i].getImageResourceId();
        }

        CardViewAdapter adapter = new CardViewAdapter(systemNames, mediaImages);
        systemRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        systemRecycler.setLayoutManager(layoutManager);
        return systemRecycler;
    }

}