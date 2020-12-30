package com.example.studentdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentdb.R;
import com.example.studentdb.db.DBHelper;
import com.example.studentdb.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    EditText edName,edAge,edCourse,edMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        edName = findViewById(R.id.edName);
        edAge = findViewById(R.id.edAge);
        edCourse = findViewById(R.id.edCourse);
        edMobile = findViewById(R.id.edMobile);
    }

    public void showAll (View view) {
        startActivity(new Intent(AddStudentActivity.this, StudentListActivity.class));
    }
    public void save (View view) {
        String name = edName.getText().toString().trim();
        String age = edAge.getText().toString().trim();
        String course = edCourse.getText().toString().trim();
        String mobile = edMobile.getText().toString().trim();

        DBHelper dbHelper = new DBHelper(AddStudentActivity.this);

        Student s = new Student(name,Integer.parseInt(age),course,mobile);


        long result = dbHelper.addStudent(s);

        if (result > 0 )
        {
            Toast.makeText(this,"Successfully Add Student Details ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Failed " + result,Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {
    }
}