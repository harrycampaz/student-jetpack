package com.dezzapps.student.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.dezzapps.student.entity.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insert(Student student);

    @Query("select * from student")
    List<Student>getAllStucent();

    @Delete
    void deleteStudent(Student student);


}
