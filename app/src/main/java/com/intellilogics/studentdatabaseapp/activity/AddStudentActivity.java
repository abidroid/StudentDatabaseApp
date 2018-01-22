package com.intellilogics.studentdatabaseapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.intellilogics.studentdatabaseapp.R;
import com.intellilogics.studentdatabaseapp.database.DBHelper;
import com.intellilogics.studentdatabaseapp.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    Spinner spinnerCourse;
    String[] courses = { "C++", "Java", "Android", "Swift", "iOS", "Python"};
    ArrayAdapter<String> courseAdapter;

    EditText edName, edContact, edTotalFee, edFeePaid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        spinnerCourse = findViewById(R.id.spinnerCourse);
        courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        spinnerCourse.setAdapter(courseAdapter);

        edName = findViewById(R.id.edName);
        edContact = findViewById(R.id.edContact);
        edTotalFee = findViewById(R.id.edTotalFee);
        edFeePaid = findViewById(R.id.edFeePaid);
    }

    public void process(View view) {

        switch (view.getId())
        {
            case R.id.btnSave:

                String name = edName.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    edName.setError("Please provide Name");
                    return;
                }


                String course = spinnerCourse.getSelectedItem().toString();

                String contact = edContact.getText().toString();
                if( TextUtils.isEmpty(contact))
                {
                    edContact.setError("Please provide contact number");
                    return;
                }
                String strTotalFee = edTotalFee.getText().toString();
                String strFeePaid = edFeePaid.getText().toString();

                int totalFee = Integer.parseInt(strTotalFee);
                int feePaid = Integer.parseInt(strFeePaid);
                
                DBHelper dbHelper = new DBHelper(this);

                Student student = new Student(name, course, contact, totalFee, feePaid);

                long result = dbHelper.addStudent(student);
                
                if( result != -1 )
                {
                    Toast.makeText(this, "Student Saved", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
                
                break;
            case R.id.btnShowAll:

                startActivity(new Intent(AddStudentActivity.this, StudentListActivity.class));
                break;
        }
    }
}
