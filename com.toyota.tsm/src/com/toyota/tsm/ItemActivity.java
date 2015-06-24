package com.toyota.tsm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.toyota.tsm.api.UserAPI;
import com.toyota.tsm.common.Const.Iprocess;
import com.toyota.tsm.common.Const.Irole;
import com.toyota.tsm.common.Const.Istatus;
import com.toyota.tsm.common.Const;
import com.toyota.tsm.common.SpManager;
import com.toyota.tsm.common.ViewHub;
import com.toyota.tsm.model.TabModel;
import com.toyota.tsm.model.TitleDataModel;
import com.toyota.tsm.model.UserModel;
import com.toyota.tsm.pageradapter.BasePagerAdapter;
import com.zcproject.library.controls.LoadingDialog;
import com.zcproject.library.helper.KeyboardUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends FragmentActivity implements OnClickListener {
	private List<TitleDataModel> list;
	private BasePagerAdapter adapter;
	private TextView mTnumber, mTtime;
	private EditText mTcatnum;
	private ItemActivity vThis = this;
	// private Button mBtnInsert;
	private LoadingDialog mLoading;
	private TextView mShow;
	private ShowFragment f;
<<<<<<< HEAD
=======
	private Context ctx;
	private Activity act;
	private KeyboardUtil keyboard = null;
>>>>>>> origin/master

	// private LinearLayout ll1;
	// private RelativeLayout ll2;
	//
	// private Button btnAppend;
	// private Button btnResumed;
	// private Button btnOver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		inittab();
		initview();
		setDefaultFragment();

		inittitlebar();
		initkeyboard();
	}

	private void inittitlebar() {
		TextView title = (TextView) findViewById(R.id.tv_title);
		title.setText("作业进行中");

	}

	@SuppressLint("NewApi")
	private void setDefaultFragment() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
<<<<<<< HEAD
		f = ShowFragment.newInstance(SpManager.getauth(vThis));
		transaction.replace(R.id.id_fragment_lj, f);
		transaction.commit();
	}

	private void initview() {

=======
		f = ShowFragment.newInstance(SpManager.gettempauth(vThis));
		transaction.replace(R.id.id_fragment_lj, f);
		transaction.commit();
	}

	// 初始化键盘
	private void initkeyboard() {
		ctx = this;
		act = this;
		ViewHub.hideSoftInputMethod(mTcatnum, act);

		int auth = SpManager.gettempauth(vThis);
		showkeyboard(auth);
	}

	// 判断是否选择弹出
	private void showkeyboard(int auth) {
		final KeyboardView kview = (KeyboardView) act
				.findViewById(R.id.keyboard_view);
		switch (String.valueOf(auth)) {
		case Const.Irole.CT:
		case "155":

			mTcatnum.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					keyboard = new KeyboardUtil(act, ctx, mTcatnum, kview);
					keyboard.showKeyboard();

					return false;
				}
			});
			break;

		default:
			mTcatnum.setFocusable(false);
			break;

		}
	}

	private void initview() {

>>>>>>> origin/master
		mShow = (TextView) findViewById(R.id.tv_title_show);

		mLoading = new LoadingDialog(vThis);
		mTnumber = (TextView) findViewById(R.id.my_number);
		mTcatnum = (EditText) findViewById(R.id.my_car_number);
		mTtime = (TextView) findViewById(R.id.my_time_s);

		mTnumber.setText(SpManager.getnum(vThis) != null ? "条形码号："
				+ SpManager.getnum(vThis) : "");
		mTtime.setText(SpManager.gettime(vThis) != null ? "作业开始时间："
				+ SpManager.gettime(vThis) : "");
		mTcatnum.setText(SpManager.getcatnum(vThis) != null ? SpManager
				.getcatnum(vThis) : "");

		RadioButton radiobtn = (RadioButton) findViewById(R.id.main_rdo_menu1);
		radiobtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
			}
		});

		switch (String.valueOf(SpManager.gettempauth(vThis))) {
		case Irole.FM:
			mShow.setText("质检中");

			break;
		case Irole.TC:
<<<<<<< HEAD

=======
			mShow.setText("作业中");
>>>>>>> origin/master
			break;
		case Irole.CT:
			mShow.setText("派工中");

<<<<<<< HEAD
			break;
		case Irole.SA:
			mShow.setText("接待中");

			break;
		default:
			mShow.setText("作业中");

=======
			break;
		case Irole.PS:
			mShow.setText("洗车中");
			break;
		case Irole.SA:
			mShow.setText("接待中");
			break;

		case "155":
			mShow.setText("交车中");
			break;
		default:
			mShow.setText("作业中");
>>>>>>> origin/master
			break;
		}

	}

	// 初始化
	private void inittab() {
		TitleDataModel model1 = Const.map.get(String.valueOf(SpManager
				.gettempauth(vThis)));
		final List<TabModel> tabs = new ArrayList<TabModel>();

		TabModel hotspotTab = new TabModel();

		hotspotTab.setFragment(TitleFragment.newInstance(model1.getResoureid(),
				model1.getTitlename()));

		tabs.add(hotspotTab);

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		adapter = new BasePagerAdapter(getSupportFragmentManager());

		adapter.setTabs(tabs);
		pager.setAdapter(adapter);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
