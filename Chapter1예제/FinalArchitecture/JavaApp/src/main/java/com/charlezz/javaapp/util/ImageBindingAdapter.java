package com.charlezz.javaapp.util;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class ImageBindingAdapter {

    @BindingAdapter("image")
    public void setImage(ImageView imageView, String path){
        if(!TextUtils.isEmpty(path)){
            Glide.with(imageView).load(path).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
        }
    }
}
