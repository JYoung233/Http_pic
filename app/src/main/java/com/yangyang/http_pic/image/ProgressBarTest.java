package com.yangyang.http_pic.image;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.yangyang.http_pic.R;

/**
 * Created by asus on 2016/3/1.
 * 使用AsyncTask 模拟进度条更新
 */
public class ProgressBarTest extends Activity {
    private ProgressBar progressBar=null;
    private MyAsyncTask mtask;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        progressBar= (ProgressBar) findViewById(R.id.pg);
        mtask=new MyAsyncTask();
        mtask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mtask!=null&&mtask.getStatus()==AsyncTask.Status.RUNNING){
            mtask.cancel(true);//cancel方法只是将对应的asynctask 标记为cancel状态，并不是真正的取消线程的执行

        }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(isCancelled()){
                return;
            }
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {
            //模拟进度更新
            for (int i = 0; i <100; i++) {
                if(isCancelled()){
                    break;
                }
                publishProgress(i);
            }
            try {
                Thread.sleep(300);//增加睡眠时间

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
