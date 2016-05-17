package byteshaft.com.videocompressor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import byteshaft.com.videocompressor.utils.Helpers;

public class SelectVideo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Helpers.isUserLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
        }
        setContentView(R.layout.layout_select_video);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!Helpers.isUserLoggedIn()) {
            finish();
        }
    }
}
