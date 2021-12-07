package com.alhud.alhudastore.staiton;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.bumptech.glide.Glide;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private String[] staiton_name;
    private String[] address;
    private String[] longitude;
    private String[] latitude;
    private String[] phone;
    private String[] image;
    private Context mActivity;
    ImageView img;

    Adapter(Context context, String[] staiton_name, String[] address , String[] longitude,String[] latitude, String[] phone , String[] image){
        this.inflater = LayoutInflater.from(context);
        this.staiton_name = staiton_name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.image = image;
        mActivity=context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_view_staiton,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.text1.setText(staiton_name[i]);
        viewHolder.text2.setText(address[i]);
        Glide.with(this.mActivity)
                .load(image[i])
                .placeholder(R.color.colorPrimary)
                .into(img);
        viewHolder.icon_news_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phone[i]));
                    mActivity.startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(mActivity, "Make permission for app to call", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return staiton_name.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView text1,text2;
        ImageView icon_news_call;

       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // implement onClick
              itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:"+latitude[getAdapterPosition()]+","+longitude[getAdapterPosition()]);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    v.getContext().startActivity(mapIntent);
                }
            });
           text1 = itemView.findViewById(R.id.text1);
           text2 = itemView.findViewById(R.id.text2);
           img = itemView.findViewById(R.id.img123);
           icon_news_call = itemView.findViewById(R.id.icon_news_call);


       }
    }

}

