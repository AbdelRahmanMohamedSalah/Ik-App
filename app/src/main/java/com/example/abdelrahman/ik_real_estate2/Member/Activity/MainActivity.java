package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView TVemail;
    TextView TVname;
    TextView TVtype;
    TextView appartment;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    TextView land;
    private long mBackPressed;
    TextView other;
    TextView shop;
    TextView rents;
    TextView ofices;
    TextView amara;
    TextView villa;
    TextView palace;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Configuration config = new Configuration();
        config.locale = new Locale("en","US");
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        super.onCreate(savedInstanceState);

//        getSupportActionBar().setWindowTitle("kk");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.appartment = ((TextView) findViewById(R.id.appartmentMainActivty));
        this.villa = ((TextView) findViewById(R.id.villaMainActivity));
        this.land = ((TextView) findViewById(R.id.landMainActivity));
        this.other = ((TextView) findViewById(R.id.otherMainActivity));
        this.shop = ((TextView) findViewById(R.id.shopMainActivity));
        palace = findViewById(R.id.palaceMainActivity);
        rents = findViewById(R.id.RentsMainActivty);
        ofices = findViewById(R.id.OficesMainActivty);
        amara = findViewById(R.id.AmaratMainActivty);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        intent = new Intent(this, ListItemActivity.class);
        getSupportActionBar().setTitle("Ik Real Estate");

        this.appartment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent = new Intent(MainActivity.this, AppartmentTypeActivity.class);
//                intent.putExtra("intent", "شقق");
//                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });
        this.villa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "فلل");
                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });
        this.shop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "محلات");
                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });
        this.other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "اخر");
                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });
        this.land.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "اراضي");
                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });
        this.amara.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "عمارات");
                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });
        this.palace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "قصور");
                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });
        this.rents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "ايجارات");
                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });
        this.ofices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "عمولات خارجيه");
                intent.putExtra("Activity", "MainActivity");
                MainActivity.this.startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));

            }
        });


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                String str1 = dataSnapshot.child("name").getValue(String.class);
                String str2 = dataSnapshot.child("email").getValue(String.class);
                String str3 = dataSnapshot.child("type").getValue(String.class);

                View v = navigationView.getHeaderView(0);

                TVname = ((TextView) v.findViewById(R.id.TextNameActivityMain));
                TVname.setText(str1);

                TVemail = ((TextView) v.findViewById(R.id.TextEmailActivityMain));
                TVemail.setText(str2);

                TVtype = ((TextView) v.findViewById(R.id.TextTypeActivityMain));
                TVtype.setText(str3);
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override

    public void onBackPressed() {
        if (this.mBackPressed + 2000L > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }
        Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
        this.mBackPressed = System.currentTimeMillis();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view Arrayitem clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(this, MyDataActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, RequestsActivity.class));
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this, PeriveousActivity.class));
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, RecyclePerivousMemberActivity.class));
        } else if (id == R.id.nav_trendeMember)
        {  startActivity(new Intent(this, TrendingMemberActivity.class));

        } else if (id == R.id.nav_share) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("هل انت متاكد من الخروج");
            alert.setCancelable(true);
            alert.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    MainActivity.this.firebaseAuth.signOut();
                    MainActivity.this.startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    MainActivity.this.finish();
                }
            });
            alert.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    paramAnonymousDialogInterface.cancel();
                }
            });
            alert.create().show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
