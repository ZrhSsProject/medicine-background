package com.background.medicine.controller;

import java.io.*;
import org.apache.commons.io.FileUtils;

public class ReadBook {

    public static String readTxtFile(String path,int page) {
        String res = "";
        File file = new File(path);
        String filename = path+file.listFiles()[page-1].getName();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), "utf-16");
            BufferedReader bf = new BufferedReader(isr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null)
                    res+=str+"</br>";
            bf.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }

//    public static String readImg(String path,int page) {
//        File file = new File(path);
//        String filename = file.listFiles()[page-1].getName();
//        String res = path+filename;
//        return res;
//
//    }

    public static String readTIF(String path,int page) {
        File file = new File(path);
        String[] key = path.split("\\\\");
        String filename = "http://localhost:8081/common/"+"/tif/"+file.listFiles()[page-1].getName();
//        file = new File(filename);
//        byte[] tif = null;
//        try {
//            // Jackson会对byte[]会编码为Base64字符串，所以前端需要解码
//           tif = FileUtils.readFileToByteArray(file);
//        } catch (IOException e) {}
        return  filename;
    }


    public static int readNum(String filename) {
        int num = 0;
        File file = new File(filename);
        num = file.listFiles().length;
        return  num;
    }

    public static void main(String[] args) {
    }
}