package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Member.Adapter.AdapterRequests;
import com.example.abdelrahman.ik_real_estate2.Moudel.Requests;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestsActivity extends AppCompatActivity {
    FloatingActionButton BtnAdd;
    TextView NoData;
    AdapterRequests adapterRequests;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ImageView ic_back;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ArrayList<Requests> requestsArray;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Requests");
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.ic_back = ((ImageView) findViewById(R.id.ic_backRequestActivity));
        this.recyclerView = ((RecyclerView) findViewById(R.id.recycleRequestActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progresRequestActivity));
        this.NoData = ((TextView) findViewById(R.id.textNoDataRequestActivity));
        this.requestsArray = new ArrayList();
        this.adapterRequests = new AdapterRequests(this.requestsArray, this, this);
        this.BtnAdd = ((FloatingActionButton) findViewById(R.id.BtnRequestActivity));
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                RequestsActivity.this.onBackPressed();
            }
        });
        this.databaseReference.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                RequestsActivity.this.requestsArray.clear();
                RequestsActivity.this.NoData.setVisibility(View.GONE);
                RequestsActivity.this.progressBar.setVisibility(View.GONE);
                requestsArray.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Requests requests = dataSnapshot1.getValue(Requests.class);
                    requestsArray.add(requests);
//                    Collections.reverse(requestsArray);
                }
                if (RequestsActivity.this.requestsArray.size() == 0) {
                    RequestsActivity.this.NoData.setVisibility(View.VISIBLE);
                    RequestsActivity.this.progressBar.setVisibility(View.GONE);
                }
                RequestsActivity.this.adapterRequests.notifyDataSetChanged();
                recyclerView.scrollToPosition(adapterRequests.getItemCount() - 1);

            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);


//        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adapterRequests);
        this.BtnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                RequestsActivity.this.startActivity(new Intent(RequestsActivity.this, AddRequestActivity.class));
            }
        });
    }
}
