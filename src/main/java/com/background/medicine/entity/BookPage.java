package com.background.medicine.entity;

public class BookPage {
    String res;
    int page;

    public BookPage(String res, int page) {
        this.res = res;
        this.page = page;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "BookPage{" +
                "res='" + res + '\'' +
                ", page=" + page +
                '}';
    }
}
