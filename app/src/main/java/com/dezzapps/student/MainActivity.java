package com.dezzapps.student;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.dezzapps.student.adapters.StudentAdapter;
import com.dezzapps.student.data.StudentDatabase;
import com.dezzapps.student.entity.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private StudentDatabase studentDatabase;
    private ArrayList<Student> students;
    private StudentAdapter studentAdapter;

    public final int NEW_STUDENT_ACTYVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        studentAdapter = new StudentAdapter();

        recyclerView.setAdapter(studentAdapter);


        studentDatabase = Room.databaseBuilder(getApplicationContext(), StudentDatabase.class, "studentDb")
                .build();


        loadData();


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Student studentToDelete = students.get(viewHolder.getAdapterPosition());
                deleteStudent(studentToDelete);

            }
        }).attachToRecyclerView(recyclerView);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewStudentActivity.class);
                startActivityForResult(intent, NEW_STUDENT_ACTYVITY_REQUEST_CODE);
            }
        });
    }

    private void loadData() {

        new getAllStudentAsyncTask().execute();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_STUDENT_ACTYVITY_REQUEST_CODE && resultCode == RESULT_OK){
            String name = data.getStringExtra("NAME");
            String email = data.getStringExtra("EMAIL");
            String country = data.getStringExtra("Country");

            SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("DD,MM,YYY");
            String currentDate = simpleDateFormat.format(new Date());

            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setCountry(country);

            addNewStudent(student);
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

    private void  addNewStudent(Student student){
        new AddNewStudentAsyncTask().execute(student);
    }

    private void  deleteStudent(Student student){
        new DeleteStudentAsyncTask().execute(student);
    }

    private class  AddNewStudentAsyncTask extends  AsyncTask<Student, Void, Void>{

        @Override
        protected Void doInBackground(Student... students) {
            studentDatabase.getStudentDao().insert(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }


    private class  getAllStudentAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            students = (ArrayList<Student>) studentDatabase.getStudentDao().getAllStucent();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            studentAdapter.setStudents(students );
        }
    }

    private class  DeleteStudentAsyncTask extends  AsyncTask<Student, Void, Void>{

        @Override
        protected Void doInBackground(Student... students) {
            studentDatabase.getStudentDao().deleteStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }
}
