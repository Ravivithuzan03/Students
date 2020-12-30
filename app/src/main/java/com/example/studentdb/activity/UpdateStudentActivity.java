package com.example.studentdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentdb.R;
import com.example.studentdb.db.DBHelper;
import com.example.studentdb.model.Student;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText edName, edAge, edCourse, edMobile;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

      Student s = (Student) getIntent().getExtras().getSerializable("STUDENT");

        id = s.getId();
        edName = findViewById(R.id.edName);
        edAge = findViewById(R.id.edAge);
        edCourse = findViewById(R.id.edCourse);
        edMobile = findViewById(R.id.edMobile);

        edName.setText(s.getName());
        edAge.setText(String.valueOf(s.getAge()));
        edCourse.setText(s.getCourse());
        edMobile.setText(s.getMobile());
    }

    public void update(View view) {
        String name = edName.getText().toString().trim();
        String age = edAge.getText().toString().trim();
        String course = edCourse.getText().toString().trim();
        String mobile = edMobile.getText().toString().trim();

        Student s = new Student(id,name,Integer.parseInt(age),course,mobile);

        DBHelper dbHelper = new DBHelper(this);
        int result = dbHelper.updateStudent(s);

        if (result > 0 )
        {
            Toast.makeText(this,"Successfully Updated ",Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this,"Failed " + result,Toast.LENGTH_SHORT).show();
        }
    }
}