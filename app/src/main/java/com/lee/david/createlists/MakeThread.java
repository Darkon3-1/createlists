package com.lee.david.createlists;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by David on 6/4/2015.
 */
public class MakeThread extends AsyncTask<FileOutputStream, Integer, Void> {

    ProgressBar bar;
    String num = null;

    public void setProgressBar(ProgressBar bar) {
        this.bar = bar;
    }

    protected Void doInBackground(FileOutputStream... files) {
       try {
            for (int i = 1; i <= 11; i++) {
                num = num.valueOf(i) + "\n";
                files[0].write(num.getBytes());
                publishProgress(i * 11);
                Thread.sleep(250);
            }
        } catch (IOException |InterruptedException e) {
            e.printStackTrace();
        }
        // Closes the files once done.
        finally {
            try {
                files[0].close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
        if (this.bar != null) {
            bar.setProgress(progress[0]);
        }
    }
}

