package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Admin.Adapter.TrendingAdapter;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TreandingAdminActivity extends AppCompatActivity {

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
        progressBar.setVisibility(View.VISIBLE);
        trendingAdapter = new TrendingAdapter(this, arrayListItem, this);
        layoutManager=new LinearLayoutManager(this);
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
        addTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(TreandingAdminActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
                dialog.setContentView(R.layout.add_trending);
                final EditText etCode;
                final Button addTrende;
                final ProgressBar progressBar;
                etCode = dialog.findViewById(R.id.ETAddCodeTrendingActivity);
                addTrende = dialog.findViewById(R.id.BtnAddTrendingActivity);
                progressBar = dialog.findViewById(R.id.progressAddTrendingActivity);
                dialog.show();

                addTrende.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etCode.getText().toString().isEmpty()) {
                            etCode.setError("Field Empty");

                        } else {
                            addTrende.setEnabled(false);
                            progressBar.setVisibility(View.VISIBLE);
                            String codeUppercase = etCode.getText().toString().toUpperCase();
                            databaseReference.child("Data").orderByChild("code").equalTo(codeUppercase).addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    //for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                    if(dataSnapshot.exists()) {
                                        Item item = dataSnapshot.getChildren().iterator().next().getValue(Item.class);

                                        databaseReference.child("Trending").child(item.getId()).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    dialog.hide();
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(TreandingAdminActivity.this, "saved", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(TreandingAdminActivity.this, "error ", Toast.LENGTH_SHORT).show();
                                                    addTrende.setEnabled(true);
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        addTrende.setEnabled(true);
                                        Toast.makeText(TreandingAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                   // }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(TreandingAdminActivity.this, "Error2", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }

                    }
                });


            }
        });

    }
}
