package com.huanjulu.loadingdialog;/*
 * Copyright (C) 2016，上海宅米贸易有限公司
 * Author: huanjulu on 16/7/6
 * to: 
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NewThreadUiActivity extends AppCompatActivity {
    private static final int MESSAGE_UI_HANDLER = 0X11;
    private static final int MESSAGE_THREAD_HANDLER = 0X12;
    private Looper threadLooper;
    private TextView textView;
    private HandlerThread handlerThread;
    /**
     * 此handler 是UI线程的Handler
     */
    Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_UI_HANDLER:
                    textView.setText("执行更新");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.messgae_tv);
        /**
         * 通过Thread 形式更新UI
         */

//        new ThreadLooper().start();


        /**
         * 通过HandlerThread 形式更新UI
         */
        handlerThread = new HandlerThread("handlerthread");
        handlerThread.start();

        Handler threadHandler = new Handler(handlerThread.getLooper());
        uiHandler.sendEmptyMessageDelayed(MESSAGE_UI_HANDLER, 1000);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handlerThread.quit();
    }


    /**
     * 通过Thread 形式更新UI
     */

    class ThreadLooper extends Thread {
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            threadLooper = Looper.myLooper();
            /**
             * 子线程的Handler
             */
            Handler threadHandler = new Handler(threadLooper) {
                @Override
                public void handleMessage(Message message) {
                    super.handleMessage(message);

                    switch (message.what) {

                        case MESSAGE_THREAD_HANDLER:
                            uiHandler.sendEmptyMessageDelayed(MESSAGE_UI_HANDLER, 1000);
                            break;
                    }
                }
            };

            threadHandler.sendEmptyMessageDelayed(MESSAGE_THREAD_HANDLER, 1000);

            Looper.loop();
        }
    }
}
