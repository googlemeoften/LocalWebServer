package com.lm.test;

import com.lm.util.Constant;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuMian on 2015/11/28.
 */
public class TestDownload {


    public static void main(String[] args){

        String url = "http://cquptcloudprint.oss-cn-hangzhou.aliyuncs.com/oFVKgjkSK8D2LOkFH0OMztYNhS9Y/Baby.docx";
        String savePath = "D:/阿里云测试文件.docx";

        downloadFile(url);

    }

    public static boolean downloadFile(String httpUrl){
        int bytesum = 0;
        int byteread = 0;

        String fileName = getFileNameFormUrl(httpUrl);

        String filePath = Constant.FILE_PARENT_PATH+fileName;

        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(filePath);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static String getFileNameFormUrl(String url){
//        String url = "http://cquptcloudprint.oss-cn-hangzhou.aliyuncs.com/oFVKgjkSK8D2LOkFH0OMztYNhS9Y/Baby.docx";

        Pattern pattern = Pattern.compile("[\\w]+[\\.][\\w]{3,4}");

        Matcher matcher = pattern.matcher(url);
        String fileName = "";
        while(matcher.find()){
            fileName = matcher.group();
        }
        return fileName;
    }
}
