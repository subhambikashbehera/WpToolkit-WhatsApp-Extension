package com.EDUSY.whatswebnoads.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.EDUSY.whatswebnoads.Adapter.VideoAdapter;
import com.EDUSY.whatswebnoads.Models.StatusModel;
import com.EDUSY.whatswebnoads.R;
import com.EDUSY.whatswebnoads.Utils.Myconstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoFragment extends Fragment {

    @BindView(R.id.recycleviewVideo)
    RecyclerView recyclerView;
    @BindView(R.id.progressBarvideo)
    ProgressBar progressBar;
    Handler handler=new Handler();
    ArrayList<StatusModel> videoModelArrayList;
    VideoAdapter videoAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_video,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        videoModelArrayList =new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        getstatusVideo();
    }
    private void getstatusVideo() {
        if (Myconstants.STATUS_DIRECTORY.exists())
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusfiles=Myconstants.STATUS_DIRECTORY.listFiles();
                    if (statusfiles!=null && statusfiles.length>0){
                        Arrays.sort(statusfiles);
                        for (final File statusFile:statusfiles)
                        {
                            StatusModel statusModel=new StatusModel(statusFile,statusFile.getName(),statusFile.getAbsolutePath(),statusFile.toURI());

                            statusModel.setThumblin(getThumbline(statusModel));
                            if (statusModel.isIsvideo())
                            {
                                videoModelArrayList.add(statusModel);
                            }
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                videoAdapter=new VideoAdapter(videoModelArrayList, getContext(),VideoFragment.this);
                                recyclerView.setAdapter(videoAdapter);
                                videoAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(),"LOADING WAIT FOR SOMETIME",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private Bitmap getThumbline(StatusModel statusModel) {
        if (statusModel.isIsvideo())
        {
            return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);

        }else {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()),
                    Myconstants.THUMBLINE,Myconstants.THUMBLINE);
        }
    }

    public void downloadvideo(StatusModel statusModel) throws IOException {

        File file=new File(Myconstants.APP_DIR);
        if (!file.exists())
        {
            file.mkdirs();
        }
        File destfile=new File(file+File.separator +statusModel.getTittle());
        if (destfile.exists())
        {
            destfile.delete();
        }
        copyFile(statusModel.getFile(),destfile);
        Toast.makeText(getActivity(),"Download Complete",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(destfile));
        getActivity().sendBroadcast(intent);
    }
    private void copyFile(File file, File destfile) throws IOException {

        if (!destfile.getParentFile().exists())
        {
            destfile.getParentFile().mkdirs();
        }

        if (!destfile.exists()){
            destfile.createNewFile();
        }
        FileChannel source=null;
        FileChannel destination=null;
        source =new FileInputStream(file).getChannel();
        destination=new FileOutputStream(destfile).getChannel();
        destination.transferFrom(source,0,source.size());
        source.close();
        destination.close();
    }
}
