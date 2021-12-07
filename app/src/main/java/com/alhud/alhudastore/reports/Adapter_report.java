package com.alhud.alhudastore.reports;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alhud.alhudastore.R;

public class Adapter_report extends RecyclerView.Adapter<Adapter_report.ViewHolder> {

    private LayoutInflater inflater;
    private String[] sTitles;
    private String[] name;
    private String id;

    Adapter_report(Context context, String[] titles,String id ,String[] name){
        this.inflater = LayoutInflater.from(context);
        this.sTitles = titles;
        this.name=name;
        this.id = id;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_view_report,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String y1 = name[i];
        viewHolder.y1.setText(y1);

    }

    @Override
    public int getItemCount() {
        return sTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView y1;

       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // implement onClick
             itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),show_reports.class);
                    // send story title and contents through recyclerview to detail activity
                    i.putExtra("cardId",sTitles[getAdapterPosition()]);
                    i.putExtra("id",id);
                    i.putExtra("name",name[getAdapterPosition()]);
                    v.getContext().startActivity(i);
                }
            });
            y1 = itemView.findViewById(R.id.y1);



        }
    }

}

