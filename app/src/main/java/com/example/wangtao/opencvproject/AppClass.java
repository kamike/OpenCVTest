package com.example.wangtao.opencvproject;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import org.opencv.android.OpenCVLoader;

/**
 * Created by wangtao on 2018/3/13.
 */

public class AppClass extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        OpenCVLoader.initDebug();
    }
}
