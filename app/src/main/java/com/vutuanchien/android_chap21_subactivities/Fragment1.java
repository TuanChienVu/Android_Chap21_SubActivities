package com.vutuanchien.android_chap21_subactivities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class Fragment1 extends android.support.v4.app.Fragment {

    public static boolean replay = false;

    ImageView igFish, igNenChay;
    Button btnPlay;
    TranslateAnimation caDiChuyen;
    TextView tvTimer;

    Random random = new Random();
    float maxX, maxY;
    int pX = 0, pY = 0;
    float truoctox = 0f, truoctoy = 0f;
    long time = 8000;

    boolean dung = false;

    boolean trai = true;

    public void getXYmax() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels / displayMetrics.density;
        float heigh = displayMetrics.heightPixels / displayMetrics.density;

        float percentX = (60 * 100) / width;

        maxX = 100 - percentX;
        pX = (int) maxX;
        maxX = maxX / 100;

        float percentY = (42 * 100) / heigh;

        maxY = 100 - percentY;
        pY = (int) maxY;
        maxY = maxY / 100;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);


        getXYmax();
        igFish = (ImageView) view.findViewById(R.id.imgFish);
        igNenChay = (ImageView) view.findViewById(R.id.imgNenChay);
        btnPlay = (Button) view.findViewById(R.id.btnPlay);
        tvTimer = (TextView) view.findViewById(R.id.txtTimer);


        AnimationDrawable drawable = (AnimationDrawable) igFish.getBackground();
        drawable.start();

        Animation aniFish = AnimationUtils.loadAnimation(getActivity(), R.anim.carot);
        Animation aniNen = AnimationUtils.loadAnimation(getActivity(), R.anim.nenchay);
        Animation aniPlay = AnimationUtils.loadAnimation(getActivity(), R.anim.buttonmenu);


        igNenChay.startAnimation(aniNen);
        if (replay == false) {
            trai = random.nextBoolean();

            igFish.startAnimation(aniFish);

            btnPlay.startAnimation(aniPlay);
            aniFish.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {


                    if (dung == false) {

                        if (trai == true) {
                            truoctox = -maxX / 2f;
                            truoctoy = (float) random.nextInt(7) / 10;

                            dichuyencatrai(0, truoctox, 0, truoctoy);
                        } else {
                            truoctox = maxX / 2f;
                            truoctoy = (float) random.nextInt(7) / 10;

                            dichuyencaphai(0, truoctox, 0, truoctoy);

                        }
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    dung = true;

                    igFish.clearAnimation();
                    btnPlay.clearAnimation();

                    igFish.setVisibility(View.GONE);
                    btnPlay.setVisibility(View.GONE);
                    tvTimer.setVisibility(View.VISIBLE);


                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        int i = 3;

                        @Override
                        public void run() {
                            i--;

                            if (i == 0) {
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.container, new Fragment2())
                                        .commit();
                            } else {
                                tvTimer.setText("" + i);
                                handler.postDelayed(this, 1000);
                            }

                        }
                    }, 1000);
                }


            });
        } else {
            igFish.clearAnimation();
            btnPlay.clearAnimation();

            igFish.setVisibility(View.GONE);
            btnPlay.setVisibility(View.GONE);
            tvTimer.setVisibility(View.VISIBLE);

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                int i = 3;

                @Override
                public void run() {
                    i--;

                    if (i == 0) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new Fragment2())
                                .commit();
                    } else {
                        tvTimer.setText("" + i);
                        handler.postDelayed(this, 1000);
                    }

                }
            }, 1000);

        }
        return view;
    }

    public void dichuyencaphai(float fromX, float toX, float fromY, float toY) {
        AnimationSet ani = new AnimationSet(false);
        ani.addAnimation(new ScaleAnimation(1, -1, 1, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));

        TranslateAnimation ca = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, fromX, Animation.RELATIVE_TO_PARENT, toX
                , Animation.RELATIVE_TO_PARENT, fromY, Animation.RELATIVE_TO_PARENT, toY);

        ca.setDuration(time);
        ani.addAnimation(ca);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (dung == false) {

                    float ht = (float) random.nextInt(7) / 10;
                    dichuyencatrai(truoctox, -maxX / 2, truoctoy, ht);
                    truoctox = -maxX / 2;
                    truoctoy = ht;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        igFish.startAnimation(ani);
    }

    public void dichuyencatrai(float fromX, float toX, float fromY, float toY) {
        caDiChuyen = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, fromX, Animation.RELATIVE_TO_PARENT, toX
                , Animation.RELATIVE_TO_PARENT, fromY, Animation.RELATIVE_TO_PARENT, toY);
        caDiChuyen.setDuration(time);
        caDiChuyen.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (dung == false) {
                    float ht = (float) random.nextInt(7) / 10;
                    dichuyencaphai(truoctox, maxX / 2, truoctoy, ht);
                    truoctox = maxX / 2;
                    truoctoy = ht;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        igFish.startAnimation(caDiChuyen);

    }

}
