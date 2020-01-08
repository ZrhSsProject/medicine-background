package com.background.medicine.entity;

public class Result {
    String res;

    public Result(String res){
        this.res = res;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "Result{" +
                "res='" + res + '\'' +
                '}';
    }
}
