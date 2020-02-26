package acs.tasklist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ObjectOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

// This class maintains and displays a list of all tasks entered by the user, sorted in order of
// earliest due date first
public class TaskList extends Activity {
    private ListView mListView;
    SharedPreferences shared;
    ArrayList<String> list;
    ArrayAdapter adapter;

    // Reads data from DB, loads into array, listview displays to user on screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);
        Intent intent = getIntent();
        if(intent.hasExtra("bool")){
            Toast.makeText(getApplicationContext(), "Task added successfully",
                    Toast.LENGTH_SHORT).show();
        }
        shared = getSharedPreferences("tasks", Context.MODE_PRIVATE);
        mListView = findViewById(R.id.lvx);
        list = new ArrayList<String>();
        for (int i=1; i<=shared.getInt("next",1); i++){
            String item;
            item = shared.getString(Integer.toString(i),"");
            if(!item.equals("")) list.add(item);
        }

        class SortByDate implements Comparator<String>{
            String d1 = new String();
            String d2 = new String();
            Date date1;
            Date date2;

            public void toDate() throws Exception{
                date1=new SimpleDateFormat("MM-dd-yyyy").parse(d1);
                date2=new SimpleDateFormat("MM-dd-yyyy").parse(d2);
            }

            public int compare(String a, String b){
                d1 = a.substring(5, 15);
                d2 = b.substring(5, 15);
                try {
                    toDate();
                }
                catch (Exception e){
                    // never will throw exception
                }
                if (date1.before(date2)) return -1;
                else return 1;
            }
        }
        Collections.sort(list, new SortByDate());
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        //if(i.hasExtra("OtherO")||i.hasExtra("EventO")||i.hasExtra("ShoppingO")||i.hasExtra("HomeworkO")){list.add(i.getStringExtra("OtherO"));}
        mListView.setAdapter(adapter);

        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        // ListView on item selected listener, user can delete clicked on items
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                builder.setTitle("Complete Task")
                        .setMessage("Are you sure that you want to complete the following task?\n\n"+list.get(position))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                shared = getSharedPreferences("tasks", Context.MODE_PRIVATE);
                                SharedPreferences.Editor e = shared.edit();
                                for(int i=0; i<=shared.getInt("next", 1); i++){
                                    if(list.get(position).equals(shared.getString(Integer.toString(i),""))){
                                        e.remove(Integer.toString(i));
                                        e.commit();
                                        break;
                                    }
                                }
                                list.remove(position);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Task completed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    public void goBack(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // Completes all tasks that the user has in their list
    public void clear(View v){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        builder.setTitle("Complete All Tasks")
                .setMessage("Are you sure that you want to complete all of your tasks?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        shared = getSharedPreferences("tasks", Context.MODE_PRIVATE);
                        SharedPreferences.Editor e = shared.edit();
                        e.clear();
                        e.commit();
                        Toast.makeText(getApplicationContext(), "All tasks were completed",
                                Toast.LENGTH_SHORT).show();
                        list.clear();
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}

