package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Admin.Adapter.AdapterChangeItem;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListChangeItemAdminActivity extends AppCompatActivity {
    AdapterChangeItem adapter;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ImageView ic_back, ic_filter;
    ArrayList<Item> item;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    SearchView searchView;
    TextView textNoData, textTitle;
    LinearLayoutManager linearLayoutManager;
    int filterType = 1;
    RadioGroup radioGroup;
    Button saveFilter;
    //    EditText from_price_filter, to_price_filter;
//    LinearLayout layoutPriceFilter;
    RadioButton radioButtonTitle, radioButtonId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_watting_item_admin);

        this.item = new ArrayList<>();
        this.adapter = new AdapterChangeItem(this.item, this.item, this);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        this.recyclerView = ((RecyclerView) findViewById(R.id.recycleListWattingItemActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progreeListWattingItemActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backListWattingItemActivity));
        this.textNoData = ((TextView) findViewById(R.id.textNoDataListWattingItemActivity));
        this.searchView = ((SearchView) findViewById(R.id.rearchListWattingItemActivity));
        textTitle = findViewById(R.id.textTitleWattingListItemActivity);
        ic_filter = findViewById(R.id.ic_filterListChangeItemDetailsAdminActivity);

        ic_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(ListChangeItemAdminActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
                dialog.setContentView(R.layout.filter_item);
                radioGroup = dialog.findViewById(R.id.radioGroupFilterListItemActivity);
                saveFilter = dialog.findViewById(R.id.btnFilterItemListItemActivity);
                radioButtonTitle = dialog.findViewById(R.id.radioBtnTitleFilterItemListItemActivity);
                radioButtonId = dialog.findViewById(R.id.radioBtnIdFilterItemListItemActivity);
//                layoutPriceFilter = dialog.findViewById(R.id.layoutPricrFilterListItemActivity);
//                from_price_filter = dialog.findViewById(R.id.ETPriceFromListItemActivity);
//                to_price_filter = dialog.findViewById(R.id.ETPriceToListItemActivity);
                //((RadioButton) radioButtonTitle.findViewById(R.id.radioBtnTitleFilterItemListItemActivity)).setChecked(true);
                dialog.show();
//                from_price_filter.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        radioButtonPrice.setChecked(true);
//                    }
//                });
//                to_price_filter.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        radioButtonPrice.setChecked(true);
//                    }
//                });
                radioButtonId.setChecked(true);

                saveFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                        if (checkedRadioButtonId == R.id.radioBtnIdFilterItemListItemActivity) {
                            filterType = 1;
//                            layoutPriceFilter.setVisibility(View.GONE);
                        } else if (checkedRadioButtonId == R.id.radioBtnTitleFilterItemListItemActivity) {

                            filterType = 0;
//                            layoutPriceFilter.setVisibility(View.GONE);
//                        } else if (checkedRadioButtonId == R.id.radioBtnPriceFilterItemListItemActivity) {

//                            layoutPriceFilter.setVisibility(View.VISIBLE);

                        }
                        dialog.hide();

                    }
                });
            }
        });

        // this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // THIS ALSO SETS setStackFromBottom to true

        textTitle.setText("All Data");
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        getData();
        adapter.notifyDataSetChanged();
        getData();

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        this.recyclerView.setAdapter(this.adapter);
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ListChangeItemAdminActivity.this.startActivity(new Intent(ListChangeItemAdminActivity.this, MainActivityAdmin.class));

                ListChangeItemAdminActivity.this.finish();
            }
        });
    }

    protected void onResume() {
        getData();
        super.onResume();
    }

    private void getData() {
        this.databaseReference.orderByChild("state").equalTo("1").addListenerForSingleValueEvent(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                item.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ListChangeItemAdminActivity.this.progressBar.setVisibility(View.GONE);
                    Item items = (Item) dataSnapshot1.getValue(Item.class);
                    //System.out.println(item.toString());
                    ListChangeItemAdminActivity.this.item.add(items);

                }
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);

                for (Item i : item) {
                    //System.out.println(i.toString());
                }
                ListChangeItemAdminActivity.this.adapter.notifyDataSetChanged();
                if (ListChangeItemAdminActivity.this.item.size() == 0) {
                    ListChangeItemAdminActivity.this.progressBar.setVisibility(View.GONE);
                    ListChangeItemAdminActivity.this.textNoData.setVisibility(View.VISIBLE);
                }
            }
        });
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String paramAnonymousString) {
                ListChangeItemAdminActivity.this.adapter.getFilter(filterType).filter(paramAnonymousString);
                return false;
            }

            public boolean onQueryTextSubmit(String paramAnonymousString) {
                ListChangeItemAdminActivity.this.adapter.getFilter(filterType).filter(paramAnonymousString);
                return false;
            }
        });
    }
}
