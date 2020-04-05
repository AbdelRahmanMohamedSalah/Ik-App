package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Member.Adapter.AdapterItems;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyDataActivity extends AppCompatActivity {
    TextView NoData;
    TextView TextDataSize;
    AdapterItems adapterItems;
    ArrayList<Item> arrayList;
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
        setContentView(R.layout.activity_my_data);


        this.recyclerView = ((RecyclerView) findViewById(R.id.recycleMyDataActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backMyDataActivity));
        this.TextDataSize = ((TextView) findViewById(R.id.textDataSizeMyDataActivity));
        this.NoData = ((TextView) findViewById(R.id.textNoDataMyDataActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progresMyDataActivity));
        this.arrayList = new ArrayList();
        this.adapterItems = new AdapterItems(this.arrayList, this.arrayList, this, this);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MyDataActivity.this.onBackPressed();
            }
        });
        this.databaseReference.orderByChild("member_id").equalTo(this.firebaseAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                MyDataActivity.this.progressBar.setVisibility(View.INVISIBLE);
                MyDataActivity.this.arrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Item item = dataSnapshot1.getValue(Item.class);
                    arrayList.add(item);
                    TextDataSize.setText(arrayList.size() + "");
                }


                if (MyDataActivity.this.arrayList.size() == 0) {
                    MyDataActivity.this.NoData.setVisibility(View.VISIBLE);
                    MyDataActivity.this.progressBar.setVisibility(View.GONE);
                }
                MyDataActivity.this.adapterItems.notifyDataSetChanged();

                recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);
            }
        });
        layoutManager=new LinearLayoutManager(this);
       recyclerView.setLayoutManager(layoutManager);
       layoutManager.setReverseLayout(true);
        this.recyclerView.setAdapter(this.adapterItems);
    }
}
