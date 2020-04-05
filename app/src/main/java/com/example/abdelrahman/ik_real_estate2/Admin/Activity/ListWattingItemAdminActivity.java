package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Admin.Adapter.AdapterItemAdmin;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ListWattingItemAdminActivity extends AppCompatActivity {
    AdapterItemAdmin adapter;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ImageView ic_back;
    ArrayList<Item> item;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    SearchView searchView;
    TextView textNoData;
    TextView textTitle;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_watting_item_admin);


        this.item = new ArrayList();
        this.adapter = new AdapterItemAdmin(this.item, this);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        this.recyclerView = ((RecyclerView) findViewById(R.id.recycleListWattingItemActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progreeListWattingItemActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backListWattingItemActivity));
        textTitle=findViewById(R.id.textTitleWattingListItemActivity);
        this.textNoData = ((TextView) findViewById(R.id.textNoDataListWattingItemActivity));
        this.searchView = ((SearchView) findViewById(R.id.rearchListWattingItemActivity));
//        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);

textTitle.setText("Watting List");
        this.recyclerView.setAdapter(this.adapter);
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ListWattingItemAdminActivity.this.onBackPressed();
            }
        });
    }

    protected void onResume() {
        getData();
        super.onResume();
    }


    private void getData() {
        this.databaseReference.orderByChild("state").equalTo("0").addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                ListWattingItemAdminActivity.this.item.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ListWattingItemAdminActivity.this.progressBar.setVisibility(View.INVISIBLE);
                    Item data = (Item) ((DataSnapshot) dataSnapshot1).getValue(Item.class);
                    ListWattingItemAdminActivity.this.item.add(data);
//                    Collections.reverse(item);
                }
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);

                ListWattingItemAdminActivity.this.adapter.notifyDataSetChanged();
                if (ListWattingItemAdminActivity.this.item.size() == 0) {
                    ListWattingItemAdminActivity.this.progressBar.setVisibility(View.INVISIBLE);
                    ListWattingItemAdminActivity.this.textNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}