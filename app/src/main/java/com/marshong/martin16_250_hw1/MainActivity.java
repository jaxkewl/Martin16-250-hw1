package com.marshong.martin16_250_hw1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MainFragment extends Fragment {

        public static final String TAG = MainFragment.class.getSimpleName();

        public static final String extraEmail = "extra_email";
        public static final String extraPassword = "extra_password";

        //submit button
        Button mSubmitButton;

        //email and password
        EditText mEmailEditText;
        EditText mPasswordEditText;

        public MainFragment() {
        }

        // validate the email
        private boolean isValidEmail(String email) {
            String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }

        // validate the password
        private boolean isValidPassword(String password) {
            if (null != password && password.length() > 0) {
                return true;
            }
            return false;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mPasswordEditText = (EditText) rootView.findViewById(R.id.edit_text_password);
            mEmailEditText = (EditText) rootView.findViewById(R.id.edit_text_email);

            mSubmitButton = (Button) rootView.findViewById(R.id.button_submit);
            mSubmitButton.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {

                                                     String password = mPasswordEditText.getText().toString();
                                                     String email = mEmailEditText.getText().toString();

                                                     Log.d(TAG,"email: " + email);
                                                     Log.d(TAG,"password: " + password);

                                                     boolean validEmail = isValidEmail(email);
                                                     boolean validPass = isValidPassword(password);

                                                     if (!validEmail) {
                                                         mEmailEditText.setError("Email is not valid");
                                                     }
                                                     if (!validPass) {
                                                         mPasswordEditText.setError("Password is not valid");
                                                     }

                                                     //navigate to second screen if everything passes
                                                     if (validEmail && validPass) {
                                                         Intent intent = new Intent(getActivity(), Screen2.class);
                                                         intent.putExtra(extraEmail, email);
                                                         intent.putExtra(extraPassword, password);
                                                         startActivity(intent);
                                                     }
                                                 }
                                             }


            );


            return rootView;
        }
    }
}
