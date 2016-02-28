package com.feigezhou.tulingdemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.feigezhou.tulingdemo.R;

public class MainActivity extends Activity implements HttpGetDataListener,
		OnClickListener {

	private List<ListData> lists;//创建一个集合
	private ListView lv;
	private EditText sendtext;
	private Button send_btn;
	private String content_str;
	private TextAdapter adapter;
	private String[] welcome_array;
	private double currentTime=0, oldTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {//初始化方法
		lv = (ListView) findViewById(R.id.lv);//ListView
		sendtext = (EditText) findViewById(R.id.sendText);
		send_btn = (Button) findViewById(R.id.send_btn);
		lists = new ArrayList<ListData>();//实例化这个集合
		send_btn.setOnClickListener(this);//点击发送调用
		adapter = new TextAdapter(lists, this);
		lv.setAdapter(adapter);//绑定adapter
		ListData listData;
		listData = new ListData(getRandomWelcomeTips(), ListData.RECEIVER,//把文字放入封装类中 
				getTime());
		lists.add(listData);
	}

	private String getRandomWelcomeTips() {//随机欢迎语
		String welcome_tip = null;
		welcome_array = this.getResources()//获取资源
				.getStringArray(R.array.welcome_tips);
		int index = (int) (Math.random() * (welcome_array.length - 1));//获取随机
		welcome_tip = welcome_array[index];
		return welcome_tip;
	}

	@Override
	public void getDataUrl(String data) {
		// System.out.println(data);
		parseText(data);//调用解析的方法
	}

	public void parseText(String str) {//解析文字的方法
		try {
			JSONObject jb = new JSONObject(str);
			// System.out.println(jb.getString("code"));
			// System.out.println(jb.getString("text"));
			ListData listData;//创建对象
			listData = new ListData(jb.getString("text"), ListData.RECEIVER,
					getTime());//将其实例化
			lists.add(listData);//把数据添加到这个集合当中
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		getTime();
		content_str = sendtext.getText().toString();
		sendtext.setText("");//每次发送完后清空
		String dropk = content_str.replace(" ", "");//去掉空格
		String droph = dropk.replace("\n", "");//去掉回车
		ListData listData;
		listData = new ListData(content_str, ListData.SEND, getTime());
		lists.add(listData);
		if (lists.size() > 30) {//数据大于30就把它移除
			for (int i = 0; i < lists.size(); i++) {
				lists.remove(i);
			}
		}
		adapter.notifyDataSetChanged();//刷新
	 new HttpData(
				"http://www.tuling123.com/openapi/api?key=2dec490a6bce24451b81665f80ef0833&info="
						+ droph, this).execute();
	}

	private String getTime() {//获取系统时间
		currentTime = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date curDate = new Date();
		String str = format.format(curDate);
		if (currentTime - oldTime >= 500) {//0.5s后就有时间
			oldTime = currentTime;
			return str;
		} else {
			return "";
		}

	}

public boolean onCreateOptionsMenu(Menu menu) {
	MenuItem exit = menu.add(0, 1, 0, "关于软件");
	MenuItem about = menu.add(0, 0, 0, "退出登陆");
	return super.onCreateOptionsMenu(menu);
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	if(item.getItemId() == 0){
		this.stopService(new Intent(this,MainActivity.class));
		this.finish();
	}else{
		Intent it = new Intent(MainActivity.this,AboutActivity.class);
		startActivity(it);
	}
	return super.onOptionsItemSelected(item);
}

@Override
protected void onDestroy() {
	super.onDestroy();
}

}