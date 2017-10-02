/*
 *  Copyright (c) Assign1. CMPUT 301. University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 *
 * resource: https://searchcode.com/file/98904167/boloAndroid-master/bolo/app/src/main/java/com/example/bolo/activity/QuickyActivity.java
 *           https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android
 *           https://stackoverflow.com/questions/4755487/how-to-have-multiple-buttons-in-one-activity
 *
 *
 */

package zgao1_countbook.example.zgao1_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditCounter extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    private Integer cposition;
    private Intent intent;
    private Context context;
    private Counter counter;
    private EditText tname;
    private EditText tinitialvalue;
    private EditText tcurrentvalue;
    private EditText tcomment;

    private Button bsave;
    private Button bdelete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_edit);
        loadFromFile();

        //initialize the position
        Intent intent = getIntent();
        cposition = intent.getIntExtra("position", 0);

        // buttons: save button and delete button
        Button bsave = (Button) findViewById(R.id.bsave);
        Button bdelete = (Button) findViewById(R.id.bdelete);

        // edit text:

        tname = (EditText) findViewById(R.id.tname);
        tname.setText(counter.getName());
        tinitialvalue = (EditText) findViewById(R.id.tinit);
        tinitialvalue.setText(counter.getInitialvalue().toString());
        tcurrentvalue = (EditText) findViewById(R.id.tcur);
        tcurrentvalue.setText(counter.getCurrentvalue().toString());
        tcomment = (EditText) findViewById(R.id.tcomment);
        tcomment.setText(counter.getComment());

        //source from https://stackoverflow.com/questions/4755487/how-to-have-multiple-buttons-in-one-activity
        //save button
        // also from lonetwitter in lab notes

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textname = tname.getText().toString();
                Integer textinitialvalue = Integer.parseInt(tinitialvalue.getText().toString());
                Integer textcurrentvalue = Integer.parseInt(tcurrentvalue.getText().toString());
                String textcomment = tcomment.getText().toString();


                //counter.set(cposition, new Counter(textname, textcurrentvalue, textinitialvalue,textcomment));

                Intent i4 = new Intent(EditCounter.this, MainActivity.class);
                saveInFile();
                startActivity(i4);

            }

        });

        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(EditCounter.this, MainActivity.class);
                //counter.saveInFile(context);
                startActivity(i5);
            }
        });
    }


    /********************************************************************************/
        public void loadFromFile() {
            try {
                FileInputStream fis = openFileInput(FILENAME);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
                counter = gson.fromJson(in, listType);

            } catch (FileNotFoundException e) {
                //counter = new ArrayList<Counter>();
            } catch (IOException e) {
                throw new RuntimeException();
            }

        }

        public void saveInFile() {
            try {
                FileOutputStream fos = openFileOutput(FILENAME,
                        Context.MODE_PRIVATE);

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

                Gson gson = new Gson();
                gson.toJson(counter, out);
                out.flush();

                fos.close();
            } catch (FileNotFoundException e) {

                throw new RuntimeException();
            } catch (IOException e) {

                throw new RuntimeException();
            }
        }



}
