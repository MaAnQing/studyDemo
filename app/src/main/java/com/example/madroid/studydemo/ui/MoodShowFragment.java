package com.example.madroid.studydemo.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.adapter.MoodAdapter;
import com.example.madroid.studydemo.model.MoodItem;
import com.example.madroid.studydemo.viewholder.MoodViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoodShowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoodShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoodShowFragment extends Fragment implements MoodViewHolder.onItemClickedListener,MoodViewHolder.onItemLongClickedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecycler ;
    private MoodAdapter mAdapter ;
    private List<MoodItem> mDataSet ;
    private Context mContext ;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoodShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoodShowFragment newInstance(String param1, String param2) {
        MoodShowFragment fragment = new MoodShowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MoodShowFragment() {
        // Required empty public constructor
        mContext = getActivity() ;
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
        View view = inflater.inflate(R.layout.fragment_mood_show, container, false);
        initView(view) ;
        return view ;
    }

    private void initView(View view){
        mRecycler = (RecyclerView)view.findViewById(R.id.myRecycler) ;
        mAdapter = new MoodAdapter(getDataSet()) ;
        mAdapter.setItemClickedListener(this);
        mAdapter.SetItemLongClickedListener(this);
        mRecycler.setAdapter(mAdapter);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext) ;
        mRecycler.setLayoutManager(manager);


    }

    private List<MoodItem> getDataSet(){
        mDataSet = new ArrayList<MoodItem>() ;
        for (int i=0 ;i < 100 ; i++){
            MoodItem item = new MoodItem("name " + i,"date " + i,"duration " +i,R.drawable.ic_launcher,"address " +i) ;
            mDataSet.add(item);
        }

        return mDataSet ;
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

    //recycler item click
    @Override
    public void onClick(View view, int position) {
        Toast.makeText(getActivity(),"position:" + position,Toast.LENGTH_SHORT).show();
        Log.i("madroid","position: " + position) ;
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
