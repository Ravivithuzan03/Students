package com.example.studentdb.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentdb.R;
import com.example.studentdb.activity.AddStudentActivity;
import com.example.studentdb.activity.SplashActivity;
import com.example.studentdb.activity.UpdateStudentActivity;
import com.example.studentdb.db.DBHelper;
import com.example.studentdb.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentVH> {

    ArrayList<Student> students;
    Context context;


    public StudentAdapter(ArrayList<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_student,parent,false);
        StudentVH svh = new StudentVH(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentVH holder, int position) {


        final  Student s = students.get(position);
        holder.tvName.setText(s.getName());
        holder.tvAge.setText(String.valueOf(s.getAge()));
        holder.tvCourse.setText(s.getCourse());
        holder.tvMobile.setText(s.getMobile());

        holder.cardUpdate.setOnClickListener((view) -> {
            Intent intent = new Intent (context, UpdateStudentActivity.class);
           intent.putExtra("STUDENT",s);
            context.startActivity(intent);

         });



        holder.cardDelete.setOnClickListener((view) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle("Confirmation !!!!");
            builder.setMessage("Are you sure to delete" +s.getName() + "?");
            builder.setIcon( android.R.drawable.ic_menu_delete);
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    DBHelper dbHelper = new DBHelper(context);
                    int result = dbHelper.deleteStudent(s.getId());

                    if(result > 0)
                    {
                        Toast.makeText(context," Successfully Deleted",Toast.LENGTH_SHORT).show();
                        students.remove(s);
                        notifyDataSetChanged();
                    }
                    else
                    {
                        Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        });

    }


    @Override
    public int getItemCount() {
        return students.size();
    }

    class StudentVH extends RecyclerView.ViewHolder {

        TextView tvName,tvAge,tvCourse,tvMobile;
        CardView cardUpdate,cardDelete;

        public StudentVH(@NonNull View v) {
            super(v);

            tvName = v.findViewById(R.id.tvName);
            tvAge = v.findViewById(R.id.tvAge);
            tvCourse = v.findViewById(R.id.tvCourse);
            tvMobile = v.findViewById(R.id.tvMobile);

            cardUpdate = v.findViewById(R.id.cardUpdate);
            cardDelete = v.findViewById(R.id.cardDelete);


        }
    }
}
