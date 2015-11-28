package com.lm.test;

import com.lm.client.Client;
import com.lm.server.ServerThread;

/**
 * Created by LiuMian on 2015/11/28.
 */
public class TestClient {

    public static void main(String[] args){
        new Thread(new Client()).start();
    }
}
