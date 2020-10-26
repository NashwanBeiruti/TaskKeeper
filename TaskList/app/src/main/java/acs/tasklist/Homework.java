package acs.tasklist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// Handles process of adding a homework object task to task list
public class Homework extends Activity {

    HomeworkO homework = new HomeworkO();
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework_t);
        shared = getSharedPreferences("tasks", Context.MODE_PRIVATE);
    }

    public void cancel(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // Gets all input info from user, sets values of homework object using it, stores in DB and adds it
    public void addHomework(View v)
    {
        EditText editText = (EditText) findViewById(R.id.EditTextName2);
        EditText ed = (EditText) findViewById(R.id.EditTextItems2);
        String description = editText.getText().toString();
        String subject = ed.getText().toString();
        DatePicker datepicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        int   day  = datepicker.getDayOfMonth();
        int   month= datepicker.getMonth();
        int   year = datepicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = sdf.format(calendar.getTime());
        // back to date: Date date = sdf.parse(formatedDate);
        homework.setDesc(description);
        homework.setDueDate(formattedDate);
        homework.setSubject(subject);
        if(!description.trim().isEmpty() && !subject.trim().isEmpty()) {
            Intent i = new Intent(this, TaskList.class);
            i.putExtra("bool", 1);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString(Integer.toString(shared.getInt("next", 1)), homework.formatInfo());
            editor.putInt("next", shared.getInt("next", 1)+1);
            editor.commit();
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "All fields must be filled out",
                    Toast.LENGTH_LONG).show();
        }
    }
}
