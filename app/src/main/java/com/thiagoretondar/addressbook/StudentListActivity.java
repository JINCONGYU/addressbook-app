package com.thiagoretondar.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.thiagoretondar.addressbook.dao.StudentDao;
import com.thiagoretondar.addressbook.model.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button newContactBtn = (Button) findViewById(R.id.student_new_btn);
        newContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToFormIntent = new Intent(StudentListActivity.this, FormActivity.class);
                startActivity(goToFormIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadStudentList();
    }

    private void loadStudentList() {
        StudentDao studentDao = new StudentDao(this);
        List<Student> students = studentDao.findAllStudents();

        ListView studentList = (ListView) findViewById(R.id.student_list);
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        studentList.setAdapter(adapter);
    }
}
