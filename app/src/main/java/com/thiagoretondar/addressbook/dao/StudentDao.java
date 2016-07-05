package com.thiagoretondar.addressbook.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thiagoretondar.addressbook.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagoretondar on 7/4/16.
 */
public class StudentDao extends SQLiteOpenHelper {

    public StudentDao(Context context) {
        super(context, "addressbook", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE students (" +
                "id INTEGER PRIMARY KEY, " +
                "firstName TEXT NOT NULL, " +
                "lastName TEXT NOT NULL, " +
                "nickName TEXT NOT NULL, " +
                "address TEXT NOT NULL, " +
                "phone TEXT NOT NULL, " +
                "website TEXT NOT NULL, " +
                "rate REAL)";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE students";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insert(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("firstName", student.getFirstName());
        values.put("lastName", student.getLastName());
        values.put("nickName", student.getNickname());
        values.put("address", student.getAddress());
        values.put("phone", student.getPhone());
        values.put("website", student.getWebsite());
        values.put("rate", student.getRate());

        db.insert("students", null, values);
    }

    public List<Student> findAllStudents() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * FROM students";

        List<Student> students = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Student student = new Student();
            student.setId(cursor.getLong(cursor.getColumnIndex("id")));
            student.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
            student.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
            student.setNickname(cursor.getString(cursor.getColumnIndex("nickName")));
            student.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            student.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            student.setWebsite(cursor.getString(cursor.getColumnIndex("website")));
            student.setRate(cursor.getDouble(cursor.getColumnIndex("rate")));

            students.add(student);
        }
        cursor.close();

        return students;
    }
}
