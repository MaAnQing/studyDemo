package com.example.madroid.studydemo.ui.tikkyheader;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.view.stikkyheader.IO2014HeaderAnimator;
import com.example.madroid.studydemo.view.stikkyheader.StikkyHeaderBuilder;


public class IO2014HeaderFragment extends Fragment {

    private ListView mListView;

    public IO2014HeaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_io2014, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = (ListView) getView().findViewById(R.id.listview);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        IO2014HeaderAnimator animator = new IO2014HeaderAnimator(getActivity());

        StikkyHeaderBuilder.stickTo(mListView)
                .setHeader(R.id.header, (ViewGroup) getView())
                .minHeightHeaderDim(R.dimen.min_height_header_materiallike)
                .animator(animator)
                .build();

        populateListView();
    }

    private void populateListView() {

        String[] elements = new String[500];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = "row " + i;
        }

        mListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, elements));
    }

}
