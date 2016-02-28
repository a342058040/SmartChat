package com.feigezhou.tulingdemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
//异步通讯
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpData extends AsyncTask<String, Void, String>{
	//HttpData是用来做异步通讯的  让它扩展至AsyncTask<String

	private HttpClient mHttpClient;//通过HttpClient请求
	private HttpGet mHttpGet;//请求方式：HttpGet 
	private HttpResponse mHttpResponse;//发送请求后获取请求的返回
	private HttpEntity mHttpEntity;//创建实体
	private InputStream in;//将获取的数据转换为流文件
	private HttpGetDataListener listener;//实现HttpGetDataListener这个接口
	
	private String url;
	public HttpData(String url,HttpGetDataListener listener) {//构造方法  进行传递
		this.url = url;
		this.listener = listener;
	}
	
	@Override
	protected String doInBackground(String... params) {
		//书写HttpClient和HttpGet方法
		try {
			//指定try和catch抛出异常
			mHttpClient = new DefaultHttpClient();//实例化客户端
			mHttpGet = new HttpGet(url);//通过get方式传递一个具体的URL请求   URL通过调用当前这个类来传递
			mHttpResponse = mHttpClient.execute(mHttpGet);//通过客户端来发送
			mHttpEntity = mHttpResponse.getEntity();//通过response来获取数据
			in = mHttpEntity.getContent();//获取内容
			BufferedReader br = new BufferedReader(new InputStreamReader(in));//通过缓冲区进行读取获取的内容
			String line = null;
			StringBuffer sb = new StringBuffer();//储存所有数据
			while ((line = br.readLine()) != null) {//通过while循环进行读取   不为空则一直读取
				sb.append(line);
			}
			return sb.toString();//转换为string类型
		} catch (Exception e) {
		}
		
		return null;
	}
	@Override
	protected void onPostExecute(String result) {//复写方法  来获取数据 采用回调的方法创建HttpGetDataListener这个接口
		listener.getDataUrl(result);//返回数据
		super.onPostExecute(result);
	}
}
