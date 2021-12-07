package com.alhud.alhudastore.totaloil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alhud.alhudastore.R;
import com.bumptech.glide.Glide;

public class Adapter_menu_totaloil extends RecyclerView.Adapter<Adapter_menu_totaloil.ViewHolder> {

    private LayoutInflater inflater;
    private String[] sContent;
    private Context mActivity;
    ImageView img;

    Adapter_menu_totaloil(Context context, String[] contents ){
        this.inflater = LayoutInflater.from(context);
        this.sContent = contents;
        mActivity=context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_view_menu_totaloil,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Glide.with(this.mActivity)
                .load(sContent[i])
                .placeholder(R.color.colorPrimary)
                .into(img);
        /*viewHolder.number.setText(m);
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        // generate random color


        shape.setColor(Color.rgb(255,30,30));
        viewHolder.circle.setBackground(shape);*/
    }

    @Override
    public int getItemCount() {
        return sContent.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // implement onClick
            /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),Details.class);
                    // send story title and contents through recyclerview to detail activity
                    i.putExtra("titleOfStory",sTitles[getAdapterPosition()]);
                    i.putExtra("contentOfStory",sContent[getAdapterPosition()]);
                    v.getContext().startActivity(i);
                }
            });*/

            img = itemView.findViewById(R.id.img1);


        }
    }

}

