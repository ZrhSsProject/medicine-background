package com.background.medicine.controller;

import java.io.*;
import org.apache.commons.io.FileUtils;

public class ReadBook {
    public static String userpath = "C:/Users/ShenSong/Desktop/毕业设计/数据/测试/user/";

    public static String readTxtFile(String path,int userid,int bookid,int page) {
        String res = "";
        String filename = "";
        int flag = 0;
        String type = "utf-16";
        File file1 = new File(userpath+userid+"/"+bookid+"-"+page+".txt");
        if(file1.exists()){
            filename = userpath+userid+"/"+bookid+"-"+page+".txt";
            flag = 1;
            type = "utf-8";
        }else {
            File file = new File(path);
            filename = path + file.listFiles()[page - 1].getName();
        }
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), type);
            BufferedReader bf = new BufferedReader(isr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                if(flag == 1)
                    res += str;
                else
                    res += str + "</br>";
            }
            bf.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void savetxtFile(String info,Integer userID, Integer fileID, Integer page)
    {
        String path1 = userpath+userID;
        String paths = userpath+userID+"/"+fileID+"-"+page+".txt";
        File f=new File(path1);
        if(!f.exists()){
            f.mkdirs();
        }
        File f1=new File(paths);
        try{
            f1.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }

        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(paths,false);
            fwriter.write(info);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

//    public static String saveTxtFile(int userID,int page) {
//        String res = "";
//        File file = new File(path);
//        String filename = path+file.listFiles()[page-1].getName();
//        try {
//            InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), "utf-16");
//            BufferedReader bf = new BufferedReader(isr);
//            String str;
//            // 按行读取字符串
//            while ((str = bf.readLine()) != null)
//                res+=str+"</br>";
//            bf.close();
//            isr.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return res;
//
//    }

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