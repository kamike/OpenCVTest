package com.example.wangtao.opencvproject;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by wangtao on 2018/3/13.
 */

public class ImgprocMethod {
    public static Bitmap getImgBolor1(Mat src) {
        Mat grayImage = new Mat();
        Mat target = new Mat();

        Imgproc.cvtColor(src, grayImage, Imgproc.COLOR_BGR2GRAY);
        Imgproc.Canny(grayImage, target, 10, 100);
        return getBitmapRes(target);
    }


    public static Bitmap convertGray0(Mat src) {
        Mat temp = new Mat();
        Imgproc.cvtColor(src, temp, Imgproc.COLOR_BGR2GRAY);
        Bitmap resBmp = getBitmapRes(temp);
        return resBmp;
    }





    @NonNull
    private static Bitmap getBitmapRes(Mat target) {
        Bitmap resBmp = Bitmap.createBitmap(target.width(), target.height(), Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(target, resBmp);
        return resBmp;
    }
}
