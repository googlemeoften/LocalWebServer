package com.lm.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuMian on 2015/11/28.
 */
public class TestRegex {

    public static void main(String[] args){
        String url = "http://cquptcloudprint.oss-cn-hangzhou.aliyuncs.com/oFVKgjkSK8D2LOkFH0OMztYNhS9Y/Baby.docx";

//        Pattern pattern = Pattern
//                .compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");
        Pattern pattern = Pattern.compile("[\\w]+[\\.][\\w]{3,4}");

        Matcher matcher = pattern.matcher(url);
        String fileName = "";
        while(matcher.find()){
//            System.out.println(matcher.group());
            fileName = matcher.group();
        }

        System.out.println("fileName: "+fileName);



    }


}
