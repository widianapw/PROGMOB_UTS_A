package com.example.recyclewithdetails;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class GridActivity extends RecyclerView.Adapter<GridActivity.GridViewHolder> {

    private static final String TAG = "ListView";
    private ArrayList<String> imageNames = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> imageDesc = new ArrayList<>();
    private ArrayList<String> descriptionDetail = new ArrayList<>();

    private Context context;

    public GridActivity(Context context,ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String> imageDesc , ArrayList<String> descriptionDetail ) {

        this.imageNames = imageNames;
        this.images = images;
        this.imageDesc = imageDesc;
        this.descriptionDetail = descriptionDetail;
        this.context = context;

    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, final int i) {
        Glide.with(context)
                .asBitmap()
                .load(images.get(i))
                .into(holder.imgPhoto);
        holder.imgPhoto.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}

