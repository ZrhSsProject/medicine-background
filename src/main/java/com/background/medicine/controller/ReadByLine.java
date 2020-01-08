package com.background.medicine.controller;

import java.io.*;
import java.util.ArrayList;

public class ReadByLine {
//  每页书的行数设置10
    public static int BOOKLINE = 10;

    public static String readFile(String filename,int page) {
        String res = "";
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), "GB2312");
            BufferedReader bf = new BufferedReader(isr);
            String str;
            int count = 0;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                count++;
                if((page-1)*10+1 > count)
                    continue;
                else if(count <= page*10)
                    res+=str+"</br>";
                else
                    break;
            }
            bf.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(res);
        return res;

    }

    public static void main(String[] args) {
        ReadByLine rl=new ReadByLine();
        String filename = "C:/100.txt";
        rl.readFile(filename,1);
    }
}
