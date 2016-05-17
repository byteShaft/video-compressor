package byteshaft.com.videocompressor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import byteshaft.com.videocompressor.utils.AppGlobals;
import byteshaft.com.videocompressor.utils.Helpers;
import byteshaft.com.videocompressor.utils.WebServiceHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mBackButton;
    private EditText mFirstNameEntry;
    private EditText mLastNameEntry;
    private EditText mEmailEntry;
    private EditText mPasswordEntry;
    private Button mSignUpButton;

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mBackButton = (ImageButton) findViewById(R.id.back_button);
        mFirstNameEntry = (EditText) findViewById(R.id.first_name);
        mLastNameEntry = (EditText) findViewById(R.id.last_name);
        mEmailEntry = (EditText) findViewById(R.id.email_et);
        mPasswordEntry = (EditText) findViewById(R.id.password_et);
        mSignUpButton = (Button) findViewById(R.id.button_sign_up);

        mBackButton.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);

    }

    public boolean validate() {
        boolean valid = true;

        mFirstName = mFirstNameEntry.getText().toString();
        mLastName= mLastNameEntry.getText().toString();
        mEmail = mEmailEntry.getText().toString();
        mPassword = mPasswordEntry.getText().toString();

        if (mFirstName.trim().isEmpty() || mFirstName.length() < 3) {
            mFirstNameEntry.setError("at least 3 characters");
            valid = false;
        } else {
            mFirstNameEntry.setError(null);
        }

        if (mLastName.trim().isEmpty() || mLastName.length() < 3) {
            mLastNameEntry.setError("at least 3 characters");
            valid = false;
        } else {
            mLastNameEntry.setError(null);
        }

        if (mEmail.trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            mEmailEntry.setError("enter a valid email address");
            valid = false;
        } else {
            mEmailEntry.setError(null);
        }

        if (mPassword.isEmpty() || mPassword.length() < 4 || mPassword.length() > 10) {
            mPasswordEntry.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mPasswordEntry.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                System.out.println("back Button");
                onBackPressed();
//                startActivity(new Intent(RegisterActivity.this, WelcomeActivity.class));
                break;
            case R.id.button_sign_up:
                validate();
                System.out.println("Sign Up Button");
                if (!validate()) {
                    Toast.makeText(getApplicationContext(), "Incorrect Entry", Toast.LENGTH_SHORT).show();
                } else {
                    new SingUpTask(mFirstName, mLastName, mEmail, mPassword).execute();
                }
                break;
        }
    }

    private class SingUpTask extends AsyncTask<Void, Void, Void> {

        private String mFirstName;
        private String mLastName;
        private String mEmail;
        private String mPassword;

        public SingUpTask(String firstName, String lastName, String email, String password) {
            super();
            mFirstName = firstName;
            mLastName = lastName;
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            WebServiceHelper.showProgressDialog(RegisterActivity.this , "Registering");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String data = WebServiceHelper.getRegistrationString(
                    mFirstName, mLastName, mEmail, mPassword);
            try {
                HttpURLConnection connection = WebServiceHelper.openConnectionForUrl(data, "POST");
                System.out.println(data);
                String output = WebServiceHelper.readResponseData(connection);

                InputStream in = connection.getInputStream();
                String response = WebServiceHelper.convertInputStreamToString(in);
                JSONObject jsonObject = new JSONObject(response);
                String result = jsonObject.getString("result");
                if (result.equals("success")) {

                    String public_user_id = jsonObject.getString("public_user_id");
                    String userId = jsonObject.getString("user_id");
                    String accoutnId = jsonObject.getString("account_id");
                    String token = jsonObject.getString("session_token");
                    // saveing values
                    Helpers.saveDataToSharedPreferences(AppGlobals.KEY_USER_PUBLIC_ID, public_user_id);
                    Helpers.saveDataToSharedPreferences(AppGlobals.KEY_USER_ID, userId);
                    Helpers.saveDataToSharedPreferences(AppGlobals.KEY_USER_ACCOUNT_ID, accoutnId);
                    Helpers.saveDataToSharedPreferences(AppGlobals.KEY_USER_TOKEN, token);
                    Helpers.saveUserLogin(true);
                }

                System.out.println(output);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
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
