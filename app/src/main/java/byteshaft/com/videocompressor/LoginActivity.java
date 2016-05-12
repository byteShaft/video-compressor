package byteshaft.com.videocompressor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends Activity implements View.OnClickListener {

    private ImageButton backButton;
    private EditText mUser_email;
    private EditText mUser_password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backButton = (ImageButton) findViewById(R.id.back_button);
        mUser_email = (EditText) findViewById(R.id.email_et);
        mUser_password = (EditText) findViewById(R.id.password_et);
        loginButton = (Button) findViewById(R.id.button_login);

        loginButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                System.out.println("back button clicked");
                break;
            case R.id.button_login:
                System.out.println("login button clicked");
                break;
        }
    }
}

