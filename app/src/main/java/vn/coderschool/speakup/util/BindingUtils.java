package vn.coderschool.speakup.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by kenp on 26/03/2017.
 */

public class BindingUtils {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .into(imageView);
    }
}
