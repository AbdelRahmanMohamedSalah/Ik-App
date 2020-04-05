package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ChangeItemDetailsAdminActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseReference databaseReference;
    EditText etDetails, etCode;
    EditText etName;
    EditText etPhone;
    EditText etPrice;
    EditText etSpace;
    EditText etTitle;
    FirebaseAuth firebaseAuth;
    ImageView ic_back;
    ImageView ic_save;
    Spinner spinnerBathRoom;
    Spinner spinnerBedRoom;
    Spinner spinnerLevel;
    Spinner spinnerLocation;
    Spinner spinnerOption;
    Spinner spinnerType;
    TextView textTitle;
    Item IntentItem;
    ArrayList<String> ArrayLevel, ArrayLocation, ArrayBedRoom, ArrayBathRoom, ArrayOption, ArrayType;
    TextView TLevel, TbedRoom, TbathRoom, Toption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

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
        this.etCode = findViewById(R.id.ETCodeAddActivity);
        this.etSpace = ((EditText) findViewById(R.id.ETspaceAddActivity));
        this.etDetails = ((EditText) findViewById(R.id.ETdetailsAddActivity));
        this.etName = ((EditText) findViewById(R.id.ETOwnerNameAddActivity));
        this.etPhone = ((EditText) findViewById(R.id.ETOwnerPhoneAddActivity));
        this.textTitle = ((TextView) findViewById(R.id.textTitleAddActivity));
        textTitle.setText("Edit Data");
        etCode.setVisibility(View.VISIBLE);
        TLevel = findViewById(R.id.textLevelAddActivity);
        TbedRoom = findViewById(R.id.textRoomAddActivity);
        TbathRoom = findViewById(R.id.textBathRoomAddActivity);
        Toption = findViewById(R.id.textOptionAddActivity);


        IntentItem = (Item) getIntent().getSerializableExtra("intent_Data");
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Data").child(IntentItem.getId());
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ChangeItemDetailsAdminActivity.this.onBackPressed();
            }
        });
        this.etCode.setText(IntentItem.getCode().toString());
        this.etTitle.setText(IntentItem.getTitle().toString());
        this.etSpace.setText(IntentItem.getSpace().toString());
        this.etPrice.setText(IntentItem.getPrice().toString());
        this.etDetails.setText(IntentItem.getDetails().toString());
        this.etPhone.setText(IntentItem.getOwner_phone().toString());
        this.etName.setText(IntentItem.getOwner_name().toString());
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
        ArrayBedRoom.add("لايوجد");
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
        ArrayBathRoom.add("لا يوجد");
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

        if (IntentItem.getType().equals("اراضي")) {
            TLevel.setVisibility(View.GONE);
            TbathRoom.setVisibility(View.GONE);
            TbedRoom.setVisibility(View.GONE);
            Toption.setVisibility(View.GONE);
            spinnerOption.setVisibility(View.GONE);
            spinnerBedRoom.setVisibility(View.GONE);
            spinnerBathRoom.setVisibility(View.GONE);
            spinnerLevel.setVisibility(View.GONE);

            spinnerLevel.setSelection(ArrayLevel.indexOf("ارضي"));
            spinnerBathRoom.setSelection(ArrayBathRoom.indexOf("لا يوجد"));
            spinnerBedRoom.setSelection(ArrayBedRoom.indexOf("لايوجد"));
            spinnerOption.setSelection(ArrayOption.indexOf("لايوجد"));


        } else {

            TLevel.setVisibility(View.VISIBLE);
            TbathRoom.setVisibility(View.VISIBLE);
            TbedRoom.setVisibility(View.VISIBLE);
            Toption.setVisibility(View.VISIBLE);
            spinnerOption.setVisibility(View.VISIBLE);
            spinnerBedRoom.setVisibility(View.VISIBLE);
            spinnerBathRoom.setVisibility(View.VISIBLE);
            spinnerLevel.setVisibility(View.VISIBLE);


        }
        this.ic_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Object localObject1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Object localObject2 = Calendar.getInstance();
                if (!ChangeItemDetailsAdminActivity.this.etCode.getText().toString().isEmpty()) {
                    if (!ChangeItemDetailsAdminActivity.this.etTitle.getText().toString().isEmpty()) {
                        if (!ChangeItemDetailsAdminActivity.this.etSpace.getText().toString().isEmpty()) {
                            if (!ChangeItemDetailsAdminActivity.this.etPrice.getText().toString().isEmpty()) {
                                if (!ChangeItemDetailsAdminActivity.this.etDetails.getText().toString().isEmpty()) {
                                    if (IntentItem.getType().equals("اراضى")) {
                                    } else {
                                    }
                                    if (!ChangeItemDetailsAdminActivity.this.spinnerType.getSelectedItem().toString().equals("اختر")) {
                                        if (!ChangeItemDetailsAdminActivity.this.spinnerLevel.getSelectedItem().toString().equals("اختر")) {
                                            if (!ChangeItemDetailsAdminActivity.this.spinnerBathRoom.getSelectedItem().toString().equals("اختر")) {
                                                if (!ChangeItemDetailsAdminActivity.this.spinnerBedRoom.getSelectedItem().toString().equals("اختر")) {
                                                    if (!ChangeItemDetailsAdminActivity.this.spinnerOption.getSelectedItem().toString().equals("اختر")) {
                                                        if (!ChangeItemDetailsAdminActivity.this.spinnerLocation.getSelectedItem().toString().equals("اختر")) {
                                                            if (!ChangeItemDetailsAdminActivity.this.etPhone.getText().toString().isEmpty()) {
                                                                if (!ChangeItemDetailsAdminActivity.this.etName.getText().toString().isEmpty()) {
                                                                    Map<String, Object> data = new HashMap<>();
                                                                    data.put("title", ChangeItemDetailsAdminActivity.this.etTitle.getText().toString());
                                                                    data.put("level", ChangeItemDetailsAdminActivity.this.spinnerLevel.getSelectedItem().toString());
                                                                    data.put("type", ChangeItemDetailsAdminActivity.this.spinnerType.getSelectedItem().toString());
                                                                    data.put("room", ChangeItemDetailsAdminActivity.this.spinnerBedRoom.getSelectedItem().toString());
                                                                    data.put("bathRoom", ChangeItemDetailsAdminActivity.this.spinnerBathRoom.getSelectedItem().toString());
                                                                    data.put("option", ChangeItemDetailsAdminActivity.this.spinnerOption.getSelectedItem().toString());
                                                                    data.put("location", ChangeItemDetailsAdminActivity.this.spinnerLocation.getSelectedItem().toString());
                                                                    data.put("price", ChangeItemDetailsAdminActivity.this.etPrice.getText().toString());
                                                                    data.put("details", ChangeItemDetailsAdminActivity.this.etDetails.getText().toString());
                                                                    data.put("space", ChangeItemDetailsAdminActivity.this.etSpace.getText().toString());
                                                                    data.put("owner_name", ChangeItemDetailsAdminActivity.this.etName.getText().toString());
                                                                    data.put("state", IntentItem.getState());
                                                                    data.put("owner_phone", ChangeItemDetailsAdminActivity.this.etPhone.getText().toString());
                                                                    StringBuilder localStringBuilder = new StringBuilder();
                                                                    localStringBuilder.append(((DateFormat) localObject1).format(((Calendar) localObject2).getTime()));
                                                                    localStringBuilder.append("");
                                                                    data.put("time", localStringBuilder.toString());
                                                                    data.put("code", ChangeItemDetailsAdminActivity.this.etCode.getText().toString());
                                                                    data.put("id", ChangeItemDetailsAdminActivity.this.databaseReference.getKey());
                                                                    data.put("member_id", IntentItem.getMember_id().toString());
                                                                    data.put("member_name", IntentItem.getMember_name().toString());
                                                                    databaseReference.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                            if (task.isSuccessful()) {

                                                                                Toast.makeText(ChangeItemDetailsAdminActivity.this, "saved", Toast.LENGTH_SHORT).show();
                                                                                onBackPressed();
                                                                            }
                                                                        }
                                                                    });
                                                                    return;
                                                                }
                                                                ChangeItemDetailsAdminActivity.this.etPhone.setError("برجاء ادخال رقم المحمول المالك");
                                                                return;
                                                            }
                                                            ChangeItemDetailsAdminActivity.this.etName.setError("برجاؤ ادخال اسم المالك");
                                                            return;
                                                        }
                                                        Toast.makeText(ChangeItemDetailsAdminActivity.this, "برجاء اختيار الموقع", 0).show();
                                                        return;
                                                    }
                                                    Toast.makeText(ChangeItemDetailsAdminActivity.this, "برجاء اختيار التشطيب", 0).show();
                                                    return;
                                                }
                                                Toast.makeText(ChangeItemDetailsAdminActivity.this, "برجاء اختيار عدد الغرف", 0).show();
                                                return;
                                            }
                                            Toast.makeText(ChangeItemDetailsAdminActivity.this, "برجاء اختيار عدد الحمامات", 0).show();
                                            return;
                                        }
                                        Toast.makeText(ChangeItemDetailsAdminActivity.this, "برجاء اختيار الطابق", 0).show();
                                        return;
                                    }
                                    Toast.makeText(ChangeItemDetailsAdminActivity.this, "برجاء اختيار نوع العقار", 0).show();
                                    return;
                                }
                                ChangeItemDetailsAdminActivity.this.etDetails.setError("Field is Empty");
                                return;
                            }
                            ChangeItemDetailsAdminActivity.this.etPrice.setError("Field is Empty");
                            return;
                        }
                        ChangeItemDetailsAdminActivity.this.etSpace.setError("Field is Empty");
                        return;
                    }
                    ChangeItemDetailsAdminActivity.this.etTitle.setError("Field is Empty");
                    return;
                }
                ChangeItemDetailsAdminActivity.this.etCode.setError("Field is Empty");
            }
        });
        ArrayAdapter localArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ArrayType);
        localArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerType.setAdapter(localArrayAdapter);
        this.spinnerType.setOnItemSelectedListener(this);
        localArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ArrayLevel);
        localArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerLevel.setAdapter(localArrayAdapter);
        localArrayAdapter = new

                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ArrayBedRoom);
        localArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerBedRoom.setAdapter(localArrayAdapter);
        localArrayAdapter = new

                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ArrayBathRoom);
        localArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerBathRoom.setAdapter(localArrayAdapter);
        localArrayAdapter = new

                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ArrayOption);
        localArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerOption.setAdapter(localArrayAdapter);
        localArrayAdapter = new

                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ArrayLocation);
        localArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerLocation.setAdapter(localArrayAdapter);

        this.spinnerLevel.setSelection(ArrayLevel.indexOf(IntentItem.getLevel()));
        this.spinnerType.setSelection(ArrayType.indexOf(IntentItem.getType()));
        this.spinnerBedRoom.setSelection(ArrayBedRoom.indexOf(IntentItem.getRoom()));
        this.spinnerBathRoom.setSelection(ArrayBathRoom.indexOf(IntentItem.getBathRoom()));
        this.spinnerOption.setSelection(ArrayOption.indexOf(IntentItem.getOption()));
        this.spinnerLocation.setSelection(ArrayLocation.indexOf(IntentItem.getLocation()));
    }

    public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt,
                               long paramLong) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
