package com.intellilogics.studentdatabaseapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.intellilogics.studentdatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abidroid on 12/27/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "intell.db";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COURSE = "course";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_TOTAL_FEE = "total_fee";
    private static final String KEY_FEE_PAID = "fee_paid";

    private static final String TABLE_STUDENT = "tbl_student";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "( "
                                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                    + KEY_NAME + " TEXT NOT NULL,"
                                    + KEY_COURSE + " TEXT NOT NULL,"
                                    + KEY_CONTACT + " TEXT NOT NULL,"
                                    + KEY_TOTAL_FEE + " INTEGER NOT NULL,"
                                    + KEY_FEE_PAID + " INTEGER NOT NULL )" ;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public long addStudent(Student student) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, student.getName() );
        values.put(KEY_COURSE, student.getCourse());
        values.put(KEY_CONTACT, student.getContact());
        values.put(KEY_TOTAL_FEE, student.getTotalFee());
        values.put(KEY_FEE_PAID, student.getFeePaid());


        return db.insert(TABLE_STUDENT, null, values);
    }

    public List<Student> getAllStudents() {

        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_STUDENT, null);

        if(cursor.moveToFirst())
        {
            do
            {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String course = cursor.getString(2);
                String contact = cursor.getString(3);
                int totalFee =cursor.getInt(4);
                int feePaid =cursor.getInt(5);

                Student student = new Student(id, name, course, contact, totalFee, feePaid);
                studentList.add(student);

            }while( cursor.moveToNext());
        }

        return studentList;
    }

    public int updateStudent(Student student) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_COURSE, student.getCourse());
        values.put(KEY_CONTACT, student.getContact());
        values.put(KEY_TOTAL_FEE, student.getTotalFee());
        values.put(KEY_FEE_PAID, student.getFeePaid());

        return db.update(TABLE_STUDENT, values, "id=?", new String[]{String.valueOf(student.getId())});
    }

    public int deleteStudent(int sid) {

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_STUDENT, "id=?", new String[]{String.valueOf(sid)});
    }
}















