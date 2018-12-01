package com.bw.movie.utils;

import android.net.Uri;
import android.support.annotation.DrawableRes;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * SimpDrawViewUtils 工具类
 */
public class SimpDrawViewUtils {
    /**
     * 高斯模糊
     */
    public static void showUrlBlur(SimpleDraweeView draweeView, String url, int iterations, int blurRadius) {
        try {
            Uri uri = Uri.parse(url);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(draweeView.getController())
                    .setImageRequest(request)
                    .build();
            draweeView.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 加载本地资源图片
     *
     * @param draweeView
     * @param resId
     */

    public static void showRes(SimpleDraweeView draweeView, @DrawableRes int resId) {
        try {
            // 你没看错，这里是三个///。
            draweeView.setImageURI(Uri.parse("res:///" + resId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
