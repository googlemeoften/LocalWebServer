package com.lm.util;

import com.lm.exception.DownloadException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
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
        }

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
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
        Pattern pattern = Pattern.compile("[\\w]+[\\.][\\w]{3,4}");

        Matcher matcher = pattern.matcher(url);
        String fileName = "";
        while(matcher.find()){
            fileName = matcher.group();
        }
        return fileName;
    }
}
