package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.content.Intent;
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

import com.example.abdelrahman.ik_real_estate2.Admin.Adapter.AdapterPerivousAdmin;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.AddPreviousActivity;
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

public class PerivousAdminActivity extends AppCompatActivity {


    TextView NoData;
    AdapterPerivousAdmin adapterPerivousAdmin;
    ArrayList<Perivous> arrayList;
    DatabaseReference databaseReference;
    FloatingActionButton floatingActionButton;
    ImageView ic_back;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    Map<String, Users> users;
    Users userObject;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perivous_admin);
        this.recyclerView = ((RecyclerView) findViewById(R.id.recyclePreviousAdminActivity));
        this.floatingActionButton = ((FloatingActionButton) findViewById(R.id.FabAddPerivousAdminActivity));
        this.NoData = ((TextView) findViewById(R.id.textNoDataPerivousAdminActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progresPerivousAdminActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backPerivousAdminActivity));
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                PerivousAdminActivity.this.onBackPressed();
            }
        });
        this.NoData.setVisibility(View.GONE);
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                PerivousAdminActivity.this.startActivity(new Intent(PerivousAdminActivity.this, AddPreviousActivity.class));
                PerivousAdminActivity.this.finish();
            }
        });
        this.arrayList = new ArrayList();
        this.users = new HashMap();
        this.adapterPerivousAdmin = new AdapterPerivousAdmin(this.arrayList, this.users, this);
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.databaseReference.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot paramAnonymousDataSnapshot) {
                PerivousAdminActivity.this.arrayList.clear();
                Object localObject = paramAnonymousDataSnapshot.child("Perivous_Request").getChildren().iterator();
                while (((Iterator) localObject).hasNext()) {
                    Perivous localPerivous = (Perivous) ((DataSnapshot) ((Iterator) localObject).next()).getValue(Perivous.class);
                    PerivousAdminActivity.this.arrayList.add(localPerivous);
                }
                if (PerivousAdminActivity.this.arrayList.size() == 0) {
                    PerivousAdminActivity.this.NoData.setVisibility(View.VISIBLE);
                    PerivousAdminActivity.this.progressBar.setVisibility(View.GONE);
                } else {
                    PerivousAdminActivity.this.progressBar.setVisibility(View.GONE);
                }
                databaseReference.child("Users").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        users.clear();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                            userObject = dataSnapshot1.getValue(Users.class);
                            userObject = new Users(
                                    dataSnapshot1.child("email").getValue(String.class),
                                    dataSnapshot1.child("Fname").getValue(String.class),
                                    dataSnapshot1.child("key").getValue(String.class),
                                    dataSnapshot1.child("Lname").getValue(String.class),
                                    dataSnapshot1.child("mainAdmin").getValue(String.class),
                                    dataSnapshot1.child("name").getValue(String.class),
                                    dataSnapshot1.child("phone").getValue(String.class),
                                    dataSnapshot1.child("type").getValue(String.class));
                            users.put(userObject.getKey(), userObject);
                        }
                        PerivousAdminActivity.this.adapterPerivousAdmin.notifyDataSetChanged();
                        recyclerView.scrollToPosition(adapterPerivousAdmin.getItemCount() - 1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);
        this.recyclerView.setAdapter(this.adapterPerivousAdmin);
    }
}

