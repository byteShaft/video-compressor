package byteshaft.com.videocompressor;

import android.app.Application;
import android.content.Context;

public class AppGlobals extends Application {

    private static Context sContext;
    private static final String BASE_URL = "http://videocco.com/mobile/mobile.php?action=";
    public static final String SIGNUP_URL = String.format("%s%s", BASE_URL, "new_signup&");
    public static final String LOGIN_URL = String.format("%s%s", BASE_URL, "login&");
    public static final String KEY_USERNAME = "username ";
    public static final String KEY_PASSWORD = "password";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
