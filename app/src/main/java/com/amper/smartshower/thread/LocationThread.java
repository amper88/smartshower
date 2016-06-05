package com.amper.smartshower.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by amper on 05/06/16.
 */
public class LocationThread extends Thread {

    @Override
    public void run() {
        try {
            while(true) {
                Thread.sleep(1000);
                Log.d("LocationThread-run", "ESTOY CORRIENDO!!!!");
                Message msgObj = handler.obtainMessage();
                Log.d("LocationThread-run", msgObj.getData().toString());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String aResponse = msg.getData().getString("message");
            Log.d("LocationThread-handle!", aResponse);

            if ((null != aResponse)) {

            }else{

            }

        }
    };

    public Handler getThreadHandler() {
        return handler;
    }

}
