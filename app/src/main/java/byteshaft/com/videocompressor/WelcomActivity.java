package byteshaft.com.videocompressor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomActivity extends Activity implements View.OnClickListener {

    private Button loginButton;
    private TextView singUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        loginButton = (Button) findViewById(R.id.button_login);
        singUp = (TextView) findViewById(R.id.signup_text);
        loginButton.setOnClickListener(this);
        singUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                System.out.println("login Button");
                startActivity(new Intent(WelcomActivity.this, LoginActivity.class));
                break;
            case R.id.signup_text:
                System.out.println("sign up Text");
                startActivity(new Intent(WelcomActivity.this, RegisterActivity.class));
                break;
        }
    }
}
