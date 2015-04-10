package com.marshong.martin16_250_hw1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Screen2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SecondScreenFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_screen2, menu);
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
    public static class SecondScreenFragment extends Fragment {

        public static final String TAG = SecondScreenFragment.class.getSimpleName();

        TextView mPassword;
        TextView mEmail;

        public SecondScreenFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_screen2, container, false);

            mPassword = (TextView) rootView.findViewById(R.id.text_view_password);
            mEmail = (TextView) rootView.findViewById(R.id.text_view_email);

            //get the email and password from the bundle
            Bundle bundle = getActivity().getIntent().getExtras();
            String password = bundle.getString(MainActivity.MainFragment.extraPassword);
            String email = bundle.getString(MainActivity.MainFragment.extraEmail);

            //Log.d(TAG, "email: " + email);
            //Log.d(TAG, "password: " + password);

            mPassword.setText(password);
            mEmail.setText(email);

            return rootView;
        }
    }
}
