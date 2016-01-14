package com.example.madroid.studydemo.recyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.madroid.studydemo.R;

import java.util.ArrayList;

public class RecyclerDemoActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ArrayList<String> mList;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);
        initData();
        setupView();
    }

    private void setupView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new MyLayoutManager());
        mRecycler.setAdapter(new MyAdapter());

        mButton = (Button) findViewById(R.id.scroll_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecycler.smoothScrollToPosition(0);
            }
        });

        findViewById(R.id.zoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoom();
            }
        });
    }

    private void zoom() {

    }

    private void initData() {
        mList = new ArrayList();

        for (int i = 0; i < 100; i++) {
            mList.add("item : " + i);
        }
    }


    class Holder extends RecyclerView.ViewHolder {

        private TextView mText;
        private View view ;

        public Holder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.text);
            view = findViewById(R.id.view) ;
         }

        public void setText(CharSequence text) {
            mText.setText(text);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<Holder> {

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getApplicationContext(), R.layout.item_recycler_demo, null);

            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

}
