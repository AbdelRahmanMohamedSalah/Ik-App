package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Moudel.Users;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyTeamAdminActivity extends AppCompatActivity {

    Spinner SpinnerMemberName;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayMemberName;
    ImageView changeType;
    DatabaseReference databaseReference;
    TextView textEmail;
    TextView textPhone;
    TextView textType;
    Users users;
    ImageView ic_back;
    ArrayList<Users> usersMap;
    RadioGroup radioGroupDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team_admin);
        this.SpinnerMemberName = ((Spinner) findViewById(R.id.spinnerTeamMyTeamActivity));
        this.textType = ((TextView) findViewById(R.id.TypeMyTeamActivity));
        this.textPhone = ((TextView) findViewById(R.id.PhoneMyTeamActivity));
        this.textEmail = ((TextView) findViewById(R.id.EmailMyTeamActivity));
        this.changeType = ((ImageView) findViewById(R.id.btnChaneMyTeamActivity));
        ic_back = findViewById(R.id.ic_backMyTeamAdminActivity);
        this.usersMap = new ArrayList<>();
        this.arrayMemberName = new ArrayList();
        this.users = new Users();

        textPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", textPhone.getText().toString(), null));
                startActivity(intent);

            }
        });


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        this.databaseReference.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot paramAnonymousDataSnapshot) {
                MyTeamAdminActivity.this.arrayMemberName.clear();
                MyTeamAdminActivity.this.usersMap.clear();
                Iterator localIterator = paramAnonymousDataSnapshot.getChildren().iterator();
                while (localIterator.hasNext()) {
                    DataSnapshot localDataSnapshot = (DataSnapshot) localIterator.next();
                    MyTeamAdminActivity.this.arrayMemberName.add(localDataSnapshot.child("name").getValue(String.class));
//                    users = localDataSnapshot.getValue(Users.class);
                    MyTeamAdminActivity.this.users = new Users(
                            localDataSnapshot.child("email").getValue(String.class),
                            localDataSnapshot.child("Fname").getValue(String.class),
                            localDataSnapshot.child("key").getValue(String.class),
                            localDataSnapshot.child("Lname").getValue(String.class),
                            localDataSnapshot.child("mainAdmin").getValue(String.class),
                            localDataSnapshot.child("name").getValue(String.class),
                            localDataSnapshot.child("phone").getValue(String.class),
                            localDataSnapshot.child("type").getValue(String.class)
                    );
                    System.out.println(users.getName());
                    MyTeamAdminActivity.this.usersMap.add(MyTeamAdminActivity.this.users);
                }
                MyTeamAdminActivity.this.adapter.notifyDataSetChanged();
            }
        });
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usersMap) {
            @Override
            public int getCount() {
                return usersMap.size();
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (!usersMap.isEmpty()) {
                    TextView textView = v.findViewById(android.R.id.text1);
                    textView.setText(usersMap.get(position).getName());
                }
                return v;
            }

        };
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.SpinnerMemberName.setAdapter(this.adapter);
        this.SpinnerMemberName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong) {


                MyTeamAdminActivity.this.textType.setText(MyTeamAdminActivity.this.usersMap.get(paramAnonymousInt).getType());
                MyTeamAdminActivity.this.textEmail.setText(MyTeamAdminActivity.this.usersMap.get(paramAnonymousInt).getEmail());
                MyTeamAdminActivity.this.textPhone.setText(MyTeamAdminActivity.this.usersMap.get(paramAnonymousInt).getPhone());

            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {
            }
        });
        changeType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MyTeamAdminActivity.this);
                dialog.setContentView(R.layout.change_type_my_team_admin_activity);
                TextView nameDialog;

                Button saveDialog;
                nameDialog = dialog.findViewById(R.id.nameChangeTypeMyTeamAdminActivity);
                radioGroupDialog = dialog.findViewById(R.id.RadioGroupChangeTypeMyTeamAdminActivity);
                saveDialog = dialog.findViewById(R.id.BtnSaveChangeTypeMyTeamAdminActivity);
                nameDialog.setText(usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getName());
                dialog.show();

                if (usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getType().equals("admin")) {
                    ((RadioButton) radioGroupDialog.getChildAt(0)).setChecked(true);

                } else {
                    ((RadioButton) radioGroupDialog.getChildAt(1)).setChecked(true);

                }
                saveDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int checkedRadioButtonId = radioGroupDialog.getCheckedRadioButtonId();

                        Map<String, Object> map = new HashMap<>();

                        if (checkedRadioButtonId == R.id.RadioMember) {
                            map.put("type", "member");
                        } else if (checkedRadioButtonId == R.id.RadioAdmin) {
                            map.put("type", "admin");
                        }
                        map.put("email", usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getEmail());
                        map.put("key", usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getKey());
                        map.put("phone", usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getPhone());
                        map.put("mainAdmin", usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getMainAdmin());
                        map.put("Lname", usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getLname());
                        System.out.println(" first name = " + usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getFname());
                        map.put("Fname", usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getFname());
                        map.put("name", usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getName());
                        databaseReference.child(usersMap.get(SpinnerMemberName.getSelectedItemPosition()).getKey()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dialog.hide();
                                    Toast.makeText(MyTeamAdminActivity.this, "saved", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MyTeamAdminActivity.this, MyTeamAdminActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(MyTeamAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

            }
        });
    }

    public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    }

    public void onNothingSelected(AdapterView<?> paramAdapterView) {
    }
}
