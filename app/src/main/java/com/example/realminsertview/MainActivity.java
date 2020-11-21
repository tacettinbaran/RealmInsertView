package com.example.realminsertview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    EditText editTextUserName, editTextName, editTextPassword;
    RadioGroup radioGroupGender;
    RadioButton radioButtonMan, radioButtonWoman;
    Button buttonSingIn;

    String usernameText = "";
    String nameText = "";
    String passText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineRealm();
        defineComponent();
        getValuesFromComponents();


    }

    public void defineComponent() {

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        buttonSingIn = findViewById(R.id.buttonSingIn);
        radioButtonMan = findViewById(R.id.radioButtonMan);
        radioButtonWoman = findViewById(R.id.radioButtonWoman);

    }

    void defineRealm() {
        realm = Realm.getDefaultInstance();
    }

    public void getValuesFromComponents() {

        usernameText = editTextUserName.getText().toString();
        nameText = editTextName.getText().toString();
        passText = editTextPassword.getText().toString();

        buttonSingIn.setOnClickListener(v -> {

            if (TextUtils.isEmpty(editTextUserName.getText().toString())) {

                Toast.makeText(getApplicationContext(), "Username is empty. " + usernameText, Toast.LENGTH_LONG).show();

            } else if (TextUtils.isEmpty(editTextName.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Name is empty.", Toast.LENGTH_LONG).show();

            } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Password is empty.", Toast.LENGTH_LONG).show();

            } else if (!radioButtonMan.isChecked() && !radioButtonWoman.isChecked()) {
                Toast.makeText(getApplicationContext(), "Radio Button is not checked...", Toast.LENGTH_LONG).show();

            } else {

                Integer genderId = radioGroupGender.getCheckedRadioButtonId();
                RadioButton checkedGenderRadioButton = (RadioButton) findViewById(genderId);
                String genderText = checkedGenderRadioButton.getText().toString();
                writeDataToRealm(nameText, usernameText, passText, genderText);
                clearComponent();

            }

        });
    }


    public void writeDataToRealm(String nameText, String usernameText, String passText, String genderText) {
        realm.executeTransactionAsync(realm -> {

            PersonInformation personInformation = realm.createObject(PersonInformation.class);
            personInformation.setName(nameText);
            personInformation.setUsername(usernameText);
            personInformation.setPasword(passText);
            personInformation.setGender(genderText);

        }, () -> {
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();

        }, error -> {
            Toast.makeText(getApplicationContext(), "Unsuccessful!!!!", Toast.LENGTH_LONG).show();

        });
    }

    public void clearComponent() {
        editTextUserName.setText("");
        editTextName.setText("");
        editTextPassword.setText("");
        editTextUserName.requestFocus();
    }


}