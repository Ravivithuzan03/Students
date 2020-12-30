package com.example.studentdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.studentdb.model.Student;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tbl_student ( id  INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, course TEXT, mobile TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_student");
        onCreate(sqLiteDatabase);
    }


    public long addStudent(Student s) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
       cv.put("name", s.getName());
       cv.put("age", s.getAge());
       cv.put("course", s.getCourse());
       cv.put("mobile", s.getMobile());

       return db.insert("tbl_student",null,cv);
    }

    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> students = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor =  db.rawQuery("SELECT * from tbl_student",null);

        if(cursor.moveToFirst())
        {
            do{

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String course = cursor.getString(3);
                String mobile = cursor.getString(4);


                Student s = new Student(id, name, age, course, mobile);
                students.add(s);
            }while ( cursor.moveToNext());
        }

        return students;

    }

    public int deleteStudent(int id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("tbl_student", "id=?", new String[]{ String.valueOf(id) });

    }

    public int updateStudent(Student s) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", s.getName());
        cv.put("age", s.getAge());
        cv.put("course", s.getCourse());
        cv.put("mobile", s.getMobile());

      return db.update("tbl_student",cv, "id=?",new String[]{String.valueOf(s.getId())});
    }
}
