package com.example.mobiljegy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Spinner;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();

    EditText userEmailEditText;
    EditText userNameEditText;
    EditText editText_Password;
    EditText editText_PasswordAgain;

    Spinner email_spinner;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmailEditText = findViewById(R.id.userEmailEditText);
        userNameEditText = findViewById(R.id.userNameEditText);
        editText_Password = findViewById(R.id.editText_Password);
        editText_PasswordAgain = findViewById(R.id.editText_PasswordAgain);

        email_spinner = findViewById(R.id.emailSpinner);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String userName = preferences.getString("userName", "");
        String password = preferences.getString("password", "");

        userNameEditText.setText(userName);
        editText_Password.setText(password);
        editText_PasswordAgain.setText(password);

        email_spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.email_modes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        email_spinner.setAdapter(adapter);

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
        String emailType = email_spinner.getSelectedItem().toString();

        if(!password.equals(passwordAgain)) {
            Log.e(LOG_TAG, "Nem egyezik meg a jelszó és a megerősítése!");
        } else {
            Log.i(LOG_TAG, "Regisztrált: " + userName + ", email cím: " + userEmail + ", (" + emailType + ")"+ ", jelszó: " + password + ", jelszó megerősítése: " + passwordAgain + ". ");
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedItem = adapterView.getItemAtPosition(i).toString();
        //Log.i(LOG_TAG, selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //TODO.
    }
}