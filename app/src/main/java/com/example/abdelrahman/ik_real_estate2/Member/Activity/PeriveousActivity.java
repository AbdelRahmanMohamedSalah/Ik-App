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

import com.example.abdelrahman.ik_real_estate2.Member.Adapter.AdapterPerivous;
import com.example.abdelrahman.ik_real_estate2.Moudel.Perivous;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PeriveousActivity extends AppCompatActivity {

    FloatingActionButton BtnAdd;
    TextView NoData;
    AdapterPerivous adapterPerivous;
    ArrayList<Perivous> arraylist;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ImageView ic_back;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periveous);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.BtnAdd = ((FloatingActionButton) findViewById(R.id.BtnAddPreviousPreviousActivity));
        this.recyclerView = ((RecyclerView) findViewById(R.id.recyclePrevious));
        this.NoData = ((TextView) findViewById(R.id.textNoDataPerivousActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progresPerivousActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backPerivousActivity));
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                PeriveousActivity.this.onBackPressed();
            }
        });
        this.arraylist = new ArrayList();
        this.adapterPerivous = new AdapterPerivous(this.arraylist, this);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);

        this.recyclerView.setAdapter(this.adapterPerivous);
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Perivous_Respons");
        this.databaseReference.orderByChild("periviewMemberId").equalTo(this.firebaseAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                PeriveousActivity.this.progressBar.setVisibility(View.GONE);
                PeriveousActivity.this.NoData.setVisibility(View.GONE);
                PeriveousActivity.this.arraylist.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Perivous perivous = dataSnapshot1.getValue(Perivous.class);
                    arraylist.add(perivous);

                }
                if (PeriveousActivity.this.arraylist.size() == 0) {
                    PeriveousActivity.this.NoData.setVisibility(View.VISIBLE);
                    PeriveousActivity.this.progressBar.setVisibility(View.GONE);
                }
                PeriveousActivity.this.adapterPerivous.notifyDataSetChanged();

                recyclerView.scrollToPosition(adapterPerivous.getItemCount() - 1);
            }
        });
        this.BtnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                PeriveousActivity.this.startActivity(new Intent(PeriveousActivity.this, AddPreviousActivity.class));
                PeriveousActivity.this.finish();
            }
        });
    }
}
