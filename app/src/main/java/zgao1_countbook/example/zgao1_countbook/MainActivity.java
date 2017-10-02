


/*Class Name: MainActivity
 * Version: Version 1.0
 *  Copyright (c) Assign1. CMPUT 301. University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 * resources: https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
 *
 */

package zgao1_countbook.example.zgao1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
/**
 * Represents a MainActivity
 * @author Irene Gao
 * @version 1.0
 * @since 1.0
 * @see  AppCompatActivity
 */


/* MainActivity is for display the counter.
 * the LonelyTwitterAvtivity.java taught in class was a reference
 */
public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayAdapter<Counter> adapter;
    private ArrayList<Counter> counters = new ArrayList<>();

    private ListView oldcounterList;
    private TextView countertext;

    /*
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadFromFile();

        oldcounterList = (ListView) findViewById(R.id.counterList);
        //countertext = (TextView) findViewById(R.id.countertext);
        oldcounterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //Gson gson = new Gson();
                Intent i1 = new Intent(MainActivity.this, EditCounter.class);
                i1.putExtra("position", position);
                startActivityForResult(i1,2);
            }
        });

        Button bcreate = (Button) findViewById(R.id.bcreate);
        bcreate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i2 = new Intent(MainActivity.this, CreateCounter.class);
                startActivityForResult(i2,1);
            }
        });

    }
    /***************************************************************************************/
    /*source code from: https://stackoverflow.com/questions/37187189/mutiple-onactivityresult-method-in-android*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){

            if(resultCode == RESULT_OK){
                //do somthing
                String name = data.getStringExtra("name");
                Integer initialvalue = data.getIntExtra("initialvalue",0);
                String comment = data.getStringExtra("comment");


                counters.add(new Counter(name,initialvalue,comment));

                adapter.notifyDataSetChanged();
                //countertext.setText(Integer.toString(counters.size()));
                saveInFile();
            }

        }

        if(requestCode == 2){

            if(resultCode == RESULT_OK){
                //do somthing
                String name = data.getStringExtra("name");
                Integer currentvalue = data.getIntExtra("currentvalue",0);
                Integer initialvalue = data.getIntExtra("initialvalue",0);
                String comment = data.getStringExtra("comment");
                int position = data.getIntExtra("position", 0);
                counters.set(position, new Counter(name, currentvalue, initialvalue,comment));

                adapter.notifyDataSetChanged();
                //countertext.setText(Integer.toString(counters.size()));
                saveInFile();

            }

        }

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();



        loadFromFile();

        adapter = new ArrayAdapter<Counter>(this,
                R.layout.list, counters);
        oldcounterList.setAdapter(adapter);
    }


    /****************************************************************************/
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            counters = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {

            counters = new ArrayList<Counter>();
        } catch (IOException e) {

            throw new RuntimeException();
        }

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(counters, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {

            throw new RuntimeException();
        } catch (IOException e) {

            throw new RuntimeException();
        }
    }
}