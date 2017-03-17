package com.android.leleyouba.ybshop.common.util;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.IntegerRes;
import android.widget.ImageView;

import com.android.leleyouba.ybshop.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;

import java.io.File;


/**
 * Created by xalo on 2017/3/15.
 */

public final class ImageLoaderUtil {

    public static void loadImage(Context context, String url, ImageView imageView){

        Glide.with(context).load(url).asBitmap().error(R.mipmap.apple).placeholder(R.mipmap.apple).into(imageView);

    }
    public static void loadImage(Context context, File file, ImageView imageView){

        Glide.with(context).load(file).asBitmap().error(R.mipmap.apple).placeholder(R.mipmap.apple).into(imageView);

    }
    public static void loadImage(Context context, @IdRes int resIS, ImageView imageView){

        Glide.with(context).load(resIS).asBitmap().error(R.mipmap.apple).placeholder(R.mipmap.apple).into(imageView);

    }
    public static void loadImage(Context context, byte[] model, ImageView imageView){

        Glide.with(context).load(model).asBitmap().error(R.mipmap.apple).placeholder(R.mipmap.apple).into(imageView);
    }

    public static void loadImage(Context context, Uri uri, ImageView imageView){

        Glide.with(context).load(uri).asBitmap().error(R.mipmap.apple).placeholder(R.mipmap.apple).into(imageView);
    }

}
