package com.oscar.notficacionlistener.Utils.linkPreview;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by oemy9 on 01/10/2017.
 */

public interface DowloadImageCallback {
    void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url);
}
