package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.app.Dialog;
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
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Member.Adapter.AdapterItems;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListItemActivity extends AppCompatActivity {
    AdapterItems adapterItems;
    DatabaseReference databaseReference;
    ImageView ic_back, ic_filter;
    ArrayList<Item> Arrayitem;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    SearchView searchView;
    TextView textNoData;
    Item item;
    TextView title;
    String intentType;
    LinearLayoutManager layoutManager;
    public int filterType;
    RadioGroup radioGroup;
    Button saveFilter;
    //    EditText from_price_filter, to_price_filter;
//    LinearLayout layoutPriceFilter;
    RadioButton radioButtonTitle, radioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        Arrayitem = new ArrayList<>();
        adapterItems = new AdapterItems(Arrayitem, Arrayitem, this, this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Data");

        this.recyclerView = ((RecyclerView) findViewById(R.id.recycleListItemActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progreeListItemActivity));
        this.title = ((TextView) findViewById(R.id.textTitleListItemActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backListItemActivity));
        this.textNoData = ((TextView) findViewById(R.id.textNoDataListItemActivity));
        this.searchView = ((SearchView) findViewById(R.id.rearchListItemActivity));
        ic_filter = findViewById(R.id.ic_filterListItemActivity);

        ic_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(ListItemActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
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


        this.ic_back.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View paramAnonymousView) {
                ListItemActivity.this.onBackPressed();
            }
        });
        try

        {
            String intent = getIntent().getStringExtra("intent");
            if (intent.equals("شقق")) {
                this.title.setText("شقق");
                intentType = getIntent().getStringExtra("Type");

                this.databaseReference.orderByChild("type").equalTo("شقق").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            if (dataSnapshot1.child("state").getValue().equals("1")) {

                                if (intentType.equals("L")) {
                                    title.setText("شقق ترفيهي");
                                    if (dataSnapshot1.child("code").getValue().toString().startsWith("L")) {
                                        ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                        item = dataSnapshot1.getValue(Item.class);
                                        Arrayitem.add(item);
//                                        Collections.reverse(Arrayitem);
                                    }

                                } else if (intentType.equals("N")) {
                                    title.setText("شقق نص تشطيب");
                                    if (dataSnapshot1.child("code").getValue().toString().startsWith("N")) {
                                        ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                        item = dataSnapshot1.getValue(Item.class);
                                        Arrayitem.add(item);
//                                        Collections.reverse(Arrayitem);
                                    }
                                } else if (intentType.equals("S")) {
                                    title.setText("شقق اجتماعى");
                                    if (dataSnapshot1.child("code").getValue().toString().startsWith("S")) {
                                        ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                        item = dataSnapshot1.getValue(Item.class);
                                        Arrayitem.add(item);
//                                        Collections.reverse(Arrayitem);
                                    }
                                }
                                adapterItems.notifyDataSetChanged();
                                recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);


                            }
                        }

                        if (Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }
                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            } else if (intent.equals("اراضي"))

            {
                this.title.setText("اراضي");
                this.databaseReference.orderByChild("type").equalTo("اراضي").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("state").getValue().equals("1")) {
                                ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                item = dataSnapshot1.getValue(Item.class);
                                Arrayitem.add(item);
//                                Collections.reverse(Arrayitem);

                            }


                            adapterItems.notifyDataSetChanged();
                            recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);

                        }

                        if (ListItemActivity.this.Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }

                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            } else if (intent.equals("فلل"))

            {
                this.title.setText("فلل");
                this.databaseReference.orderByChild("type").equalTo("فلل").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("state").getValue().equals("1")) {

                                ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                item = dataSnapshot1.getValue(Item.class);
                                Arrayitem.add(item);
//                                Collections.reverse(Arrayitem);
                            }
                            adapterItems.notifyDataSetChanged();
                            recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);

                        }

                        if (ListItemActivity.this.Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }

                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            } else if (intent.equals("قصور"))

            {
                this.title.setText("قصور");
                this.databaseReference.orderByChild("type").equalTo("قصور").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("state").getValue().equals("1")) {

                                ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                item = dataSnapshot1.getValue(Item.class);
                                Arrayitem.add(item);
//                                Collections.reverse(Arrayitem);
                            }
                            adapterItems.notifyDataSetChanged();
                            recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);

                        }

                        if (ListItemActivity.this.Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }

                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            } else if (intent.equals("ايجارات"))

            {
                this.title.setText("ايجارات");
                this.databaseReference.orderByChild("type").equalTo("ايجارات").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("state").getValue().equals("1")) {

                                ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                item = dataSnapshot1.getValue(Item.class);
                                Arrayitem.add(item);
//                                Collections.reverse(Arrayitem);
                            }
                            adapterItems.notifyDataSetChanged();
                            recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);

                        }

                        if (ListItemActivity.this.Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }

                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            } else if (intent.equals("عمارات"))

            {
                this.title.setText("عمارات");
                this.databaseReference.orderByChild("type").equalTo("عمارات").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("state").getValue().equals("1")) {

                                ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                item = dataSnapshot1.getValue(Item.class);
                                Arrayitem.add(item);
//                                Collections.reverse(Arrayitem);
                            }
                            adapterItems.notifyDataSetChanged();
                            recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);

                        }

                        if (ListItemActivity.this.Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }

                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            } else if (intent.equals("عمولات خارجيه"))

            {
                this.title.setText("عمولات خارجيه");
                this.databaseReference.orderByChild("type").equalTo("عمولات خارجيه").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("state").getValue().equals("1")) {

                                ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                item = dataSnapshot1.getValue(Item.class);
                                Arrayitem.add(item);
//                                Collections.reverse(Arrayitem);
                            }
                            adapterItems.notifyDataSetChanged();
                            recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);

                        }

                        if (ListItemActivity.this.Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }

                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            } else if (intent.equals("محلات"))

            {
                this.title.setText("محلات");
                this.databaseReference.orderByChild("type").equalTo("محلات").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("state").getValue().equals("1")) {

                                ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                item = dataSnapshot1.getValue(Item.class);
                                Arrayitem.add(item);
//                                Collections.reverse(Arrayitem);
                            }
                            adapterItems.notifyDataSetChanged();
                            recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);

                        }

                        if (ListItemActivity.this.Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }

                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            } else if (intent.equals("اخر"))

            {
                this.title.setText("اخر");
                this.databaseReference.orderByChild("type").equalTo("اخر").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("state").getValue().equals("1")) {

                                ListItemActivity.this.progressBar.setVisibility(View.GONE);
                                item = dataSnapshot1.getValue(Item.class);
                                Arrayitem.add(item);
//                                Collections.reverse(Arrayitem);
                            }
                            adapterItems.notifyDataSetChanged();
                            recyclerView.scrollToPosition(adapterItems.getItemCount() - 1);

                        }

                        if (ListItemActivity.this.Arrayitem.size() == 0) {
                            ListItemActivity.this.progressBar.setVisibility(View.GONE);
                            ListItemActivity.this.textNoData.setVisibility(View.VISIBLE);
                        }

                    }

                    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
                    }

                });
            }
            this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()

            {
                public boolean onQueryTextChange(String paramAnonymousString) {
                    ListItemActivity.this.adapterItems.getFilter(filterType).filter(paramAnonymousString);
                    return false;
                }

                public boolean onQueryTextSubmit(String paramAnonymousString) {
                    ListItemActivity.this.adapterItems.getFilter(filterType).filter(paramAnonymousString);
                    return false;
                }
            });
//            this.recyclerView.setLayoutManager(new

//                    LinearLayoutManager(this));
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setReverseLayout(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapterItems);

        } catch (
                Exception paramBundle)

        {


            Toast.makeText(this, paramBundle.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
