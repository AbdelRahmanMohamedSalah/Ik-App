package com.example.abdelrahman.ik_real_estate2.Member.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Member.Activity.RecyclerPerivewMemberActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Perivous;
import com.example.abdelrahman.ik_real_estate2.Moudel.Users;
import com.example.abdelrahman.ik_real_estate2.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.Holder>
{
    public String FromActivity;
    public String PeriviewName;
    public String RequestName;
    public ArrayList<Perivous> array;
    public Context context;
    private Map<String, Users> users;

    public AdapterRecycle(ArrayList<Perivous> paramArrayList, Context paramContext)
    {
        this.array = paramArrayList;
        this.context = paramContext;
    }

    public AdapterRecycle(ArrayList<Perivous> paramArrayList, Map<String, Users> paramMap, Context paramContext)
    {
        this.array = paramArrayList;
        this.users = paramMap;
        this.context = paramContext;
    }

    public AdapterRecycle(ArrayList<Perivous> paramArrayList, Map<String, Users> paramMap, Context paramContext, String paramString)
    {
        this.array = paramArrayList;
        this.users = paramMap;
        this.context = paramContext;
        this.FromActivity = paramString;
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
        Perivous localPerivous = (Perivous)this.array.get(paramInt);
        this.RequestName = ((Users)this.users.get(localPerivous.getClientMemberId())).getName();
        this.PeriviewName = ((Users)this.users.get(localPerivous.getPeriviewMemberId())).getName();
        paramHolder.code.setText(localPerivous.getCode());
        paramHolder.Time.setText(localPerivous.getTime());
        final Object localObject = paramHolder.Date;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localPerivous.getDay());
        localStringBuilder.append("/");
        localStringBuilder.append(localPerivous.getMonth());
        ((TextView)localObject).setText(localStringBuilder.toString());
        paramHolder.TextRequestMemberName.setText(this.RequestName);
        paramHolder.TextPeriviewMemberName.setText(this.PeriviewName);

        final Intent intent = new Intent(this.context, RecyclerPerivewMemberActivity.class);

        intent.putExtra("PerviewMember", this.PeriviewName);
        intent.putExtra("RequestName", this.RequestName);
        intent.putExtra("previewDetails", localPerivous);
        paramHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                AdapterRecycle.this.context.startActivity(intent);
            }
        });
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt)
    {
        return new Holder(LayoutInflater.from(this.context).inflate(R.layout.perivous_item_admin, paramViewGroup, false));
    }

    public class Holder
            extends RecyclerView.ViewHolder
    {
        TextView Date;
        TextView TextPeriviewMemberName;
        TextView TextRequestMemberName;
        TextView Time;
        TextView code;

        public Holder(View paramView)
        {
            super(paramView);
            this.code = ((TextView)paramView.findViewById(R.id.TextCodePreviousItemAdminActivity));
            this.Date = ((TextView)paramView.findViewById(R.id.TextDatePreviousItemActivity));
            this.Time = ((TextView)paramView.findViewById(R.id.TextTimePreviousItemActivity));
            this.TextPeriviewMemberName = ((TextView)paramView.findViewById(R.id.TextPerivierwMemberId));
            this.TextRequestMemberName = ((TextView)paramView.findViewById(R.id.TextRequestMemberId));
        }
    }
}

