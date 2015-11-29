package com.lm.util;

import com.lm.exception.DownloadException;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Help {
	 /**
     * 将对象序列化
     */
    public static byte[] parseObjectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //写入到ByteArrayOutputStream中的
            oos.writeObject(obj);
            bytes = new byte[bos.size()];
            bytes = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 把对象反序列化
     * @param bytes
     * @param clazz
     * @return 
     * @return
     * @throws Exception
     */
    public static <T> T parseByteToObject(byte[] bytes, Class<T> clazz) {
        T obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static void closeSocketAndStream(Socket socket, InputStream is,OutputStream os){
        if(socket != null){
            try {
                socket.close();
                closeIOStream(is,os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static void closeIOStream(InputStream is,OutputStream os){
    	
    	if(is != null){
    		try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if(os != null){
    		try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    }

    public static void downloadFile(String httpUrl) throws DownloadException {

        String fileName = getFileNameFromUrl(httpUrl);
        String filePath =Constant.FILE_PARENT_PATH+fileName;
        String encodeUrl = encodeUrl(httpUrl);
        URL url = null;
        try {
            url = new URL(encodeUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();
        } catch (IOException e) {
            System.out.println("conn.getInputStream error");
            e.printStackTrace();
        }

        try {
            FileOutputStream fs = new FileOutputStream(filePath);

            int bufferSize = 0;
            byte[] buffer = new byte[1024];
            while ((bufferSize = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, bufferSize);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DownloadException("下载文件未找到");
        } catch (IOException e) {
            throw new DownloadException("下载文件时 IO异常");
        }
    }

    public static String getFileNameFromUrl(String url){
        //截取文件名以及后缀
        Pattern pattern = Pattern.compile("[\\w]+[\\.][\\w]{3,4}");

        Matcher matcher = pattern.matcher(url);
        String fileName = "";
        while(matcher.find()){
            fileName = matcher.group();
        }
        System.out.println("fileName: "+fileName);
        return fileName;
    }

    //对路径里面的中文字符编码
    public static String encodeUrl(String url){

        String fileName = url.substring(url.lastIndexOf("/")+1);
        String preUrl = url.substring(0,url.lastIndexOf("/")+1);
        String encodeUrl = "";
        try {
            String encodeFileName = URLEncoder.encode(fileName,"utf-8");
            System.out.println("encodeFileName: "+encodeFileName);
            encodeUrl = preUrl+URLEncoder.encode(fileName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("url: "+url);
        System.out.println("encodeUrl: "+encodeUrl);

        return encodeUrl;
    }
}
