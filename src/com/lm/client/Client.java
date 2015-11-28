package com.lm.client;

import com.lm.model.request.CSProtocol;
import com.lm.util.Constant;
import com.lm.util.Help;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by LiuMian on 2015/11/27.
 */
public class Client implements Runnable{

    private Socket client;
    private InputStream is;
    private OutputStream os;
    private boolean isAlive;

    public Client (){
        isAlive = true;
    }

    public void connectServer(){
        try {
            client = new Socket(Constant.SERVER_IP,Constant.SERVER_PORT);
            is = client.getInputStream();
            os = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        byte[] bytes;
        while(isAlive){
            try {
                bytes = new byte[is.available()];
                is.read(bytes);
                parseBytes(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void parseBytes(byte[] bytes){
        CSProtocol protocol = Help.parseByteToObject(bytes,CSProtocol.class);

    }

    public void destroyClient(){
        Help.closeSocketAndStream(client,is,os);
    }
}
