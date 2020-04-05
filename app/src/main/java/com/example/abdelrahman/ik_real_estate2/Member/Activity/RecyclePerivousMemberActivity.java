package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Member.Adapter.AdapterRecycle;
import com.example.abdelrahman.ik_real_estate2.Moudel.Perivous;
import com.example.abdelrahman.ik_real_estate2.Moudel.Users;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclePerivousMemberActivity extends AppCompatActivity {
    AdapterRecycle adapterPerivousAdmin;
    ArrayList<Perivous> arrayList;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ImageView imageView;
    RecyclerView recyclerView;
    Map<String, Users> users;
    LinearLayoutManager layoutManager;
    TextView NoData;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_perivous_member);
        this.recyclerView = ((RecyclerView) findViewById(R.id.recycleRecyclePerivousMemberActivity));
        NoData = findViewById(R.id.textNoDataRecyclePerivousMemberActivity);
        progressBar = findViewById(R.id.progresRecyclePerivousMemberActivity);
        NoData.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        this.arrayList = new ArrayList();
        this.imageView = ((ImageView) findViewById(R.id.ic_backPerivousMemberActivity));
        this.imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                RecyclePerivousMemberActivity.this.onBackPressed();
            }
        });
        this.users = new HashMap();
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.adapterPerivousAdmin = new AdapterRecycle(this.arrayList, this.users, this);

        this.databaseReference.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                RecyclePerivousMemberActivity.this.arrayList.clear();
                RecyclePerivousMemberActivity.this.users.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("Perivous_recycle").getChildren()) {
                    Perivous localPerivous = dataSnapshot1.getValue(Perivous.class);
                    if (localPerivous.getPeriviewMemberId().equals(RecyclePerivousMemberActivity.this.firebaseAuth.getCurrentUser().getUid())) {
                        PrintStream localPrintStream = System.out;
                        StringBuilder localStringBuilder = new StringBuilder();
                        localStringBuilder.append("Equal ");
                        localStringBuilder.append(RecyclePerivousMemberActivity.this.firebaseAuth.getCurrentUser().getUid());
                        localPrintStream.println(localStringBuilder.toString());
                        RecyclePerivousMemberActivity.this.arrayList.add(localPerivous);
                    }
                }
                if (arrayList.size() == 0) {
                    NoData.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                }
                RecyclePerivousMemberActivity.this.adapterPerivousAdmin.notifyDataSetChanged();
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
                            dataSnapshot1.child("type").getValue(String.class));
                    users.put(user.getKey(), user);
                }
                RecyclePerivousMemberActivity.this.adapterPerivousAdmin.notifyDataSetChanged();
                recyclerView.scrollToPosition(adapterPerivousAdmin.getItemCount() - 1);
            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);

        this.recyclerView.setAdapter(this.adapterPerivousAdmin);
    }
}
