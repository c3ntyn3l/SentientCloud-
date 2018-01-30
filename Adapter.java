package com.example.c3ntry.sentientcloud;

import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by c3ntyn3l on 6/6/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    Context context;
    private List<List_items> list ;

    public Adapter(Context context, List<List_items> list) {
        this.context = context;
        this.list = list;
    }

    public final static String TAG="VIVZ";



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=LayoutInflater.from(parent.getContext())
        .inflate(R.layout.cardview,parent,false);
        Log.d(TAG,"onCreateViewHolder: ");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final List_items lists =list.get(position);
        holder.up.setText(lists.getTitle());
        holder.middle.setText(lists.getMain());
        holder.down.setText((lists.getDown()));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked "+ lists.getMain(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.d(TAG,"onBindViewHolder: "+position);
    }


    @Override
    public int getItemCount()
    {
       return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView up;
        TextView middle;
        TextView down;
        LinearLayout linearLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);
            up=(TextView) itemView.findViewById(R.id.title);
            middle=(TextView)itemView.findViewById(R.id.main);
            down=(TextView)itemView.findViewById(R.id.down);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearlayout);
        }
    }
}
