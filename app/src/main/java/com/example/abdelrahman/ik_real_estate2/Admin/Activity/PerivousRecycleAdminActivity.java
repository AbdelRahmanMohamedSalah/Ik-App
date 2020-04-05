package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Admin.Adapter.AdapterRecyclePerivous;
import com.example.abdelrahman.ik_real_estate2.Moudel.Perivous;
import com.example.abdelrahman.ik_real_estate2.Moudel.Users;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PerivousRecycleAdminActivity extends AppCompatActivity {

    AdapterRecyclePerivous adapterPerivousAdmin;
    ArrayList<Perivous> arrayList;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    Map<String, Users> users;
    ImageView ic_back;
    TextView NoData;
    ProgressBar progressBar;

    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_perivous_recycle_admin);
        ic_back = findViewById(R.id.ic_backPerivousRecycleAdminActivity);
        NoData = findViewById(R.id.textNoDataPerivousRecycleAdminActivity);
        progressBar = findViewById(R.id.progresPerivousRecycleAdminActivity);

        this.recyclerView = ((RecyclerView) findViewById(R.id.recyclePerivousRecycleAdminActivity));
        this.arrayList = new ArrayList();
        this.users = new HashMap();
        progressBar.setVisibility(View.VISIBLE);
        NoData.setVisibility(View.GONE);
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.adapterPerivousAdmin = new AdapterRecyclePerivous(this.arrayList, this.users, this);
        this.databaseReference.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);

                PerivousRecycleAdminActivity.this.arrayList.clear();
                PerivousRecycleAdminActivity.this.users.clear();
                Object localObject = dataSnapshot.child("Perivous_recycle").getChildren().iterator();
                while (((Iterator) localObject).hasNext()) {
                    Perivous localPerivous = (Perivous) ((DataSnapshot) ((Iterator) localObject).next()).getValue(Perivous.class);
                    PerivousRecycleAdminActivity.this.arrayList.add(localPerivous);
                }
                PerivousRecycleAdminActivity.this.adapterPerivousAdmin.notifyDataSetChanged();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("Users").getChildren()) {
//                    Users user = dataSnapshot1.getValue(Users.class);
                    Users user = new Users(
                            dataSnapshot1.child("email").getValue(String.class),
                            dataSnapshot1.child("Fname").getValue(String.class),
                            dataSnapshot1.child("key").getValue(String.class),
                            dataSnapshot1.child("Lname").getValue(String.class),
                            dataSnapshot1.child("mainAdmin").getValue(String.class),
                            dataSnapshot1.child("name").getValue(String.class),
                            dataSnapshot1.child("phone").getValue(String.class),
                            dataSnapshot1.child("type").getValue(String.class)
                    );

                    users.put(user.getKey(), user);

                }
                if (arrayList.size() == 0) {

                    progressBar.setVisibility(View.GONE);
                    NoData.setVisibility(View.VISIBLE);
                }
                PerivousRecycleAdminActivity.this.adapterPerivousAdmin.notifyDataSetChanged();
                recyclerView.scrollToPosition(adapterPerivousAdmin.getItemCount() - 1);


            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);

        this.recyclerView.setAdapter(this.adapterPerivousAdmin);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}

