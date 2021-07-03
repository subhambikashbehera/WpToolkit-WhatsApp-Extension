package com.EDUSY.whatswebnoads.Models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.net.URI;

public class StatusModel {

    private static final String mp4=".mp4";
    private final File file;

    private Bitmap thumblin;
    private final String tittle,path;
    private boolean isvideo;
    URI uri;

    public StatusModel(File file, String tittle, String path, URI uri) {
        this.file = file;
        this.tittle = tittle;
        this.path = path;
        this.uri=uri;
        this.isvideo=file.getName().endsWith(mp4);
    }

    public File getFile() {
        return file;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Bitmap getThumblin() {
        return thumblin;
    }

    public void setThumblin(Bitmap thumblin) {
        this.thumblin = thumblin;
    }

    public String getTittle() {
        return tittle;
    }

    public String getPath() {
        return path;
    }

    public boolean isIsvideo() {
        return isvideo;
    }

    public void setIsvideo(boolean isvideo) {
        this.isvideo = isvideo;
    }
}
