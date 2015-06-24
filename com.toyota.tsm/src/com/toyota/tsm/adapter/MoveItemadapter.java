package com.toyota.tsm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.toyota.tsm.R;
import com.toyota.tsm.model.ListModel;

public class MoveItemadapter extends MyBaseAdapter<ListModel> {

	public MoveItemadapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		int count = getCount();
		if (count == 0) {
			return convertView;
		}
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.layout_move_item, parent,
					false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 获取数据
		ListModel listItem = mdata.get(position);
		holder.tvcarnum.setText(listItem.getPlatenumber());
		holder.tvdate.setText(listItem.getStartservice());
		holder.tvdelnum.setText(listItem.getBarcode());

		return convertView;
	}

	private static class ViewHolder {

		TextView tvdelnum, tvcarnum, tvdate;

		public ViewHolder(View v) {
			tvdelnum = (TextView) v.findViewById(R.id.tv_dlnum);
			tvcarnum = (TextView) v.findViewById(R.id.tv_carnum);
			tvdate = (TextView) v.findViewById(R.id.tv_time);
		}

	}

}
