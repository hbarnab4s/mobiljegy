package com.example.mobiljegy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;


public class RegisterActivity extends AppCompatActivity {

    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();

    EditText userEmailEditText;
    EditText userNameEditText;
    EditText editText_Password;
    EditText editText_PasswordAgain;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmailEditText = findViewById(R.id.userEmailEditText);
        userNameEditText = findViewById(R.id.userNameEditText);
        editText_Password = findViewById(R.id.editText_Password);
        editText_PasswordAgain = findViewById(R.id.editText_PasswordAgain);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String userName = preferences.getString("userName", "");
        String password = preferences.getString("password", "");

        userNameEditText.setText(userName);
        editText_Password.setText(password);
        editText_PasswordAgain.setText(password);

        Log.i(LOG_TAG, "onCreate");

        Bundle bundle = getIntent().getExtras();
        int secret_key = bundle.getInt("SECRET_KEY");

        if(secret_key != 1823453) {
            finish();
        }
    }

    public void register(View view) {
        String userEmail = userEmailEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String password = editText_Password.getText().toString();
        String passwordAgain = editText_PasswordAgain.getText().toString();

        if(!password.equals(passwordAgain)) {
            Log.e(LOG_TAG, "Nem egyezik meg a jelszó és a megerősítése!");
        } else {
            Log.i(LOG_TAG, "Regisztrált: " + userName + ", email cím: " + userEmail + ", jelszó: " + password + ", jelszó megerősítése: " + passwordAgain + ". ");
        }




    }

    public void cancel(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }
}