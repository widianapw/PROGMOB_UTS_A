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



public class ListActivity extends RecyclerView.Adapter<ListActivity.ViewHolder> {

    private static final String TAG = "ListView";

    private ArrayList<String> imageNames = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> imageDesc = new ArrayList<>();
    private ArrayList<String> descriptionDetail = new ArrayList<>();

    private Context context;

    public ListActivity(Context context,ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String> imageDesc , ArrayList<String> descriptionDetail ) {
        this.imageNames = imageNames;
        this.images = images;
        this.imageDesc = imageDesc;
        this.descriptionDetail = descriptionDetail;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent,false);
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

        ImageView image;
        TextView imageName;
        TextView imageDesc;
        RelativeLayout parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.gambar);
            imageName = itemView.findViewById(R.id.nama);
            imageDesc = itemView.findViewById(R.id.deskripsi);
            parentLayout = itemView.findViewById(R.id.list);
        }
    }
}
