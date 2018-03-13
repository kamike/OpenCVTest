package com.example.wangtao.opencvproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.IOException;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    private Button btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        ScreenUtils.setFullScreen(this);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv_main);
        btnSelect = findViewById(R.id.btn_select_img);
        try {
            bmpSrc = BitmapFactory.decodeStream(getResources().getAssets().open("test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bmpSrc);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(false)
                        .start(MainActivity.this, PhotoPicker.REQUEST_CODE);
            }
        });
    }


    private Bitmap bmpSrc;

    public void onclickGenerate(View view) {
        Mat mat = new Mat();
        Utils.bitmapToMat(bmpSrc, mat);
        switch (selectMethodIndex) {
            case 0:
                imageView.setImageBitmap(ImgprocMethod.convertGray0(mat));
                break;
            case 1:
                imageView.setImageBitmap(ImgprocMethod.getImgBolor1(mat));
                break;
            case 2:
                break;
            case 3:
                break;
            default:

        }


    }

    public void onclickReset(View view) {
        imageView.setImageBitmap(bmpSrc);
    }

    private String[] array = {"灰度图", "获取边框"};
    private int selectMethodIndex = 0;

    public void onclickSelectMethod(View view) {
        new AlertDialog.Builder(this).setItems(array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectMethodIndex = which;

            }
        }).setPositiveButton("cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (!photos.isEmpty()) {
                    bmpSrc = BitmapFactory.decodeFile(photos.get(0));
                    imageView.setImageBitmap(bmpSrc);
                }
            }
        }
    }
}
