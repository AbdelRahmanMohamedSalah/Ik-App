package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Member.Activity.AddActivity;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.AppartmentTypeActivity;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.ListItemActivity;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.LoginActivity;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.MyDataActivity;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MainActivityAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final int TIME_INTERVAL = 2000;
    TextView TVemail;
    public String mainAdmin;

    TextView TVname;
    TextView TVtype;
    TextView appartment;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    TextView land;
    private long mBackPressed;
    TextView other;
    TextView shop;
    TextView villa;
    Intent intent;
    TextView rents;
    TextView palace;
    TextView ofices;
    TextView amara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Configuration config = new Configuration();
        config.locale = new Locale("en","US");
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);


        String locale = getResources().getConfiguration().locale.getDisplayName();
        System.out.println(locale);

        this.appartment = ((TextView) findViewById(R.id.appartmentMainActivtyAdmin));
        this.villa = ((TextView) findViewById(R.id.villaMainActivityAdmin));
        this.land = ((TextView) findViewById(R.id.landMainActivityAdmin));
        this.other = ((TextView) findViewById(R.id.otherMainActivityAdmin));
        this.shop = ((TextView) findViewById(R.id.shopMainActivityAdmin));
        palace=findViewById(R.id.palaceMainActivityAdmin);
        amara=findViewById(R.id.AmaratMainActivtyAdmin);
        rents=findViewById(R.id.RentsMainActivtyAdmin);
        ofices=findViewById(R.id.OficesMainActivtyAdmin);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(this.firebaseAuth.getCurrentUser().getUid());
        intent = new Intent(this, ListItemActivity.class);
        this.appartment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent=new Intent(MainActivityAdmin.this,AppartmentTypeActivity.class);
                intent.putExtra("intent", "شقق");
               // intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });
        this.villa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "فلل");
                intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });
        this.palace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "قصور");
                intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });
        this.shop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "محلات");
                intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });
        this.other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "اخر");
                intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });
        this.land.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "اراضي");
                intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });
        this.amara.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "عمارات");
                intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });
        this.rents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "ايجارات");
                intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });
        this.ofices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent.putExtra("intent", "عمولات خارجيه");
                intent.putExtra("Activity", "MainActivityAdmin");
                MainActivityAdmin.this.startActivity(intent);
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(MainActivityAdmin.this,AddActivity.class));
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(this.firebaseAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {


            public void onDataChange(DataSnapshot dataSnapshot) {
                String str1 = (String) dataSnapshot.child("name").getValue(String.class);
                String str2 = (String) dataSnapshot.child("email").getValue(String.class);
                String str3 = (String) dataSnapshot.child("type").getValue(String.class);
                mainAdmin = dataSnapshot.child("mainAdmin").getValue(String.class);

                View v = navigationView.getHeaderView(0);
                TVname = ((TextView) v.findViewById(R.id.TvNameAdminMainActivity));
                TVemail = ((TextView) v.findViewById(R.id.TvEmailAdminMainActivity));
                TVtype = ((TextView) v.findViewById(R.id.TvTypeAdminMainActivity));

                TVname.setText(str1);
                TVemail.setText(str2);
                TVtype.setText(str3);
            }

            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_DataAdmin) {
            startActivity(new Intent(this, MyDataActivity.class));

        } else if (id == R.id.nav_wattingListAdmin) {
            startActivity(new Intent(this, ListWattingItemAdminActivity.class));

        } else if (id == R.id.nav_EditDataAdmin) {
            startActivity(new Intent(this, ListChangeItemAdminActivity.class));
        } else if (id == R.id.nav_previousRequestAdmin) {
            startActivity(new Intent(this, PerivousAdminActivity.class));
        } else if (id == R.id.nav_RequestAdmin) {
            startActivity(new Intent(this, RequestsListAdminActivity.class));
        } else if (id == R.id.nav_MyTeamAdmin)
            if (this.mainAdmin.equals("1")) {
                startActivity(new Intent(this, MyTeamAdminActivity.class));
            } else {
                Toast.makeText(this, "You Dont Have Right ", Toast.LENGTH_SHORT).show();

            }
        else if (id == R.id.nav_RecycleAdmin) {
            startActivity(new Intent(this, PerivousRecycleAdminActivity.class));
        } else if (id == R.id.nav_LogOutAdmin) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("هل انت متاكد من الخروج");
            alert.setCancelable(true);
            alert.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(MainActivityAdmin.this, LoginActivity.class));
                    finish();
                }
            });
            alert.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    paramAnonymousDialogInterface.cancel();
                }
            });
            alert.create().show();
        }else if(id==R.id.nav_TrendeAdmin){
            startActivity(new Intent(MainActivityAdmin.this,TreandingAdminActivity.class));

        }


    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
}
}
