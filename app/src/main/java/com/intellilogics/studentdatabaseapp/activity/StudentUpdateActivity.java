package com.intellilogics.studentdatabaseapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.intellilogics.studentdatabaseapp.R;
import com.intellilogics.studentdatabaseapp.database.DBHelper;
import com.intellilogics.studentdatabaseapp.model.Student;

public class StudentUpdateActivity extends AppCompatActivity {

    EditText edName, edContact, edTotalFee, edFeePaid;
    Spinner spinnerCourse;
    String[] courses = { "C++", "Java", "Android", "Swift", "iOS", "Python"};
    ArrayAdapter<String> courseAdapter;
    DBHelper dbHelper;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);

        dbHelper = new DBHelper(this);
        edName = findViewById(R.id.edName);
        edContact = findViewById(R.id.edContact);
        edTotalFee = findViewById(R.id.edTotalFee);
        edFeePaid = findViewById(R.id.edFeePaid);

        Student student = (Student) getIntent().getExtras().getSerializable("STUDENT");
        id = student.getId();
        edName.setText(student.getName());
        edContact.setText(student.getContact());
        edTotalFee.setText(String.valueOf(student.getTotalFee()));
        edFeePaid.setText(String.valueOf(student.getFeePaid()));

        spinnerCourse = findViewById(R.id.spinnerCourse);
        courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);
        spinnerCourse.setAdapter(courseAdapter);


        for (int i = 0; i < courses.length; i++)
        {
            if( courses[i].equals(student.getCourse()))
            {
                spinnerCourse.setSelection(i);
                break;
            }
        }
    }

    public void update(View view) {

        String name = edName.getText().toString();
        String course = spinnerCourse.getSelectedItem().toString();
        String contact = edContact.getText().toString();
        String strTotalFee = edTotalFee.getText().toString();
        String strFeePaid = edFeePaid.getText().toString();

        int totalFee = Integer.parseInt(strTotalFee);
        int feePaid = Integer.parseInt(strFeePaid);

        Student student = new Student(id, name, course, contact, totalFee, feePaid);

        int result = dbHelper.updateStudent(student);

        if( result > 0)
        {
            Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

    }
}
