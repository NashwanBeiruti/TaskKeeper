package acs.tasklist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

// Handles process of adding a shopping object task to task list
public class Shopping extends Activity {
    private ListView list;
    ShoppingO shopping = new ShoppingO();
    SharedPreferences shared;

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_t);
        shared = getSharedPreferences("tasks", Context.MODE_PRIVATE);
        list = findViewById(R.id.lvx);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                shopping.list);
        list.setAdapter(adapter);
    }

    // Adds item to arraylist of ShoppingO object
    public void addItem(View v)
    {
        String item;
        EditText ed = (EditText) findViewById(R.id.EditTextItems2);
        item = ed.getText().toString();
        if (!item.equals("")){
            shopping.addItem(item);
            adapter.notifyDataSetChanged();
            ed.setText("");
        }
    }

    public void cancel(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // Gets all input info from user, sets values of shopping object using it, stores in DB and adds it
    public void addShopping(View v)
    {
        EditText editText = (EditText) findViewById(R.id.EditTextName2);
        String description = editText.getText().toString();
        DatePicker datepicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        int   day  = datepicker.getDayOfMonth();
        int   month= datepicker.getMonth();
        int   year = datepicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = sdf.format(calendar.getTime());
        // back to date: Date date = sdf.parse(formatedDate);
        shopping.setDesc(description);
        shopping.setDueDate(formattedDate);
        if(!description.trim().isEmpty() && !shopping.list.isEmpty()) {
            Intent i = new Intent(this, TaskList.class);
            i.putExtra("bool", 1);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString(Integer.toString(shared.getInt("next", 1)), shopping.formatInfo());
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
