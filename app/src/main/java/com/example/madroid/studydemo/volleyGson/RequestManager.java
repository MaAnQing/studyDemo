package com.example.madroid.studydemo.volleyGson;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * @FileName: com.example.madroid.studydemo.volleyGson.RequestManager.java
 * @author: madroid
 * @date: 2015-06-16 17:31
 */
public class RequestManager {
    private static final String TAG = "RequestManager";
    private static RequestManager mManager ;
    private RequestQueue mRequestQueue ;
    private Context mCtx ;
    private ImageLoader mImageLoader ;

    private RequestManager(Context context) {
        mCtx = context;
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized RequestManager getInstance(Context cox){
        if (mManager == null){
            mManager = new RequestManager(cox) ;
        }
        return mManager ;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
