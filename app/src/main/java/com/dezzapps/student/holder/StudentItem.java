package com.dezzapps.student.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dezzapps.student.R;
import com.dezzapps.student.databinding.StudentListItemBinding;
import com.dezzapps.student.entity.Student;

public class StudentItem extends RecyclerView.ViewHolder {

    private TextView name, email, country, registeredTime;
    public StudentListItemBinding studentListItemBinding;

    public StudentItem(@NonNull StudentListItemBinding studentListItemBinding) {
        super(studentListItemBinding.getRoot());

        this.studentListItemBinding = studentListItemBinding;

      //  name = itemView.findViewById(R.id.textName);
       // email = itemView.findViewById(R.id.textEmail);
       // country = itemView.findViewById(R.id.textCountry);
       // registeredTime = itemView.findViewById(R.id.textRegistered);





    }

   /* public  void bind(final Student student){
        name.setText(student.getName());
        email.setText(student.getEmail());
        country.setText(student.getCountry());
        registeredTime.setText(student.getRegisteredTime());
    }*/
}
