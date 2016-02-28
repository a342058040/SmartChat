package com.feigezhou.tulingdemo;

import java.util.List;

import com.feigezhou.tulingdemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextAdapter extends BaseAdapter{//扩展至BaseAdapter
	
	private List<ListData> lists;//集合的泛型
	private Context mContext;
	private RelativeLayout layout;
	
	public TextAdapter(List<ListData> lists,Context mContext) {
		
		this.lists = lists;
		this.mContext = mContext;
	}
	
	@Override
	public int getCount() {//复写4个方法
		return lists.size();//返回当前listview所承载的条数
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		if(lists.get(position).getFlag() == ListData.RECEIVER){//接受  加载左边的leftitem
			layout = (RelativeLayout) inflater.inflate(R.layout.leftitem, null);
		}
		if (lists.get(position).getFlag() == ListData.SEND) {//自己发送的    加载右边的
			layout = (RelativeLayout) inflater.inflate(R.layout.rightitem, null);
		}
		TextView tv = (TextView) layout.findViewById(R.id.tv);
		TextView time = (TextView) layout.findViewById(R.id.time);
		tv.setText(lists.get(position).getContent());//获取当前TextView并设置内容
		time.setText(lists.get(position).getTime());//在Adapter中适配
		return layout;
	}

}
