package com.example.abdelrahman.ik_real_estate2.Member.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Member.Activity.PerivousDetailsActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Perivous;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterPerivous extends RecyclerView.Adapter<AdapterPerivous.Holder>
{
    public ArrayList<Perivous> array;
    public Context context;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    View v;

    public AdapterPerivous(ArrayList<Perivous> paramArrayList, Context paramContext)
    {
        this.array = paramArrayList;
        this.context = paramContext;
    }

    public String formatString(String paramString)
    {
        String str = paramString.toString();
        paramString = str;
        if (str.contains(",")) {
            paramString = str.replaceAll(",", "");
        }
        long l = Long.parseLong(paramString);
        return new DecimalFormat("#,###,###").format(Long.valueOf(l));
    }

    public int getItemCount()
    {
        return this.array.size();
    }

    public void onBindViewHolder(@NonNull Holder paramHolder, int paramInt)
    {
        final Perivous localPerivous = (Perivous)this.array.get(paramInt);
        paramHolder.code.setText(localPerivous.getCode());
        paramHolder.Time.setText(localPerivous.getTime());
        final Object localObject = paramHolder.Date;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localPerivous.getDay());
        localStringBuilder.append("/");
        localStringBuilder.append(localPerivous.getMonth());
        ((TextView)localObject).setText(localStringBuilder.toString());
        final Intent intent = new Intent(this.context, PerivousDetailsActivity.class);
        intent.putExtra("ItemPervious", localPerivous);
        paramHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (localPerivous.getState().toString().equals("1"))
                {
                    AdapterPerivous.this.context.startActivity(intent);
                    return;
                }
                Toast.makeText(AdapterPerivous.this.context, "Admin Not Confirmed Request", 0).show();
            }
        });
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt)
    {
        this.v = LayoutInflater.from(this.context).inflate(R.layout.pervious_item, paramViewGroup, false);
        return new Holder(this.v);
    }

    public class Holder
            extends RecyclerView.ViewHolder
    {
        TextView Date;
        TextView Time;
        TextView code;
        ImageView ic_delete;
        ImageView ic_edit;

        public Holder(View paramView)
        {
            super(paramView);
            this.code = ((TextView)paramView.findViewById(R.id.TextCodePreviousItemActivity));
            this.Date = ((TextView)paramView.findViewById(R.id.TextDatePreviousItemActivity));
            this.Time = ((TextView)paramView.findViewById(R.id.TextTimePreviousItemActivity));
        }
    }
}

