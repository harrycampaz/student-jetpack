package com.dezzapps.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dezzapps.student.databinding.ActivityAddNewStudentBinding;
import com.dezzapps.student.databinding.ActivityMainBinding;
import com.dezzapps.student.entity.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.xml.transform.Result;

public class AddNewStudentActivity extends AppCompatActivity {


    private ActivityAddNewStudentBinding activityAddNewStudentBinding;

    Student student;
    private AddNewStudentActivityClickHandler addNewStudentActivityClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        student = new Student();

        activityAddNewStudentBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student);

        activityAddNewStudentBinding.setStudent(student);

        addNewStudentActivityClickHandler = new AddNewStudentActivityClickHandler(this);

        activityAddNewStudentBinding.setClickHandler(addNewStudentActivityClickHandler);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public class  AddNewStudentActivityClickHandler{
        Context context;

        public AddNewStudentActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onSubmitClicked(View view){

            if(TextUtils.isEmpty(student.getName())){
                Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_LONG).show();
            }else {
                Intent intent = new Intent();

                intent.putExtra("NAME", student.getName());
                intent.putExtra("EMAIL", student.getEmail());
                intent.putExtra("COUNTRY", student.getCountry());

                setResult(RESULT_OK, intent);
                finish();

            }
        }
    }


}
