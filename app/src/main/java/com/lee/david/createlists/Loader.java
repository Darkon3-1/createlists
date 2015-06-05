package com.lee.david.createlists;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by David on 6/4/2015.
 */
public class Loader extends AsyncTask<FileInputStream, Integer, Void> {

    private ProgressBar bar;
    protected MainActivity context;
    private ArrayList<String> numList = new ArrayList<String>();

    Loader(ProgressBar b, Context context){
        this.bar = b;
        this.context = (MainActivity) context;
    }
    protected Void doInBackground(FileInputStream... files){


        try {
            BufferedReader num = new BufferedReader(new InputStreamReader(files[0]));
            String nums = num.readLine();

            int progress = 0;
            while (nums != null) {
                numList.add(nums);
                publishProgress(progress++ * 11);
                nums = num.readLine();
                Thread.sleep(250);
            }


        } catch (IOException |InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
        if (this.bar != null) {
            bar.setProgress(progress[0]);
        }
    }

    public void display() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView test = (TextView) context.findViewById(R.id.disNum);
                for (int i = 0; i < numList.size(); i++)
                {
                    test.append(numList.get(i) + "\n");
                }
            }
        });
    }
}

