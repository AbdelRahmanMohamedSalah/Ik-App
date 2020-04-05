package com.example.abdelrahman.ik_real_estate2.Member.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Admin.Activity.RequestsListAdminActivity;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.EditDetailsRequestActivity;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.RequestDetailsActivity;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.RequestsActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Requests;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterRequests extends RecyclerView.Adapter<AdapterRequests.Holder> {
    Activity activity;
    public ArrayList<Requests> array;
    public Context context;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    View v;

    public AdapterRequests(ArrayList<Requests> paramArrayList, Context paramContext, Activity paramActivity) {
        this.array = paramArrayList;
        this.context = paramContext;
        this.activity = paramActivity;
    }

    public String formatString(String paramString) {
        String str = paramString.toString();
        paramString = str;
        if (str.contains(",")) {
            paramString = str.replaceAll(",", "");
        }
        long l = Long.parseLong(paramString);
        return new DecimalFormat("#,###,###").format(Long.valueOf(l));
    }

    public int getItemCount() {
        return this.array.size();
    }

    public void onBindViewHolder(@NonNull Holder paramHolder, int paramInt) {

        final Requests localRequests = (Requests) this.array.get(paramInt);

        paramHolder.title.setText(localRequests.getTitle());
        paramHolder.price.setText(localRequests.getPrice());
        paramHolder.member.setText(localRequests.getMemberName());



//        if ((this.activity instanceof RequestsListAdminActivity)) {
          //  paramHolder.member.setText(localRequests.getMemberName());
//        }

        this.databaseReference = FirebaseDatabase.getInstance().getReference("Requests");

//        paramHolder.ic_delete.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View paramAnonymousView) {
//                AdapterRequests.this.databaseReference.child(localRequests.getId()).removeValue();
//            }
//        });

        final Intent localIntent = new Intent(this.context, RequestDetailsActivity.class);
        localIntent.putExtra("Request_Details", localRequests);
        firebaseAuth = FirebaseAuth.getInstance();

        paramHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (activity instanceof RequestsActivity) {
                    localIntent.putExtra("Type", "member");
                    AdapterRequests.this.context.startActivity(localIntent);

                } else {
                    localIntent.putExtra("Type", "Admin");

                    AdapterRequests.this.context.startActivity(localIntent);

                }
            }
        });
        if (localRequests.getMemberID().equals(firebaseAuth.getCurrentUser().getUid().toString())) {
            paramHolder.ic_edit.setVisibility(View.VISIBLE);
            paramHolder.ic_delete.setVisibility(View.VISIBLE);
        } else {
            paramHolder.ic_edit.setVisibility(View.INVISIBLE);
            paramHolder.ic_delete.setVisibility(View.INVISIBLE);

        }

        paramHolder.ic_edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {

                Intent intent = new Intent(AdapterRequests.this.context, EditDetailsRequestActivity.class);
                intent.putExtra("Edit_Request_Details", localRequests);
                AdapterRequests.this.context.startActivity(intent);
            }
        });

        paramHolder.ic_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AdapterRequests.this.context);
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("هل انت متاكد من مسح ");
                localStringBuilder.append(localRequests.getTitle().toString());
                alert.setMessage(localStringBuilder.toString());
                alert.setCancelable(true);
                alert.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                        databaseReference.child(localRequests.getId()).removeValue();
                    }
                });
                alert.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                        paramAnonymous2DialogInterface.cancel();
                    }
                });
                alert.create().show();
            }
        });
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt) {
            this.v = LayoutInflater.from(this.context).inflate(R.layout.request_item_admin, paramViewGroup, false);

        return new Holder(this.v);
    }

    public class Holder
            extends RecyclerView.ViewHolder {
        ImageView ic_delete;
        ImageView ic_edit;
        TextView member;
        TextView price;
        TextView title;

        public Holder(View paramView) {
            super(paramView);
            this.title = ((TextView) paramView.findViewById(R.id.textTitleRequestItem));
            this.price = ((TextView) paramView.findViewById(R.id.textPriceRequestItem));
            this.ic_edit = ((ImageView) paramView.findViewById(R.id.icEditRequestItem));
            this.ic_delete = ((ImageView) paramView.findViewById(R.id.icDeleteRequestItem));
            this.member = ((TextView) paramView.findViewById(R.id.textmemberRequestItem));
        }
    }
}

