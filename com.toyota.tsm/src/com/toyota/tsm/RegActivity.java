package com.toyota.tsm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.toyota.tsm.api.UserAPI;
import com.toyota.tsm.common.Const;
<<<<<<< HEAD
=======
import com.toyota.tsm.common.Const.Iprocess;
>>>>>>> origin/master
import com.toyota.tsm.common.Const.Irole;
import com.toyota.tsm.common.SpManager;
import com.toyota.tsm.common.ViewHub;
import com.toyota.tsm.common.Const.Istatus;
import com.toyota.tsm.model.TabModel;
import com.toyota.tsm.model.TitleDataModel;
import com.toyota.tsm.model.WorkModel;
import com.toyota.tsm.pageindicator.TabPageIndicator;
import com.toyota.tsm.pageradapter.BasePagerAdapter;
import com.zxing.activity.CaptureActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
<<<<<<< HEAD
=======
import android.app.AlertDialog;
>>>>>>> origin/master
import android.app.LauncherActivity.IconResizer;
import android.app.Service;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.InputType;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.zcproject.library.controls.LoadingDialog;
import com.zcproject.library.helper.KeyboardUtil;

@SuppressLint("SimpleDateFormat")
public class RegActivity extends FragmentActivity implements OnClickListener {

	private EditText mBarcode, mCarnum, mTime;
	private RegActivity vThis = this;
	private Activity myacvity = this;
	private final int REQUEST_OPEN_CAMERA = 1;
	private Button mBtnSave;

	private Context ctx;
	private Activity act;
	private KeyboardUtil keyboard = null;

	private CheckBox mRadwating, mRadself;
	private TabPageIndicator indicator;
	private int index = 0;
	private BasePagerAdapter adapter;
	private List<TitleDataModel> list;
	private LoadingDialog mLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);

		initview();
		initkeyboard();
		inittab();
		inittitlebar();
		showornot(String.valueOf(SpManager.getauth(vThis)));
<<<<<<< HEAD
		new Thread(new MyThread()).start();
=======
		//重新赋值
		SpManager.settempauth(vThis, SpManager.getauth(vThis));
		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String LgTime = sdformat.format(date);
		// System.out.println("现在的日期是 = " + date.toString());
		mTime.setText(LgTime);
		// new Thread(new MyThread()).start();
>>>>>>> origin/master
	}

	private void inittitlebar() {
		TextView title = (TextView) findViewById(R.id.tv_title);
		title.setText("销售店作业时间分析系统");

<<<<<<< HEAD
=======
	}

