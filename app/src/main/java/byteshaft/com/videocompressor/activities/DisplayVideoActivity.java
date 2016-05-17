package byteshaft.com.videocompressor.activities;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import byteshaft.com.videocompressor.R;
import byteshaft.com.videocompressor.utils.AppGlobals;

public class DisplayVideoActivity extends AppCompatActivity {

    private String path = "";
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_video_display);
        path = getIntent().getStringExtra(AppGlobals.KEY_VIDEO_PATH);
        if (path.trim().isEmpty()) {
            finish();
        }
        showFrames();
        new LoadThumbnailTask().execute();
    }

    class LoadThumbnailTask extends AsyncTask<String, Bitmap, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(path);
            String time = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long timeInmillisec = Long.parseLong(time);
            long duration = timeInmillisec / 1000;
            long hours = duration / 3600;
            long minutes = (duration - hours * 3600) / 60;
            long seconds = duration - (hours * 3600 + minutes * 60);
            Log.i("Time", "time in milliseconds:"+ timeInmillisec + " duration:" + duration+
                    " hours:"+ hours+ " minutes" + minutes+ " seconds:" + seconds+ " ");
            for (int value = 0; value < seconds; value++) {
                publishProgress(mediaMetadataRetriever.getFrameAtTime(value));
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Bitmap... values) {
            super.onProgressUpdate(values);
            ImageView imageView = new ImageView(DisplayVideoActivity.this);
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100,100);
            imageView.setLayoutParams(parms);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            Log.i("current video frame", ""+ value);
            imageView.setImageBitmap(values[0]);
            layout.addView(imageView);
        }
    }

    private void showFrames() {
        layout = (LinearLayout) findViewById(R.id.linear);
//        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
//        mediaMetadataRetriever.setDataSource(path);
//        String time = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//        long timeInmillisec = Long.parseLong(time);
//        long duration = timeInmillisec / 1000;
//        long hours = duration / 3600;
//        long minutes = (duration - hours * 3600) / 60;
//        long seconds = duration - (hours * 3600 + minutes * 60);
//        Log.i("seconds", ""+ seconds);
//        for (int value = 0; value < seconds; value++) {
//
//        }
    }
}
