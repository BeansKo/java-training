package com.beans.ko.java.training.thread.pool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadURLPool {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(new TestRunnable("https://blog.csdn.net/weixin_39478115/article/details/79297083","d:/test/test1.html"));
		executorService.submit(new TestRunnable("https://blog.csdn.net/gtsina/article/details/78048925","d:/test/test2.html"));
		executorService.submit(new TestRunnable("https://blog.csdn.net/xiantian7/article/details/53169895","d:/test/test3.html"));
		executorService.submit(new TestRunnable("http://confluence.newegg.org/pages/viewpage.action?pageId=4148551","d:/test/test4.html"));
		executorService.shutdown();
		System.out.println("end");
	}

}

class TestRunnable implements Runnable{

	String url;
	String path;
	
	public TestRunnable() {
	}
	public TestRunnable(String url, String path) {
		super();
		this.url = url;
		this.path = path;
	}


	@Override
	public void run() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			URL url = new URL(this.url);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			if(conn.getResponseCode() == 200){
				InputStream inputStream = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(inputStream));
				FileOutputStream fos = new FileOutputStream(path);
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				String line = br.readLine();
				while(line != null){
					bw.write(line);
					bw.newLine();
					line = br.readLine();
				}
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
