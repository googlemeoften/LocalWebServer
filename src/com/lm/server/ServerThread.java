package com.lm.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.lm.router.Router;
import com.lm.util.Constant;
import com.lm.util.Help;

/**
 *                                  如何解决端口被占用问题！
 * @author LiuMian
 * 2015年11月27日 上午9:55:19
 *
 */
public class ServerThread implements Runnable {

	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private OutputStream os;


	@Override
	public void run() {
		Router router = new Router();
		try {
			serverSocket = new ServerSocket(Constant.LOCAL_PORT,1,InetAddress.getByName("localhost"));
			System.out.println("监听端口："+Constant.LOCAL_PORT);
			while(true){
				socket = serverSocket.accept();
				is = socket.getInputStream();
				os = socket.getOutputStream();

				LocalRequest req = new LocalRequest(is);
				LocalResponse res = new LocalResponse(os,req);
				router.routes(res);

				Help.closeIOStream(is, os);
				socket.close();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				Help.closeIOStream(is, os);
				if(socket != null){
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
		new Thread(new ServerThread()).start();
	}
	
}
