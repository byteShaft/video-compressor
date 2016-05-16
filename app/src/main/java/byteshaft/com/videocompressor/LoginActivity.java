package byteshaft.com.videocompressor;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton backButton;
    private Button loginButton;
    private EditText mUser_email;
    private EditText mUser_password;

    private String mEmail;
    private String mPassword;

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
                startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                break;
            case R.id.button_login:
                System.out.println("login button clicked");
                System.out.println(validate());
                if (!validate()) {
                    Toast.makeText(getApplicationContext(), "invalid credentials",
                            Toast.LENGTH_SHORT).show();
                } else {
                    new LoginTask(mEmail, mPassword).execute();
                }
                break;
        }
    }

    public boolean validate() {
        boolean valid = true;

        mEmail = mUser_email.getText().toString();
        mPassword = mUser_password.getText().toString();

        if (mEmail.trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.
                matcher(mEmail).matches()) {
            mUser_email.setError("enter a valid email address");
            valid = false;
        } else {
            mUser_email.setError(null);
        }

        if (mPassword.isEmpty() || mPassword.length() < 4 || mPassword.length() > 10) {
            mUser_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mUser_password.setError(null);
        }
        return valid;
    }


    class LoginTask extends AsyncTask<Void, Void, Void> {
        public String mEmail;
        private String mPassword;

        public LoginTask(String email, String password) {
            super();
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            WebServiceHelper.showProgressDialog(LoginActivity.this , "LoggingIn");
        }

        @Override
        protected Void doInBackground(Void... params) {

            String data = WebServiceHelper.getLoginString(mEmail, mPassword);
            try {
                HttpURLConnection connection = WebServiceHelper.openConnectionForUrl(data, "POST");
                String output = WebServiceHelper.readResponseData(connection);
                Log.i("OK", output);
                System.out.println(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            WebServiceHelper.dismissProgressDialog();
        }
    }
}

