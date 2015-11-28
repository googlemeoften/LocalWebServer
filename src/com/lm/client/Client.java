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
    private ClientAssistant assistant;

    public Client (){
        isAlive = true;
        connectServer();
    }

    public void connectServer(){
        try {
            client = new Socket(Constant.SERVER_IP,Constant.SERVER_PORT);
            is = client.getInputStream();
            os = client.getOutputStream();
            os.write("server connect succuse --".getBytes());
            System.out.println("server connect succuse --");
        } catch (IOException e) {
            e.printStackTrace();

            destroyClient();
        }
    }

    @Override
    public void run() {
        while(isAlive){
            try {
                byte[] bytes = new byte[512];
                System.out.println("wait for message from server");
                is.read(bytes);
                CSProtocol protocol = parseBytes(bytes);
                doOperation(protocol);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private CSProtocol parseBytes(byte[] bytes){
        return Help.parseByteToObject(bytes,CSProtocol.class);
    }

    private void doOperation(CSProtocol protocol){
        assistant = new ClientAssistant(this);
        assistant.assignTask(protocol);
    }

    public void sendCSProtocl(CSProtocol protocol){
        byte[] bytes = Help.parseObjectToByte(protocol);
        try {
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            System.out.println("œ˚œ¢∑¢ÀÕ ß∞‹");
            e.printStackTrace();
        }

    }

    public void destroyClient(){
        Help.closeSocketAndStream(client,is,os);
    }
}
