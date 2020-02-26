package acs.tasklist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

// Menu page to select type of task
public class AddTask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Button b = (Button) findViewById(R.id.button2);
        b.setBackgroundColor(Color.BLACK);
        b.setTextColor(Color.WHITE);

        b = findViewById(R.id.button3);
        b.setBackgroundColor(Color.BLACK);
        b.setTextColor(Color.WHITE);

        b = findViewById(R.id.button4);
        b.setBackgroundColor(Color.BLACK);
        b.setTextColor(Color.WHITE);

        b = findViewById(R.id.button5);
        b.setBackgroundColor(Color.BLACK);
        b.setTextColor(Color.WHITE);
    }

    // Goes to home
    public void goBack(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // Goes to shopping page
    public void toShopping(View v)
    {
        Intent i = new Intent(this, Shopping.class);
        startActivity(i);
    }

    // Goes to homework page
    public void toHomework(View v)
    {
        Intent i = new Intent(this, Homework.class);
        startActivity(i);
    }

    // Goes to event page
    public void ToEvent(View v)
    {
        Intent i = new Intent(this, Event.class);
        startActivity(i);
    }

    // Goes to other page
    public void ToOther(View v)
    {
        Intent i = new Intent(this, Other.class);
        startActivity(i);
    }
}
