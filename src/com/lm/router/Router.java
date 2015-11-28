package com.lm.router;

import java.io.IOException;

import com.lm.server.LocalRequest;
import com.lm.server.LocalResponse;
import com.xpc.main.Request;
import com.xpc.main.Response;


public class Router {
	/**
	 * 工厂函数
	 * @param url 请求的url
	 * @param req request对象
	 * @param resp response对象
	 * @return -1代表出错失败 其他对标功能正确
	 */
	public void routes(LocalResponse resp) {
		String url = resp.getUri();

		if("/index".equals(url)) {
			sendHome(resp);
		}else if("/files/oepn".equals(url)) {
			openFile(resp);
		}else if("/print/info".equals(url)){
			sendFileNotFound(resp);
		}else{

		}
	}

	/**
	 * /index
	 * @description 返回首页html结构，显示二维码
	 * @return
	 */
	private int sendHome(LocalResponse res) {
		res.sendWRFile("index.html");
		return -1;
	}


	/**
	 * /print/info
	 * 得到待打印的信息
	 */

	/**
	 * files/open
	 * {filename: id:}
	 * 打开文件
	 */

	private int openFile(LocalResponse res) {
		String filename = res.query("filename");
		String id = res.query("id");

		try {
			System.out.println("filename: "+filename);
			Runtime.getRuntime().exec("cmd /c start c:\\" + filename);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String info = "ok";
		int status = 1;
		if(null != filename && null != id) {
			try {
				Runtime.getRuntime().exec("cmd /c start c:\\" + filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				info = "fail";
				status = 0;
				e.printStackTrace();
			}
		}
		res.send("{\"status\": " + status + ",\"info\": \"" + info + "\"}");
		return -1;
	}
	
	private void sendFileNotFound(LocalResponse resp){
		resp.sendFileNotFound();
	}
}
