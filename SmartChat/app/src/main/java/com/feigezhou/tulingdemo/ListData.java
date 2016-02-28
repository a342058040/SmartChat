package com.feigezhou.tulingdemo;

public class ListData {//数据封装
	
	public static final int SEND = 1;//用户自己
	public static final int RECEIVER = 2;
	private String content;
	private int flag;
	private String time;
	
	public ListData(String content,int flag,String time) {
		setContent(content);
		setFlag(flag);
		setTime(time);
	}
	
	public String getContent() {//实现getContent()方法
		return content;
	}
	public void setContent(String content) {//实现setContent方法
		this.content = content;
	}
	public int getFlag() {//用getFlag()和setFlag进行标识
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}

