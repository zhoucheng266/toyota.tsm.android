package com.toyota.tsm.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected LayoutInflater mInflater;

	public MyBaseAdapter(Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
	}

	protected List<T> mdata;

	public void setData(List<T> data) {
		this.mdata = data;
	}

	public void addDataToHead(List<T> data) {
		if (mdata == null) {
			mdata = new ArrayList<T>();
		}
		this.mdata.addAll(0, data);
	}

	public void addDataToTail(List<T> data) {
		if (data != null) {
			if (mdata == null) {
				mdata = data;
			} else
				this.mdata.addAll(data);
		}
	}

	public void addDataToTail(T data) {
		mdata.add(data);
	}

	public List<T> getData() {
		return mdata;
	}

	public T getLastItem() {
		int count = getCount();
		if (count == 0) {
			return null;
		} else {
			return (T) getItem(count - 1);
		}
	}

	@Override
	public int getCount() {
		return mdata == null ? 0 : mdata.size();
	}

	@Override
	public Object getItem(int position) {
		return mdata == null ? null : mdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		return -1;
	}

	public void clear() {
		if (mdata != null) {
			mdata.clear();
		}
	}

}
