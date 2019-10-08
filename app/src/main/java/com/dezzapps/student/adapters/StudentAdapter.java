package com.dezzapps.student.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dezzapps.student.R;
import com.dezzapps.student.entity.Student;
import com.dezzapps.student.holder.StudentItem;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentItem> {


    private ArrayList<Student> students;

    @NonNull
    @Override
    public StudentItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item, parent, false);

        return new StudentItem(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentItem holder, int position) {

        Student currentStuden = students.get(position);

        holder.bind(currentStuden);

    }

    @Override
    public int getItemCount() {
        if(students != null){
            return students.size();
        }else {
            return  0;
        }

    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }
}
