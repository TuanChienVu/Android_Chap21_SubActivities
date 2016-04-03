package com.vutuanchien.android_chap21_subactivities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class Fragment2 extends android.support.v4.app.Fragment {


    ImageView imgCa, imgBien;
    float xDeta = 0, yDeta = 0;
    long timeCa = 8000, timerun = 15000;
    TranslateAnimation aniCa1, aniBack;
    ;
    translateAnimation aniRun;
    AnimationSet aniCa2;
    float desity;
    RelativeLayout lo_run;
    sqlHelper helper;
    List<tuvung> tuvungs;
    TextView txtDiem, txtTren, txtDuoi, txtHoi;
    tuvung kq;
    Random random = new Random();
    FrameLayout btnTren, btnDuoi;

    boolean chuabam = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        getXYdeta();

        MainActivity.diem = 0;

        helper = new sqlHelper(getActivity());
        helper.copy();
        tuvungs = helper.getTu();

        imgCa = (ImageView) view.findViewById(R.id.imgCa);
        imgBien = (ImageView) view.findViewById(R.id.imgBien);
        lo_run = (RelativeLayout) view.findViewById(R.id.lo_run);
        txtDiem = (TextView) view.findViewById(R.id.txtDiem);
        btnTren = (FrameLayout) view.findViewById(R.id.btnTren);
        btnDuoi = (FrameLayout) view.findViewById(R.id.btnDuoi);
        txtTren = (TextView) view.findViewById(R.id.txtTren);
        txtDuoi = (TextView) view.findViewById(R.id.txtDuoi);
        txtHoi = (TextView) view.findViewById(R.id.txtCauHoi);

        btnTren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuabam = true;


                float chaydc = (yDeta / timerun) * aniRun.getTime();


                back(chaydc);
                if (txtTren.getText().toString().equals(kq.getTienganh())) {
                    MainActivity.diem++;
                    setText();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new Fragment3())
                            .commit();
                }


            }
        });
        btnDuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chuabam = true;
                float chaydc = (yDeta / timerun) * aniRun.getTime();

                back(chaydc);
                if (txtDuoi.getText().toString().equals(kq.getTienganh())) {
                    MainActivity.diem++;
                    setText();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new Fragment3())
                            .commit();

                }

            }
        });

        AnimationDrawable aniDrawCa = (AnimationDrawable) imgCa.getBackground();
        aniDrawCa.start();
        AnimationDrawable aniBien = (AnimationDrawable) imgBien.getBackground();
        aniBien.start();

        setAnimationCa();
        setAniLoRun(timerun);


        setText();

        return view;
    }

    public void back(float from) {

        aniBack = new TranslateAnimation(0, 0, 0, 0, Animation.RELATIVE_TO_PARENT, from, Animation.RELATIVE_TO_PARENT, 0);
        long t = 200;
        if (from <= 1)
            t = 200;
        else if (from < 0.1f && from >= 0.2f)
            t = 300;
        else if (from < 0.2f && from >= 0.3f)
            t = 400;
        else if (from < 0.3f && from >= 0.4f)
            t = 500;
        else if (from < 0.4f && from >= 0.5f)
            t = 600;
        else if (from < 0.5f && from >= 0.6f)
            t = 700;
        else if (from < 0.6f && from >= 0.7f)
            t = 800;
        else if (from < 0.7f && from >= 0.8f)
            t = 900;
        else if (from < 0.8f && from >= 0.9f)
            t = 1000;
        else
            t = 1100;
        aniBack.setDuration(t);
        aniBack.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                lo_run.clearAnimation();
                lo_run.startAnimation(aniRun);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        lo_run.clearAnimation();
        lo_run.startAnimation(aniBack);


    }

    public void setText() {

        if (MainActivity.diem <= 3)
            timerun = 15000;
        else if (MainActivity.diem > 3 && MainActivity.diem <= 7)
            timerun = 10000;
        else if (MainActivity.diem > 7 && MainActivity.diem <= 11)
            timerun = 8000;
        else if (MainActivity.diem > 11 && MainActivity.diem <= 16)
            timerun = 5000;
        else if (MainActivity.diem > 16 && MainActivity.diem <= 25)
            timerun = 3000;
        else
            timerun = 2000;

        aniRun.setDuration(timerun);
        txtDiem.setText("" + MainActivity.diem);


        int vt = random.nextInt(tuvungs.size());
        kq = tuvungs.get(vt);

        int vtsai = random.nextInt(tuvungs.size());
        while (vt == vtsai)
            vtsai = random.nextInt(tuvungs.size());

        int rd = random.nextInt(2);
        if (rd == 0)
            updateText(kq, kq.getTienganh(), tuvungs.get(vtsai).getTienganh());
        else
            updateText(kq, tuvungs.get(vtsai).getTienganh(), kq.getTienganh());


    }

    public void updateText(tuvung kq, String tren, String duoi) {
        txtHoi.setText(kq.getTiengviet());
        txtTren.setText(tren);
        txtDuoi.setText(duoi);

    }

    public void getXYdeta() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        desity = displayMetrics.density;
        float width = displayMetrics.widthPixels / desity;
        float heigh = displayMetrics.heightPixels / desity;

        float percentX = (60 * 100) / width;
        xDeta = 100 - percentX;
        xDeta = xDeta / 100;

        float percentY = (130 * 100) / heigh;
        yDeta = 100 - percentY;
        yDeta = yDeta / 100;

    }


    public void setAniLoRun(long time) {
        aniRun = new translateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0
                , Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, yDeta);
        aniRun.setDuration(time);
        Interpolator interpolator = new LinearInterpolator();
        aniRun.setInterpolator(interpolator);
        aniRun.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (chuabam == false) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new Fragment3())
                            .commit();
                }
                chuabam = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        lo_run.startAnimation(aniRun);


    }


    public void setAnimationCa() {
        aniCa1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, -xDeta,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        aniCa1.setDuration(timeCa);

        aniCa2 = new AnimationSet(false);
        aniCa2.addAnimation(new ScaleAnimation(1, -1, 1, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
        TranslateAnimation tran2 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -xDeta, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        tran2.setDuration(timeCa);
        aniCa2.addAnimation(tran2);


        aniCa1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                imgCa.startAnimation(aniCa2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aniCa2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                imgCa.startAnimation(aniCa1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgCa.startAnimation(aniCa1);


    }

}
