/*
 *  Copyright (c) Assign1. CMPUT 301. University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 *
 *
 */

package zgao1_countbook.example.zgao1_countbook;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateCounter extends AppCompatActivity {
    private EditText newname;
    private EditText newinitialvalue;
    private EditText newcomment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_create);

        newname = (EditText)findViewById(R.id.newname);
        newinitialvalue = (EditText)findViewById(R.id.newinit);
        newcomment = (EditText)findViewById(R.id.newcomment);
        Button setbutton = (Button) findViewById(R.id.newbutton);

        setbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String namex = newname.getText().toString();
                String commentx = newcomment.getText().toString();
                Integer initialvaluex = Integer.parseInt(newinitialvalue.getText().toString());

                Intent i3 = getIntent();
                i3.putExtra("name" ,namex);
                i3.putExtra("initialvalue", initialvaluex);
                i3.putExtra("comment",commentx);
                setResult(Activity.RESULT_OK, i3);

                finish();

            }

        });

    }

}