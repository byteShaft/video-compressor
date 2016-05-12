package byteshaft.com.videocompressor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private ImageButton backButton;
    private EditText user_name;
    private EditText user_email;
    private EditText user_password;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backButton = (ImageButton) findViewById(R.id.back_button);
        user_name = (EditText) findViewById(R.id.user_name);
        user_email = (EditText) findViewById(R.id.email_et);
        user_password = (EditText) findViewById(R.id.password_et);
        signUpButton = (Button) findViewById(R.id.button_sign_up);

        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                System.out.println("back Button");
                break;
            case R.id.button_sign_up:
                System.out.println("Sign Up Button");
                break;
        }
    }
}
