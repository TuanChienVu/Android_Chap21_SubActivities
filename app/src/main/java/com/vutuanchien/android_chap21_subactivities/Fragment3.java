package com.vutuanchien.android_chap21_subactivities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Fragment3 extends android.support.v4.app.Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        TextView txtScore = (TextView) view.findViewById(R.id.txtScore);
        TextView txtHi = (TextView) view.findViewById(R.id.txtBest);
        TextView txtRung = (TextView) view.findViewById(R.id.txtRung);
        Animation aniRung = AnimationUtils.loadAnimation(getActivity(), R.anim.rung);
        txtRung.startAnimation(aniRung);

        txtScore.setText("" + MainActivity.diem);

        SharedPreferences hi = getActivity().getSharedPreferences("diemcuatoa", Context.MODE_PRIVATE);

        int d = hi.getInt("diem", 0);
        if (MainActivity.diem > d) {
            SharedPreferences.Editor editor = hi.edit();
            editor.putInt("diem", MainActivity.diem);
            editor.commit();
            d = MainActivity.diem;
        }
        txtHi.setText("" + d);


        final Fragment1 fragment1 = new Fragment1();
        Fragment1.replay = true;
        RelativeLayout rela_replay = (RelativeLayout) view.findViewById(R.id.rela_replay);
        rela_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment1)
                        .commit();

            }
        });

        return view;
    }


}
