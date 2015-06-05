package com.lee.david.createlists;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private final static String fileName = "/numbers.txt";
    private ArrayAdapter<String> items;

    public void createFile(View view) {
        //create a file called numbers
       FileOutputStream file = null;
        try {
            file = openFileOutput("numbers.txt", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final ProgressBar prgrun = (ProgressBar) findViewById(R.id.progressBar);
        prgrun.setProgress(0);
        MakeThread create = new MakeThread();
        create.setProgressBar(prgrun);
        create.execute(file);
    }

    public void loadFile(View view) {
        FileInputStream openNum = null;

       try {
            openNum = openFileInput("numbers.txt");
            } catch (IOException e) {
            e.printStackTrace();
        }

        final ProgressBar prgrun = (ProgressBar) findViewById(R.id.progressBar);
        prgrun.setProgress(0);
        Context test = (Context) this;
        Loader load = new Loader(prgrun, test);
        load.execute(openNum);
        load.display();

    }


    public void stopLoad(View view) {
        TextView display = (TextView) findViewById(R.id.disNum);
        if (display != null) {
            display.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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