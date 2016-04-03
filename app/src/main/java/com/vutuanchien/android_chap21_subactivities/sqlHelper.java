package com.vutuanchien.android_chap21_subactivities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class sqlHelper extends SQLiteOpenHelper {

    Context context;
    static String name = "data.sqlite";
    String path = "data/data/com.vutuanchien.android_chap21_subactivities/databases/";


    public sqlHelper(Context context) {
        super(context, name, null, 1);
        this.context = context;
    }

    boolean checkDatabase() {
        File a = new File(path + name);
        if (a.exists())
            return true;
        return false;

    }

    public void copy() {
        if (checkDatabase() == false) {

            this.getReadableDatabase();
            try {
                InputStream inputStream = context.getAssets().open(name);
                OutputStream outputStream = new FileOutputStream(path + name);
                byte[] buf = new byte[1024];
                int leght;
                while ((leght = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, leght);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    public List<tuvung> getTu() {
        List<tuvung> kq = new ArrayList<tuvung>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from toiec", null);
        if (cursor.moveToFirst())
            while (cursor.moveToNext()) {
                tuvung tu = new tuvung(cursor.getInt(0), cursor.getString(1), cursor.getString(4));

                kq.add(tu);

            }
        return kq;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
