package com.thiagoretondar.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thiagoretondar.addressbook.dao.StudentDao;
import com.thiagoretondar.addressbook.model.Student;

import java.io.Serializable;
import java.util.zip.Inflater;

public class FormActivity extends AppCompatActivity {

    private FormHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        helper = new FormHelper(this);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        if (student != null) {
            helper.setForm(student);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_ok:

                Student student = helper.getStudent();

                StudentDao studentDao = new StudentDao(this);

                if (student.getId() != null) {
                    studentDao.update(student);
                } else {
                    studentDao.insert(student);
                }

                studentDao.close();

                Toast.makeText(FormActivity.this, "Student " + student.getFirstName() + " saved",
                        Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
