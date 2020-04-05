package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AddActivity extends AppCompatActivity {
    TextView TBathRoom;
    TextView TLevel;
    TextView TOption;
    TextView TRoom;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceUsers;
    EditText etDetails;
    EditText etName;
    EditText etPhone;
    EditText etPrice;
    EditText etSpace;
    EditText etTitle;
    FirebaseAuth firebaseAuth;
    ImageView ic_back;
    ImageView ic_save;
    public String member_name;
    ProgressBar progressBar;
    Spinner spinnerBathRoom;
    Spinner spinnerBedRoom;
    Spinner spinnerLevel;
    Spinner spinnerLocation;
    Spinner spinnerOption;
    Spinner spinnerType;
    TextView textTitle;
    ArrayAdapter AdapterLevel, AdapterLocation, AdapterBedRoom, AdapterBathRoom, AdapterOption, AdapterType;
    ArrayList<String> ArrayLevel, ArrayLocation, ArrayBedRoom, ArrayBathRoom, ArrayOption, ArrayType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        this.firebaseAuth = FirebaseAuth.getInstance();
        this.spinnerLevel = ((Spinner) findViewById(R.id.spinnerLevelAddActivity));
        this.spinnerBathRoom = ((Spinner) findViewById(R.id.spinnerBathroomAddActivity));
        this.spinnerBedRoom = ((Spinner) findViewById(R.id.spinnerBedroomAddActivity));
        this.spinnerOption = ((Spinner) findViewById(R.id.spinnerOptionAddActivity));
        this.spinnerType = ((Spinner) findViewById(R.id.spinnerTypeAddActivity));
        this.spinnerLocation = ((Spinner) findViewById(R.id.spinnerLocationAddActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backAddActivity));
        this.ic_save = ((ImageView) findViewById(R.id.ic_saveAddActivity));
        this.etTitle = ((EditText) findViewById(R.id.ETtitleAddActivity));
        this.etPrice = ((EditText) findViewById(R.id.ETpriceAddActivity));
        this.etSpace = ((EditText) findViewById(R.id.ETspaceAddActivity));
        this.etDetails = ((EditText) findViewById(R.id.ETdetailsAddActivity));
        this.etName = ((EditText) findViewById(R.id.ETOwnerNameAddActivity));
        this.etPhone = ((EditText) findViewById(R.id.ETOwnerPhoneAddActivity));
        this.TLevel = ((TextView) findViewById(R.id.textLevelAddActivity));
        this.TBathRoom = ((TextView) findViewById(R.id.textBathRoomAddActivity));
        this.TRoom = ((TextView) findViewById(R.id.textRoomAddActivity));
        this.TOption = ((TextView) findViewById(R.id.textOptionAddActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progessBarAddActivity));
        this.textTitle = ((TextView) findViewById(R.id.textTitleAddActivity));

        this.firebaseAuth = FirebaseAuth.getInstance();

        this.textTitle.setText("Add Item");

        this.databaseReferenceUsers = FirebaseDatabase.getInstance().getReference("Users").child(this.firebaseAuth.getCurrentUser().getUid());
        this.databaseReferenceUsers.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                AddActivity.this.member_name = ((String) dataSnapshot.child("name").getValue(String.class));
            }

            public void onCancelled(DatabaseError databaseError) {
            }

        });

        ArrayType = new ArrayList<>();
        ArrayLevel = new ArrayList<>();
        ArrayBedRoom = new ArrayList<>();
        ArrayBathRoom = new ArrayList<>();
        ArrayLocation = new ArrayList<>();
        ArrayOption = new ArrayList<>();

        ArrayType.add("اختر");
        ArrayType.add("شقق");
        ArrayType.add("فلل");
        ArrayType.add("قصور");
        ArrayType.add("اراضي");
        ArrayType.add("محلات");
        ArrayType.add("عمارات");
        ArrayType.add("اخر");
        ArrayType.add("ايجارات");
        ArrayType.add("عمولات خارجيه");

        ArrayLevel.add("اختر");
        ArrayLevel.add("ارضي");
        ArrayLevel.add("ارضي مرتفع");
        ArrayLevel.add("1");
        ArrayLevel.add("2");
        ArrayLevel.add("3");
        ArrayLevel.add("4");
        ArrayLevel.add("5");
        ArrayLevel.add("6");
        ArrayLevel.add("7");
        ArrayLevel.add("8");
        ArrayLevel.add("9");
        ArrayLevel.add("10");
        ArrayLevel.add("11");
        ArrayLevel.add("12");
        ArrayLevel.add("الاخير");
        ArrayLevel.add("الروف");

        ArrayBedRoom.add("اختر");
        ArrayBedRoom.add("لا يوجد");
        ArrayBedRoom.add("1");
        ArrayBedRoom.add("2");
        ArrayBedRoom.add("3");
        ArrayBedRoom.add("4");
        ArrayBedRoom.add("5");
        ArrayBedRoom.add("6");
        ArrayBedRoom.add("7");
        ArrayBedRoom.add("8");
        ArrayBedRoom.add("9");
        ArrayBedRoom.add("10");

        ArrayBathRoom.add("اختر");
        ArrayBathRoom.add("لايوجد");
        ArrayBathRoom.add("1");
        ArrayBathRoom.add("2");
        ArrayBathRoom.add("3");
        ArrayBathRoom.add("4");
        ArrayBathRoom.add("5");
        ArrayBathRoom.add("6");
        ArrayBathRoom.add("7");
        ArrayBathRoom.add("8");
        ArrayBathRoom.add("9");
        ArrayBathRoom.add("10");

        ArrayOption.add("اختر");
        ArrayOption.add("لايوجد");
        ArrayOption.add("تحت الانشاء");
        ArrayOption.add("غير متشطبه");
        ArrayOption.add("نص تشطيب");
        ArrayOption.add("تشطيب جهاز");
        ArrayOption.add("تحتاج بعض التشطيب");
        ArrayOption.add("سوبر لوكس");
        ArrayOption.add("الترا سوبر لوكس");

        ArrayLocation.add("اختر");
        ArrayLocation.add("اكتوبر");
        ArrayLocation.add("الشيخ زايد");
        ArrayLocation.add("حدائق الاهرام");
        ArrayLocation.add("الفردوس");
        ArrayLocation.add("هرم سيتي");
        ArrayLocation.add("حي الاشجار");
        ArrayLocation.add("الحي المتميز");
        ArrayLocation.add("حي الياسمين");
        ArrayLocation.add("حدائق اكتوبر");
        ArrayLocation.add("واحه المهندسين");
        ArrayLocation.add("الخمائل");
        ArrayLocation.add("مكان اخر");


        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                AddActivity.this.onBackPressed();
            }
        });
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Data").push();

        this.ic_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Calendar localCalendar = Calendar.getInstance();
                if (!AddActivity.this.etTitle.getText().toString().isEmpty()) {
                    if (!AddActivity.this.etSpace.getText().toString().isEmpty()) {
                        if (!AddActivity.this.etPrice.getText().toString().isEmpty()) {
                            if (!AddActivity.this.etDetails.getText().toString().isEmpty()) {
                                if (!AddActivity.this.spinnerType.getSelectedItem().toString().equals("اختر")) {
                                    if (!AddActivity.this.spinnerLevel.getSelectedItem().toString().equals("اختر")) {
                                        if (!AddActivity.this.spinnerBathRoom.getSelectedItem().toString().equals("اختر")) {
                                            if (!AddActivity.this.spinnerBedRoom.getSelectedItem().toString().equals("اختر")) {
                                                if (!AddActivity.this.spinnerOption.getSelectedItem().toString().equals("اختر")) {
                                                    if (!AddActivity.this.spinnerLocation.getSelectedItem().toString().equals("اختر")) {
                                                        if (!AddActivity.this.etPhone.getText().toString().isEmpty()) {
                                                            if (!AddActivity.this.etName.getText().toString().isEmpty()) {
                                                                AddActivity.this.progressBar.setVisibility(View.VISIBLE);
                                                                AddActivity.this.ic_save.setEnabled(false);
                                                                HashMap localHashMap = new HashMap();
                                                                localHashMap.put("title", AddActivity.this.etTitle.getText().toString());
                                                                localHashMap.put("level", AddActivity.this.spinnerLevel.getSelectedItem().toString());
                                                                localHashMap.put("type", AddActivity.this.spinnerType.getSelectedItem().toString());
                                                                localHashMap.put("room", AddActivity.this.spinnerBedRoom.getSelectedItem().toString());
                                                                localHashMap.put("bathRoom", AddActivity.this.spinnerBathRoom.getSelectedItem().toString());
                                                                localHashMap.put("option", AddActivity.this.spinnerOption.getSelectedItem().toString());
                                                                localHashMap.put("location", AddActivity.this.spinnerLocation.getSelectedItem().toString());
                                                                localHashMap.put("price", AddActivity.this.etPrice.getText().toString());
                                                                localHashMap.put("details", AddActivity.this.etDetails.getText().toString());
                                                                localHashMap.put("space", AddActivity.this.etSpace.getText().toString());
                                                                localHashMap.put("owner_name", AddActivity.this.etName.getText().toString());
                                                                localHashMap.put("state", "0");
                                                                localHashMap.put("owner_phone", AddActivity.this.etPhone.getText().toString());
                                                                localHashMap.put("member_name", AddActivity.this.member_name);
                                                                StringBuilder localStringBuilder = new StringBuilder();
                                                                localStringBuilder.append(dateFormat.format(localCalendar.getTime()));
                                                                localStringBuilder.append("");
                                                                localHashMap.put("time", localStringBuilder.toString());
                                                                localHashMap.put("code", "0");
                                                                localHashMap.put("id", AddActivity.this.databaseReference.getKey());
                                                                localHashMap.put("member_id", AddActivity.this.firebaseAuth.getUid().toString());
                                                                AddActivity.this.databaseReference.setValue(localHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {

                                                                            Toast.makeText(AddActivity.this, "تم الحفظ", Toast.LENGTH_SHORT).show();
                                                                            AddActivity.this.onBackPressed();

                                                                        } else {
                                                                            Toast.makeText(AddActivity.this, "حدث خطا", Toast.LENGTH_SHORT).show();
                                                                            AddActivity.this.progressBar.setVisibility(View.INVISIBLE);
                                                                            AddActivity.this.ic_save.setEnabled(true);
                                                                        }
                                                                    }
                                                                });

                                                            } else {
                                                                AddActivity.this.etName.setError("برجاء ادخال اسم المالك");
                                                            }
                                                        } else {

                                                            AddActivity.this.etPhone.setError("برجاء ادخال رقم المحمول المالك");
                                                        }
                                                    } else {
                                                        Toast.makeText(AddActivity.this, "برجاء اختيار الموقع", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(AddActivity.this, "برجاء اختيار التشطيب", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(AddActivity.this, "برجاء اختيار عدد الغرف", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(AddActivity.this, "برجاء اختيار عدد الحمامات", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(AddActivity.this, "برجاء اختيار الطابق", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(AddActivity.this, "برجاء اختيار نوع العقار", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                AddActivity.this.etDetails.setError("Field is Empty");
                            }
                        } else {
                            AddActivity.this.etPrice.setError("Field is Empty");
                        }
                    } else {
                        AddActivity.this.etSpace.setError("Field is Empty");
                    }
                } else {
                    AddActivity.this.etTitle.setError("Field is Empty");
                }
            }
        });
        AdapterType = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, ArrayType);
        AdapterType.setDropDownViewResource(android.R.layout.simple_list_item_checked
        );
        this.spinnerType.setAdapter(AdapterType);
        this.spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                if (AddActivity.this.spinnerType.getSelectedItem().equals("اراضي")) {
                    AddActivity.this.spinnerOption.setVisibility(View.GONE);
                    AddActivity.this.spinnerBathRoom.setVisibility(View.GONE);
                    AddActivity.this.spinnerBedRoom.setVisibility(View.GONE);
                    AddActivity.this.spinnerLevel.setVisibility(View.GONE);
                    AddActivity.this.TBathRoom.setVisibility(View.GONE);
                    AddActivity.this.TLevel.setVisibility(View.GONE);
                    AddActivity.this.TRoom.setVisibility(View.GONE);
                    AddActivity.this.TOption.setVisibility(View.GONE);
                    AddActivity.this.spinnerOption.setSelection(1);
                    AddActivity.this.spinnerBathRoom.setSelection(1);
                    AddActivity.this.spinnerLevel.setSelection(1);
                    AddActivity.this.spinnerBedRoom.setSelection(1);

                } else {
                    AddActivity.this.spinnerOption.setVisibility(View.VISIBLE);
                    AddActivity.this.spinnerBathRoom.setVisibility(View.VISIBLE);
                    AddActivity.this.spinnerBedRoom.setVisibility(View.VISIBLE);
                    AddActivity.this.spinnerLevel.setVisibility(View.VISIBLE);
                    AddActivity.this.TBathRoom.setVisibility(View.VISIBLE);
                    AddActivity.this.TLevel.setVisibility(View.VISIBLE);
                    AddActivity.this.TRoom.setVisibility(View.VISIBLE);
                    AddActivity.this.TOption.setVisibility(View.VISIBLE);
                }
            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {
            }
        });
        AdapterLevel = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, ArrayLevel);
        AdapterLevel.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerLevel.setAdapter(AdapterLevel);

        AdapterBathRoom = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, ArrayBathRoom);
        AdapterBathRoom.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerBedRoom.setAdapter(AdapterBathRoom);

        AdapterBedRoom = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, ArrayBedRoom);
        AdapterBedRoom.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerBathRoom.setAdapter(AdapterBedRoom);

        AdapterOption = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, ArrayOption);
        AdapterOption.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerOption.setAdapter(AdapterOption);

        AdapterLocation = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, ArrayLocation);
        AdapterLocation.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerLocation.setAdapter(AdapterLocation);
    }

    public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    }

    public void onNothingSelected(AdapterView<?> paramAdapterView) {
    }
}
