package com.fju.water2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edmonth;
    private EditText ednext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edmonth = findViewById(R.id.month);
        ednext = findViewById(R.id.next);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void counter(View view){
        Integer month = Integer.parseInt(edmonth.getText().toString());
        double count ;
        String setTitle ="";
        String message ="";
        setTitle = "每月抄表費用";
        DialogInterface.OnCancelListener listener = new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                edmonth.setText("");
                ednext.setText("");
            }
        };
        if(1 <= month && month <= 10){
            count = month*7.35;
            message = "費用="+ count;
            listener =null;
        }if(11 <= month && month <= 30){
            count = month*9.45-21;
            message = "費用="+ count;
            listener =null;
        }if(31 <= month && month <= 50){
            count = month*11.55-84;
            message = "費用="+ count;
            listener =null;
        }if(51 <= month ){
            count = month*12.075-110.25;
            message = "費用="+ count;
            listener =null;
        }
        if(String.valueOf(month).isEmpty()){
            setTitle = "隔月抄表費用";
            if(1 <= month && month <= 20){
                count = month*7.35;
                message = "費用="+ count;
                listener =null;
            }if(21 <= month && month <= 60){
                count = month*9.45-42;
                message = "費用="+ count;
                listener =null;
            }if(61 <= month && month <= 100){
                count = month*11.55-168;
                message = "費用="+ count;
                listener =null;
            }if(101 <= month ){
                count = month*12.075-220.5;
                message = "費用="+ count;
                listener =null;
            }
        }
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(setTitle)
                .setMessage(message)
                 .setPositiveButton("OK",null)
                 .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
