package com.lm.util;

import java.io.File;
import java.net.InetAddress;

public class Constant {

	//本地端口
	public static final int LOCAL_PORT = 8080;
	//服务器端口
	public static final int SERVER_PORT=4347;
	//服务器ip
	public static final String SERVER_IP="114.215.143.80";
	// WEB_ROOT是改服务器的根目录
	public static final String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webroot";

	public static final int BUFFER_SIZE = 1024;


	public static final String FILE_PARENT_PATH="F:/temp/";

}
