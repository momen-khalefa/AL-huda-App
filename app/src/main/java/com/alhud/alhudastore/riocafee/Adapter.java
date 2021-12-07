package com.alhud.alhudastore.riocafee;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alhud.alhudastore.R;
import com.bumptech.glide.Glide;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private String[] rio_name;
    private String[] location;
    private String[] sContent1;
    private String[] longitude;
    private String[] latitude;
    private String[] phone;
    private String[] image;
    private Context mActivity;
    ImageView img;

    Adapter(Context context, String[] rio_name, String[] location , String[] longitude,String[] latitude,String[] phone,String[] image){
        this.inflater = LayoutInflater.from(context);
        this.rio_name = rio_name;
        this.location = location;
        this.longitude = longitude;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.image = image;
        mActivity=context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_view_rio,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title_text.setText(rio_name[i]);
        viewHolder.address.setText(location[i]);
        Glide.with(this.mActivity)
                .load(image[i])
                .placeholder(R.color.colorPrimary)
                .into(img);
        viewHolder.icon_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" +phone[i]));
                mActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rio_name.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title_text,address;
        ImageView icon_call;


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
           title_text = itemView.findViewById(R.id.title_text);
           address = itemView.findViewById(R.id.address);
           img= itemView.findViewById(R.id.img_rio);
           icon_call = itemView.findViewById(R.id.icon_call);



       }
    }

}

