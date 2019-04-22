package com.example.recyclewithdetails;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;



public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<String> imageNames = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> imageDesc = new ArrayList<>();
    private ArrayList<String> descriptionDetail = new ArrayList<>();

    private Context context;

    public RecycleViewAdapter(Context context,ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String> imageDesc , ArrayList<String> descriptionDetail ) {
        this.imageNames = imageNames;
        this.images = images;
        this.imageDesc = imageDesc;
        this.descriptionDetail = descriptionDetail;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        Log.d(TAG,"onBindViewHolder: called.");

        Glide.with(context)
                .asBitmap()
                .load(images.get(i))
                .into(holder.image);
        holder.imageName.setText(imageNames.get(i));
        holder.imageDesc.setText(imageDesc.get(i));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: clicked on: "+ imageNames.get(i));
                Toast.makeText(context, imageNames.get(i),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, GalleryActivity.class);
                intent.putExtra("image_url",images.get(i));
                intent.putExtra("image_name",imageNames.get(i));
                intent.putExtra("image_desc",descriptionDetail.get(i));
                context.startActivity(intent);
            }
        });
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: clicked on: "+ imageNames.get(i));
                Toast.makeText(context, "Favorite "+imageNames.get(i),Toast.LENGTH_SHORT).show();
            }
        });
//        holder.btnShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG,"onClick: clicked on: "+ imageNames.get(i));
//                Toast.makeText(context, "Share "+ imageNames.get(i),Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return imageNames.size();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Button btnFavorite;
        Button btnShare;
        ImageView image;
        TextView imageName;
        TextView imageDesc;
        CardView parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            imageDesc = itemView.findViewById(R.id.image_desc);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
//            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}
