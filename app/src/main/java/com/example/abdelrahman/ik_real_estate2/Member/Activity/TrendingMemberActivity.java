package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Admin.Adapter.TrendingAdapter;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrendingMemberActivity extends AppCompatActivity {

    ArrayList<Item> arrayList;
    RecyclerView recyclerView;
    ArrayList<Item> arrayListItem;
    ImageView ic_back;
    DatabaseReference databaseReference;
    TrendingAdapter trendingAdapter;
    FloatingActionButton addTrending;
    TextView NoData;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treanding_admin);
        arrayListItem = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleTrendingAdminActivity);
        ic_back = findViewById(R.id.ic_backTrendingAdminActivity);
        addTrending = findViewById(R.id.FabAddTrendingAdminActivity);
        NoData = findViewById(R.id.textNoDataTrendingActivity);
        progressBar = findViewById(R.id.progresTrendingActivity);
        NoData.setVisibility(View.GONE);

        addTrending.setVisibility(View.INVISIBLE);
        layoutManager = new LinearLayoutManager(this);
        progressBar.setVisibility(View.VISIBLE);
        trendingAdapter = new TrendingAdapter(this, arrayListItem, this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);


        recyclerView.setAdapter(trendingAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        databaseReference.child("Trending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListItem.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Item item = data.getValue(Item.class);
                    arrayListItem.add(item);
                    progressBar.setVisibility(View.GONE);
                    NoData.setVisibility(View.GONE);

                }
                trendingAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(trendingAdapter.getItemCount() - 1);


                if (arrayListItem.size() == 0) {

                    NoData.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
