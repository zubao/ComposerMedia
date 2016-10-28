package com.joyodream.mediacomposer;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private android.widget.TextView composer;
    private android.widget.RelativeLayout activitymain;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activitymain = (RelativeLayout) findViewById(R.id.activity_main);
        this.composer = (TextView) findViewById(R.id.composer);
        mHandlerThread.start();
        mHandler    = new Handler(mHandlerThread.getLooper());
        PermissionUtil.checkWritePermission(this);
        composer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        IMediaComposer composer = new MediaNativeComposer();
//                        int num = composer.composer("/sdcard/mc/in_audio.mp3", "/sdcard/mc/in_video.mp4", 1);
//                        String mp3  = "/sdcard/Android/data/com.joyodream.mediacomposer/mc/huoyuanjia.mp3";
//                        String mp4  = "/sdcard/Android/data/com.joyodream.mediacomposer/mc/cuc_ieschool.ts";
//                        String mp3  = "/sdcard/mc/huoyuanjia.mp3";
//                        String mp4  = "/sdcard/mc/cuc_ieschool.ts";
                        String mp3  = "/sdcard/mc/in_audio.mp3";
                        String mp4  = "/sdcard/mc/in_video.mp4";
                        String outFile  = "/sdcard/mc/out.mp4";
                        if(!new File(mp3).exists()){
                            Log.d("tag", "a file not exist");
                            return;
                        }else{
                            File file = new File(mp3);
                            try {
                                FileInputStream fis = new FileInputStream(file);
                                byte[] buffer   = new byte[1024];
                                int size    = fis.read(buffer);
                                Log.d("tag", "a file not exist size : "+ size);

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        if(!new File(mp4).exists()){
                            Log.d("tag", "v file not exist");
                            return;
                        }
                        int num = composer.composer(mp3, mp4, outFile);

                        Toast.makeText(MainActivity.this, "num: is "+ num , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


    HandlerThread mHandlerThread    = new HandlerThread("media composer");


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults == null || grantResults.length <= 0) {
            return;
        }
        switch (requestCode) {
            case PermissionUtil.PERMISSION_REQUEST_CODE_INIT:
                // 如果读取手机状态和写SDCARD被授权
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                }else {

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
