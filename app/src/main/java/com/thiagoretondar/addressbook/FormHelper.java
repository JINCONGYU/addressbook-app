package com.thiagoretondar.addressbook;

import android.widget.EditText;
import android.widget.RatingBar;

import com.thiagoretondar.addressbook.model.Student;

/**
 * Created by thiagoretondar on 7/4/16.
 */
public class FormHelper {

    private final EditText firstNameField;
    private final EditText lastNameField;
    private final EditText nicknameField;
    private final EditText addressField;
    private final EditText phoneField;
    private final EditText websiteField;
    private final RatingBar ratingField;

    public FormHelper(FormActivity activity) {
        firstNameField = (EditText) activity.findViewById(R.id.form_firstname);
        lastNameField = (EditText) activity.findViewById(R.id.form_lastname);
        nicknameField = (EditText) activity.findViewById(R.id.form_nickname);
        addressField = (EditText) activity.findViewById(R.id.form_address);
        phoneField = (EditText) activity.findViewById(R.id.form_phone);
        websiteField = (EditText) activity.findViewById(R.id.form_website);
        ratingField = (RatingBar) activity.findViewById(R.id.form_rating);
    }

    public Student getStudent() {
        Student student = new Student();
        student.setFirstName(firstNameField.getText().toString());
        student.setLastName(lastNameField.getText().toString());
        student.setNickname(nicknameField.getText().toString());
        student.setAddress(addressField.getText().toString());
        student.setPhone(phoneField.getText().toString());
        student.setWebsite(websiteField.getText().toString());
        student.setRate(Double.valueOf(ratingField.getRating()));
        return student;
    }
}
