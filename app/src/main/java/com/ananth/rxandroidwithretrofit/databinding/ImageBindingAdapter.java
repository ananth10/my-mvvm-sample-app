package com.ananth.rxandroidwithretrofit.databinding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.view.profile.ProfileActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class ImageBindingAdapter {

    @BindingAdapter({"blurImageView"})
    public static void loadBlurImage(ImageView imageView, String url) {
        System.out.println("ImageUrl 1:"+url);
        if(!TextUtils.isEmpty(url)) {
            Picasso.with(imageView.getContext())
                    .load(url) // thumbnail url goes here
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(new BlurTransformation(imageView.getContext(), 40))
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.with(imageView.getContext())
                                    .load(url) // image url goes here
                                    .into(imageView);
                        }

                        @Override
                        public void onError() {
                            System.out.println("ImageUrl 1: error");
                        }
                    });

        }
    }


}
