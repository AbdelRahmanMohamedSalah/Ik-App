package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Member.Adapter.AdapterRequests;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.AddRequestActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Requests;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class RequestsListAdminActivity extends AppCompatActivity {
    FloatingActionButton BtnAdd;
    AdapterRequests adapterRequests;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    TextView NoData;
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
        this.recyclerView = ((RecyclerView) findViewById(R.id.recycleRequestActivity));
        this.requestsArray = new ArrayList();
        this.adapterRequests = new AdapterRequests(this.requestsArray, this, this);
        this.BtnAdd = ((FloatingActionButton) findViewById(R.id.BtnRequestActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progresRequestActivity));
        this.NoData = ((TextView) findViewById(R.id.textNoDataRequestActivity));
        this.progressBar.setVisibility(View.VISIBLE);
        this.NoData.setVisibility(View.GONE);
        adapterRequests.notifyDataSetChanged();
        this.databaseReference.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                RequestsListAdminActivity.this.requestsArray.clear();
             //   RequestsListAdminActivity.this.progressBar.setVisibility(View.GONE);
                RequestsListAdminActivity.this.NoData.setVisibility(View.GONE);
                requestsArray.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Requests requests = dataSnapshot1.getValue(Requests.class);
                    requestsArray.add(requests);
//                    Collections.reverse(requestsArray);
                }
                RequestsListAdminActivity.this.adapterRequests.notifyDataSetChanged();
                recyclerView.scrollToPosition(adapterRequests.getItemCount() - 1);

            }
        });
        if (this.requestsArray.size() == 0) {
            this.NoData.setVisibility(View.VISIBLE);
            this.progressBar.setVisibility(View.GONE);
        }
//        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);

        this.recyclerView.setAdapter(this.adapterRequests);
        this.BtnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                RequestsListAdminActivity.this.startActivity(new Intent(RequestsListAdminActivity.this, AddRequestActivity.class));
            }
        });
    }
}
