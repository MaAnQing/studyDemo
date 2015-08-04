package com.example.madroid.studydemo.ebook;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.madroid.studydemo.R;

import java.io.File;

import pl.droidsonroids.gif.GifImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AppCompatActivity {

    private static final String TAG = "PhotoViewActivity" ;

    ImageView mImageView;
    GifImageView mGifView ;
    VideoView mVideoView ;
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        mImageView = (ImageView) findViewById(R.id.iv_photo);
        mGifView = (GifImageView) findViewById(R.id.iv_gif) ;
        mVideoView = (VideoView) findViewById(R.id.iv_video) ;

        initPhotoView();
    }

    private void initPhotoView() {
        // Any implementation of ImageView can be used!
        mImageView.setVisibility(View.VISIBLE);
        mGifView.setVisibility(View.GONE);
        mVideoView.setVisibility(View.GONE);

        // Set the Drawable displayed
//        Drawable bitmap = getResources().getDrawable(R.drawable.no);
//        mImageView.setImageDrawable(bitmap);

        mImageView.setImageResource(R.drawable.ic_md_no);

        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.update();
    }

    private void setupGifView() {
        mImageView.setVisibility(View.GONE);
        mGifView.setVisibility(View.VISIBLE);
        mVideoView.setVisibility(View.GONE);


        mGifView.setImageResource(R.drawable.md);
    }

    private void setupVideo() {
        mImageView.setVisibility(View.GONE);
        mGifView.setVisibility(View.GONE);
        mVideoView.setVisibility(View.VISIBLE);


        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Video.mp4" ;
        //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/md.webm" ;
        File file = new File(path) ;

        Log.i(TAG, "path:" + path + "; file exizs:" + file.exists()) ;

        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() +
                "/" + R.raw.videoviewdemo));
        //mVideoView.setVideoPath(path);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_photo_view:
                initPhotoView();
                break;

            case R.id.action_gif_view:
                setupGifView();
                break;

            case R.id.action_video_view:
                setupVideo();
                break;

            default:
                break;
        }


        return true ;
    }


}
