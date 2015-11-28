package com.lm.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.lm.util.Help;

public class LocalRequest {


	private Map<String, String> map = null;
	// 截取url,如http://localhost:8080/index.html ，截取部分为 /index.html
	private String uri;

	public LocalRequest(InputStream is) {
		parse(is);
	}
	
	public String query(String key) {
		return map.get(key);
	}
	
	public String getUri(){
		return uri;
	}

	// 从套接字中读取字符信息
	private void parse(InputStream is) {
		int i = 0;
		byte[] bytes = null;
		try {
			int size = is.available();
			bytes = new byte[size];
			i = is.read(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			i = -1;
		}
		
		StringBuffer pathSB = new StringBuffer();
		for (int j = 0; j < i; j++) {
			pathSB.append((char) (bytes[j]));
		}
		
		System.out.println("pathSB: "+pathSB.toString());
		
		uri = parseUri(pathSB.toString());

		if(null != uri) {
			map = MapRequest.URLRequest(uri);
		}
		System.out.println("" + "http:/" + uri);
	}


	// 截取请求的url
	@SuppressWarnings("unused")
	private String parseUri(String requestString) {

		int index1 = 0;
		int index2 = 0;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1) {
				return requestString.substring(index1 + 1, index2);
			}
		}
		return null;
	}

}
