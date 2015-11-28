package com.lm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

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
}
