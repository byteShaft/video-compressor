package byteshaft.com.videocompressor.utils;

import android.app.Application;
import android.content.Context;

public class AppGlobals extends Application {

    private static Context sContext;
    private static final String BASE_URL = "http://videocco.com/mobile/mobile.php?action=";
    public static final String SIGNUP_URL = String.format("%s%s", BASE_URL, "new_signup&");
    public static final String LOGIN_URL = String.format("%s%s", BASE_URL, "login&");
    public static final String KEY_USERNAME = "username ";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER_LOGIN = "user_login";
    public static final String KEY_USER_PUBLIC_ID = "user_public_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_ACCOUNT_ID = "account_id";
    public static final String KEY_USER_TOKEN = "token";
    public static final String KEY_VIDEO_PATH = "video_path";
    public static final String KEY_TO_BE_PROCESSED_VIDEO_PATH = "to_be_processed_video";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
