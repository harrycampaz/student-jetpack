package com.dezzapps.student.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dezzapps.student.entity.Student;

@Database(entities = {Student.class}, version =  1)
public abstract class StudentDatabase extends RoomDatabase {


    public  abstract  StudentDao getStudentDao();

}
