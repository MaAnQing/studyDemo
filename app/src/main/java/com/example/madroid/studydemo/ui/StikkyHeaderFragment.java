package com.example.madroid.studydemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.madroid.studydemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StikkyHeaderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StikkyHeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StikkyHeaderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView mListView ;
    private ArrayAdapter<String> mAdapter ;
    private String[] mData ;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StikkyHeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StikkyHeaderFragment newInstance(String param1, String param2) {
        StikkyHeaderFragment fragment = new StikkyHeaderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StikkyHeaderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_stikky_header, container, false) ;
        initView(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
    }

    private void initView(View view){
        mListView = (ListView)view.findViewById(R.id.listView) ;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onButtonPressed(position);
//                switch (position){
//                    case 0 :
//                        startActivity(new Intent(getActivity(), RippleBackgroundActivity.class));
//                        break;
//                    case 1 :
//                        startActivity(new Intent(getActivity(), rippleActivity.class));
//                        break;
//                    case 2 :
//                        startActivity(new Intent(getActivity(), SurfaceViewActivity.class));
//                        break;
//                    case 3 :
//                        startActivity(new Intent(getActivity(), StikkyHeaderActivity.class));
//                        break;
//                    default:
//                        break;
//                }
            }
        });
        mData = getResources().getStringArray(R.array.stikky_header_title) ;
        mAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,mData) ;
        mListView.setAdapter(mAdapter);
    }

    private void loadFragment(Fragment fragment){
        getFragmentManager().beginTransaction().
                replace(R.id.layout_container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();
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
        public void onFragmentInteraction(int id);
    }

}
