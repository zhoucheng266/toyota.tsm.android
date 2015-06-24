package com.toyota.tsm;

import com.toyota.broadcast.ConnectionChangeReceiver;
import com.toyota.broadcast.ConnectionChangeReceiver.Listener;

<<<<<<< HEAD
import android.app.TabActivity;
import android.content.Context;
=======
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
>>>>>>> origin/master
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	private MainActivity vThis = this;
	private ConnectionChangeReceiver mConnectionChangeReceiver;
	private TabHost mTabHost = null;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState != null) {

		}
		initview();
		mConnectionChangeReceiver = new ConnectionChangeReceiver();
		mConnectionChangeReceiver.setListener(new Listener() {
			@Override
			public void onChange(boolean networkAvailable) {
				initNetworkTactics();
			}
		});
<<<<<<< HEAD
		
	    registerReceiver(mConnectionChangeReceiver, new IntentFilter(
	            ConnectivityManager.CONNECTIVITY_ACTION));
=======

		registerReceiver(mConnectionChangeReceiver, new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION));
>>>>>>> origin/master
	}

	private void initNetworkTactics() {
		int bNetworkWeight = 0;// 网络环境加权值，>0表示增加配置增加，=0表示配置不变，<0表示配置降级

		ConnectivityManager mConnectivity = (ConnectivityManager) vThis
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		TelephonyManager mTelephony = (TelephonyManager) vThis
				.getSystemService(Context.TELEPHONY_SERVICE); // 检查网络连接，如果无网络可用，就不需要进行连网操作等
		NetworkInfo info = mConnectivity.getActiveNetworkInfo();
		if (info == null || !mConnectivity.getBackgroundDataSetting()) {
			// 无网络
			Toast.makeText(vThis, "网络未连接", Toast.LENGTH_LONG).show();
			return;
		}

	}

	/**
	 * 初始化view
	 */
	private void initview() {

		mTabHost = getTabHost();

		Intent intent = new Intent(vThis, RegActivity.class);
		mTabHost.addTab(mTabHost.newTabSpec("TAG_REG").setIndicator("TAG_REG")
				.setContent(intent));

		/*
		 * Intent itemlist = new Intent(vThis, ItemActivity.class);
		 * mTabHost.addTab(mTabHost.newTabSpec("TAG_ITEM")
		 * .setIndicator("TAG_ITEM").setContent(itemlist));
		 */
<<<<<<< HEAD

		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

=======
		RadioButton radiobtn = (RadioButton) findViewById(R.id.main_rdo_menu1);
		radiobtn.setOnClickListener(new OnClickListener() {
			
>>>>>>> origin/master
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
<<<<<<< HEAD
				switch (arg1) {
				case R.id.main_rdo_menu1:
					mTabHost.setCurrentTab(0);
					break;

				/*
				 * case R.id.main_rdo_menu2: mTabHost.setCurrentTab(1); break;
				 */

				}
=======
				AlertDialog.Builder builder = new AlertDialog.Builder(vThis);

				builder.setTitle("是否注销");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@SuppressLint("ResourceAsColor")
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Intent intent = new Intent(vThis,
										LoginActivity.class);
								startActivity(intent);
								finish();

							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// 这里添加点击确定后的逻辑

							}
						});
				builder.create().show();
>>>>>>> origin/master
			}
		});

		//RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

		// radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// // TODO Auto-generated method stub
		// switch (arg1) {
		// case R.id.main_rdo_menu1:
		// // mTabHost.setCurrentTab(0);
		// AlertDialog.Builder builder = new AlertDialog.Builder(vThis);
		//
		// builder.setTitle("是否注销");
		// builder.setPositiveButton("确定",
		// new DialogInterface.OnClickListener() {
		// @SuppressLint("ResourceAsColor")
		// public void onClick(DialogInterface dialog,
		// int whichButton) {
		// Intent intent = new Intent(vThis,
		// LoginActivity.class);
		// startActivity(intent);
		// finish();
		//
		// }
		// });
		// builder.setNegativeButton("取消",
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog,
		// int whichButton) {
		// // 这里添加点击确定后的逻辑
		//
		// }
		// });
		// builder.create().show();
		//
		// break;
		//
		// /*
		// * case R.id.main_rdo_menu2: mTabHost.setCurrentTab(1); break;
		// */
		//
		// }
		// }
		// });
	}

}
