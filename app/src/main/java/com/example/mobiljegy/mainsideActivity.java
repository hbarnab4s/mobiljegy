package com.example.mobiljegy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class mainsideActivity extends AppCompatActivity {
    private static final String LOG_TAG = mainsideActivity.class.getName();
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;
    private ArrayList<TicketItem> mItemList;
    private TicketItemAdapter mAdapter;

    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainside);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            Log.d(LOG_TAG, "Authenticated user");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user");
            finish();
        }


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mItemList = new ArrayList<>();

        mAdapter = new TicketItemAdapter(this, mItemList);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();

    }
    private void initializeData() {
        String[] itemList = getResources().getStringArray(R.array.ticket_item_names);
        String[] itemPrice = getResources().getStringArray(R.array.ticket_item_price);

        TypedArray itemsImageResource = getResources().obtainTypedArray(R.array.ticket_item_images);

        mItemList.clear();

        for(int i = 0; i < itemList.length; i++) {
            mItemList.add(new TicketItem(itemList[i], itemPrice[i], itemsImageResource.getResourceId(i, 0)));
        }

        itemsImageResource.recycle();

        mAdapter.notifyDataSetChanged();
    }
}