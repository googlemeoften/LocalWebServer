package com.lm.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import com.lm.util.Constant;
import com.xpc.main.Request;


public class LocalResponse {

	
	private OutputStream output;
	private LocalRequest req;

	public LocalResponse(OutputStream output,LocalRequest req) {
		this.output = output;
		this.req = req;
	}
	
	public String getUri(){
		return req.getUri();
	}
	
	public String query(String key){
		return req.query(key);
	}

	public void sendFileNotFound() {
		String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
				+ "Content-Type: text/html\r\n" + "Content-Length: 23\r\n"
				+ "\r\n" + "File Not Found";
		try {
			output.write(errorMessage.getBytes());
			output.flush();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeOutputStream(output);
		}
	}

	public void sendStaticResource(){
		File file = new File(Constant.WEB_ROOT, getUri());
		try {
			sendFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendWRFile(String path) {
		File file = new File(Constant.WEB_ROOT, path);
		try {
			sendFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String str) {
		try {
			output.write(str.getBytes());
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			closeOutputStream(output);
		}
	}
	
	public void sendFile(File file) throws IOException {
		byte[] bytes = new byte[Constant.BUFFER_SIZE];
		FileInputStream fis = null;
		
		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, Constant.BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, Constant.BUFFER_SIZE);
				}

			} catch (FileNotFoundException e) {
				System.out.println(e.toString());
				sendFileNotFound();
			} catch (IOException e) {
				System.out.println(e.toString());
				sendFileNotFound();
			} finally {
				if (fis != null) {
					fis.close();
				}
				closeOutputStream(output);
			}
		} else {
			sendFileNotFound();
		}
	}
	
	private void closeOutputStream(OutputStream os){
		if(os != null){
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
