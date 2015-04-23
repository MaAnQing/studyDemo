package com.example.madroid.studydemo.ui;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.adapter.MyRecyclerAdapter;
import com.example.madroid.studydemo.listener.OnFragmentInteractionListener;
import com.example.madroid.studydemo.listener.RecyclerViewItemClickedListener;
import com.example.madroid.studydemo.listener.RecyclerViewItemLongClickedListener;
import com.example.madroid.studydemo.model.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecyclerCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecyclerCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerCardFragment extends Fragment implements RecyclerViewItemClickedListener,RecyclerViewItemLongClickedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mAdapter ;
    private List<RecyclerItem> dataSet ;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerCardFragment newInstance(String param1, String param2) {
        RecyclerCardFragment fragment = new RecyclerCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerCardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample_recycler_view,container,false) ;
        initView(view) ;
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initView(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.myRecycler) ;

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getActivity()) ;
        mRecyclerView.setLayoutManager(mLayoutmanager);

        //如果已知内容的变化不会改变RecyclerView的布局大小，使用这个来提高性能
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyRecyclerAdapter(getDadaSet(),R.layout.recycler_card_item) ;
        mAdapter.setOnItemClickedListener(this);
        mAdapter.setOnItemLongClickedListener(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private List<RecyclerItem> getDadaSet(){

        dataSet = new ArrayList<>() ;
        for (int i = 0 ; i <100 ; i++){
            RecyclerItem item = new RecyclerItem() ;
            item.setTitle("item : " + i);
            dataSet.add(item);
        }
        return dataSet ;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "onClicked : " + position, Toast.LENGTH_SHORT).show();
        Log.i("madroid", "onclick : " + position) ;
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(getActivity(),"onLongClicked : " + position,Toast.LENGTH_SHORT).show();
        Log.i("madroid", "onclick: " + position) ;

    }



}
