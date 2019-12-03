package com.fju.water2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getSimpleName() ;
    private EditText edmonth;
    boolean isNext = false;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edmonth = findViewById(R.id.month);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Switch sw =findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isNext = isChecked;
                TextView text = findViewById(R.id.type);
                text.setText(isNext ? getString(R.string.every_other_month) : getString(R.string.monthly));
            }
        });

        //spinner
        Spinner cities = findViewById(R.id.spinner);
        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, getResources().getStringArray(R.array.cities)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter();
            }
        });
    }
    public void counter() {
        Integer month = Integer.parseInt(edmonth.getText().toString());
        double count = 0;
        String setTitle = "";
        String message = "";
        setTitle = "每月抄表費用";
        DialogInterface.OnCancelListener listener = new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                edmonth.setText("");
            }
        };
        if (1 <= month && month <= 10) {
            count = month * 7.35;
            message = "費用=" + count;
            listener = null;
        }
        if (11 <= month && month <= 30) {
            count = month * 9.45 - 21;
            message = "費用=" + count;
            listener = null;
        }
        if (31 <= month && month <= 50) {
            count = month * 11.55 - 84;
            message = "費用=" + count;
            listener = null;
        }
        if (51 <= month) {
            count = month * 12.075 - 110.25;
            message = "費用=" + count;
            listener = null;
        }
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra(getString(R.string.extra_fee), count);
        startActivity(intent);
        /*if (TextUtils.isEmpty(String.valueOf(month))) {
            Integer next = Integer.parseInt(ednext.getText().toString());
            setTitle = "隔月抄表費用";
            if (1 <= month && month <= 20) {
                count = month * 7.35;
                message = "費用=" + count;
                listener = null;
            }
            if (21 <= month && month <= 60) {
                count = month * 9.45 - 42;
                message = "費用=" + count;
                listener = null;
            }
            if (61 <= month && month <= 100) {
                count = month * 11.55 - 168;
                message = "費用=" + count;
                listener = null;
            }
            if (101 <= month) {
                count = month * 12.075 - 220.5;
                message = "費用=" + count;
                listener = null;
            }
        }*/
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(setTitle)
                .setMessage(message)
                .setPositiveButton((R.string.ok), null)
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

