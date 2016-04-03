package com.vutuanchien.android_chap21_subactivities;

public class tuvung {

    private int id;
    private String tienganh;
    private String tiengviet;

    public tuvung(int id, String tienganh, String tiengviet) {
        this.setId(id);
        this.setTienganh(tienganh);
        this.setTiengviet(tiengviet);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTienganh() {
        return tienganh;
    }

    public void setTienganh(String tienganh) {
        this.tienganh = tienganh;
    }

    public String getTiengviet() {
        return tiengviet;
    }

    public void setTiengviet(String tiengviet) {
        this.tiengviet = tiengviet;
    }
}
