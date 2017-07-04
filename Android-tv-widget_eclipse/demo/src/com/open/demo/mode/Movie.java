package com.open.demo.mode;


public class Movie {
    private int mRes;
    private String mTitle;

    public Movie(int res, String title) {
        this.mRes = res;
        this.mTitle = title;
    }

    public int getRes() {
        return this.mRes;
    }

    public String getTitle() {
        return this.mTitle;
    }

}
