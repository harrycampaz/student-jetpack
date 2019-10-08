package com.dezzapps.student;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.xml.transform.Result;

public class AddNewStudentActivity extends AppCompatActivity {

    private EditText name, email, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        country = findViewById(R.id.editTextCountry);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(name.getText())){
                    Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_LONG).show();
                }else {
                    String nameAux = name.getText().toString();
                    String emailAux = email.getText().toString();
                    String countryAux = country.getText().toString();

                    Intent intent = new Intent();

                    intent.putExtra("NAME", nameAux);
                    intent.putExtra("EMAIL", emailAux);
                    intent.putExtra("COUNTRY", countryAux);

                    setResult(RESULT_OK, intent);
                    finish();

                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
