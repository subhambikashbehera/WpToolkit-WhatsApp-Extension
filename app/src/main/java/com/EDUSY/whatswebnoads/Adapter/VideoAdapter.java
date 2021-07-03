package com.EDUSY.whatswebnoads.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.EDUSY.whatswebnoads.Fragment.VideoFragment;
import com.EDUSY.whatswebnoads.Models.StatusModel;
import com.EDUSY.whatswebnoads.R;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private final List<StatusModel> videolist;
    Context context;
    VideoFragment videofragment;
    public VideoAdapter(List<StatusModel> videolist, Context context, VideoFragment videofragment) {
        this.videolist = videolist;
        this.context = context;
        this.videofragment = videofragment;
    }
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_status,parent,false);
        return new VideoAdapter.VideoViewHolder(v); }
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        StatusModel statusModel=videolist.get(position);
        holder.ivthumbnailimageView.setImageBitmap(statusModel.getThumblin()); }
    @Override
    public int getItemCount() {
        return videolist.size();
    }
    public class VideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivThumbnail)
        ImageView ivthumbnailimageView;
        @BindView(R.id.ibSavetoGallary)
        ImageButton imageButtondownload;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            imageButtondownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StatusModel statusModel= videolist.get(getAdapterPosition());
                    if (statusModel!=null)
                    { try { videofragment.downloadvideo(statusModel); } catch (IOException e) {
                            e.printStackTrace(); } } }}); }}}
