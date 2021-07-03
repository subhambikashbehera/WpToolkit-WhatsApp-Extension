package com.EDUSY.whatswebnoads.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.EDUSY.whatswebnoads.Fragment.ImageFragment;
import com.EDUSY.whatswebnoads.Models.StatusModel;
import com.EDUSY.whatswebnoads.R;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewholder> {
    private final List<StatusModel> imagelist;
    Context context;
    ImageFragment imageFragment;
    public ImageAdapter(List<StatusModel> imagelist, Context context, ImageFragment imageFragment) {
        this.imagelist = imagelist;
        this.context = context;
        this.imageFragment = imageFragment;
    }
    @NonNull
    @Override
    public ImageViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_status,parent,false);
        return new ImageViewholder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ImageViewholder holder, int position) {
        StatusModel statusModel=imagelist.get(position);
        holder.ivthumbnailimageView.setImageBitmap(statusModel.getThumblin());

        holder.ivthumbnailimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,subhamfull.class);
                i.putExtra("image",statusModel.getUri().toString());
                context.startActivity(i);

            }
        });


    }
    @Override
    public int getItemCount() {
        return imagelist.size();
    }
    public class ImageViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivThumbnail)
        ImageView ivthumbnailimageView;
        @BindView(R.id.ibSavetoGallary)
        ImageButton imageButtondownload;
        public ImageViewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            imageButtondownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StatusModel statusModel= imagelist.get(getAdapterPosition());
                    if (statusModel!=null) { try {
                            imageFragment.downloadimage(statusModel);
                        } catch (IOException e) {
                            e.printStackTrace(); } } }}); }}}