>>>>>>> origin/master
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	final Handler handler = new Handler() { // handle
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				mTime.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Date date = new Date();
						SimpleDateFormat sdformat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String LgTime = sdformat.format(date);
						// System.out.println("现在的日期是 = " + date.toString());
						mTime.setText(LgTime);
					}
				});
			}
			super.handleMessage(msg);
		}
	};

	public class MyThread implements Runnable { // thread
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000); // sleep 1000ms
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				} catch (Exception e) {
				}
			}
		}
	}

	// 初始化
	private void inittab() {

		final List<TabModel> tabs = new ArrayList<TabModel>();
		int page = 0;
		for (TitleDataModel item : Const.Getmapbyauth(SpManager.getauth(vThis))
				.values()) {
			TabModel hotspotTab = new TabModel();
			hotspotTab.setFragment(TitleFragment.newInstance(
					item.getResoureid(), item.getTitlename()));
			tabs.add(hotspotTab);
			// 页面分配
			Const.mappage.put(item.getAuth(), page);

			Const.pagetemp.put(page, item.getAuth());

			page++;
		}

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		adapter = new BasePagerAdapter(getSupportFragmentManager());

		adapter.setTabs(tabs);
		pager.setAdapter(adapter);

		TitleDataModel model1 = Const.map.get(String.valueOf(SpManager
				.getauth(vThis)));
		pager.setOffscreenPageLimit(Const.map.size());

		pager.setCurrentItem(Const.mappage.get(String.valueOf(SpManager
				.getauth(vThis))));
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				// Toast.makeText(vThis, Const.mappage.values()., 100).show();

				// Toast.makeText(vThis, Const.pagetemp.get(arg0), 100).show();

				// SpManager.setauth(vThis,
				// Integer.parseInt(Const.pagetemp.get(arg0)));

				// 滑动的情况下 只改动临时的 权限
				SpManager.settempauth(vThis,
						Integer.parseInt(Const.pagetemp.get(arg0)));

				// 显示与否
				showornot(Const.pagetemp.get(arg0));
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	// 显示与否
	private void showornot(String status) {
		/*
		 * switch (status) { case Irole.SA: mRadwating.setEnabled(true);
		 * mRadself.setEnabled(true); default: mRadwating.setEnabled(false);
		 * mRadself.setEnabled(false); break; }
		 */
		mRadwating.setChecked(false);
		mRadself.setChecked(false);
		if (status.equals(Irole.SA)) {
			mRadwating.setEnabled(true);
			mRadself.setEnabled(true);
<<<<<<< HEAD
=======
		} else if (status.equals("155"))

		{
			mRadwating.setEnabled(true);
			mRadself.setEnabled(true);
>>>>>>> origin/master
		} else {
			mRadwating.setEnabled(false);
			mRadself.setEnabled(false);
		}
	}

	// 初始化键盘
	private void initkeyboard() {
		ctx = this;
		act = this;
		ViewHub.hideSoftInputMethod(mCarnum, myacvity);
		final KeyboardView kview = (KeyboardView) act.getParent().findViewById(
				R.id.keyboard_view);

		mCarnum.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				keyboard = new KeyboardUtil(act, ctx, mCarnum, kview);
				keyboard.showKeyboard();

				return false;
			}
		});
	}

	// 初始化
	private void initview() {
		mLoading = new LoadingDialog(vThis);
		mBarcode = (EditText) vThis.findViewById(R.id.reg_barcode_edit);
		ViewHub.hideSoftInputMethod(mBarcode, myacvity);

<<<<<<< HEAD
		hideSoftInputMethod(mBarcode);

		mBarcode.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (hasFocus) {
					// TODO Auto-generated method stub
					mBarcode.setFocusable(true);
					// TODO Auto-generated method stub
					Intent intent = new Intent(vThis, CaptureActivity.class);
					startActivityForResult(intent, REQUEST_OPEN_CAMERA);
					Vibrator vib = (Vibrator) vThis
							.getSystemService(Service.VIBRATOR_SERVICE);
					vib.vibrate(50);

					if (keyboard != null) {
						keyboard.hideKeyboard();
					}
				}

=======
		mBarcode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				Intent intent = new Intent(vThis, CaptureActivity.class);
				startActivityForResult(intent, REQUEST_OPEN_CAMERA);
				Vibrator vib = (Vibrator) vThis
						.getSystemService(Service.VIBRATOR_SERVICE);
				vib.vibrate(50);

				if (keyboard != null) {
					keyboard.hideKeyboard();
				}
>>>>>>> origin/master
			}
		});

		mCarnum = (EditText) vThis.findViewById(R.id.reg_carnum_edit);

		mTime = (EditText) vThis.findViewById(R.id.reg_time_edit);
		mTime.setInputType(InputType.TYPE_NULL);

		mRadwating = (CheckBox) vThis.findViewById(R.id.rad_wating);
		mRadself = (CheckBox) vThis.findViewById(R.id.rad_booking);
		mRadwating.setOnCheckedChangeListener(new MyCheckedChangeListener());
		mRadself.setOnCheckedChangeListener(new MyCheckedChangeListener());

		// check按钮时间
		mRadwating.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				SpManager.setwaiting(vThis, isChecked);
			}
		});
		// check按钮时间
		mRadself.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub

				SpManager.setappointment(vThis, isChecked);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 处理扫描结果（在界面上显示）
		if (requestCode == REQUEST_OPEN_CAMERA && data != null) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			mBarcode.setText(scanResult);
			mBarcode.setError(null);

			SpManager.setcatnum(vThis, mCarnum.getText().toString().trim());
			SpManager.setnum(vThis, scanResult);
			SpManager.settime(vThis, mTime.getText().toString().trim());

			new task().execute();

		}
	}

	// 作业开始
	public class task extends AsyncTask<Object, Object, Object> {

		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (mLoading.isShowing()) {
				mLoading.stop();
			}

			if (result instanceof String
					&& ((String) result).startsWith("error")) {
				Toast.makeText(vThis, (String) result, Toast.LENGTH_SHORT)
						.show();
			} else {

				// 这里成功

				Toast.makeText(vThis, "作业已经开始", Toast.LENGTH_SHORT).show();
				// 跳转
				/*
				 * TabActivity ac = (TabActivity) getParent();
				 * ac.getTabHost().setCurrentTab(1);
				 */

<<<<<<< HEAD
=======
				WorkModel model = (WorkModel) result;
				if (!model.getPlatenumber().isEmpty()) {
					SpManager.setcatnum(vThis, model.getPlatenumber());
				}

>>>>>>> origin/master
				Intent itemlist = new Intent(vThis, ItemActivity.class);
				startActivity(itemlist);
				finish();

			}

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mLoading.start("作业正在开始。。。");
		}

		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO Auto-generated method stub

			try {

				int process = 1;

<<<<<<< HEAD
				switch (String.valueOf(SpManager.getauth(vThis))) {
				case Irole.CT:
					process = 1;
=======
				switch (String.valueOf(SpManager.gettempauth(vThis))) {
				case Irole.CT:
					process = 2;
>>>>>>> origin/master
					break;
				case Irole.TC:
					process = 3;
					break;
				case Irole.FM:
					process = 7;
					break;
				case Irole.PS:
					process = 5;
					break;
				case "155":
					process = 6;
					break;

				default:
					break;
				}

				WorkModel model = UserAPI.insertwork(
						SpManager.getdlrcode(vThis), SpManager.getnum(vThis),
						SpManager.getcatnum(vThis), SpManager.gettime(vThis),
<<<<<<< HEAD
						Istatus.workstart, SpManager.getauth(vThis),
						SpManager.getUsername(vThis), String.valueOf(process),
						SpManager.getwaiting(vThis),
						SpManager.getappointment(vThis),vThis);
=======
						Istatus.workstart, SpManager.gettempauth(vThis),
						SpManager.getUsername(vThis), String.valueOf(process),
						SpManager.getwaiting(vThis),
						SpManager.getappointment(vThis), vThis);
>>>>>>> origin/master
				// SpManager.setprocess(vThis, model.getProcessType());
				return model;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
<<<<<<< HEAD
			
=======

>>>>>>> origin/master
				return "error" + e.getMessage();
			}

		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.reg_barcode_edit:

			break;

		default:
			break;
		}
	}

	private class MyCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			switch (buttonView.getId()) {
			case R.id.rad_wating:

				break;
			case R.id.rad_booking:

				break;

			default:
				break;
			}
		}
	}

}
