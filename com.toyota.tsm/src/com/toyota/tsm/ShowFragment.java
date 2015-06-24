package com.toyota.tsm;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.toyota.tsm.api.UserAPI;
import com.toyota.tsm.common.Const.Iprocess;
import com.toyota.tsm.common.Const.Istatus;
import com.toyota.tsm.common.SpManager;
import com.toyota.tsm.common.Const.Irole;
import com.toyota.tsm.model.WorkModel;
import com.zcproject.library.controls.LoadingDialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD
=======
import android.widget.EditText;
>>>>>>> origin/master
import android.widget.TextView;
import android.widget.Toast;

//逻辑处理

@SuppressLint("NewApi")
public class ShowFragment extends Fragment implements OnClickListener {

	private int mauth;
	// private Activity vThis = getActivity();
	private LoadingDialog mLoading;
	private int process = 1;
	private int status = Istatus.workend;
	private Button btnappend, btnstopappend, benstop, btnresumed, btnover;

<<<<<<< HEAD
	// private TextView v;
=======
	private TextView v;
>>>>>>> origin/master

	public static ShowFragment newInstance(int rid) {
		ShowFragment fragment = new ShowFragment();

		Bundle args = new Bundle();
		args.putInt("rid", rid);

		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		mauth = getArguments().getInt("rid");
		View view = null;
		switch (String.valueOf(mauth)) {
		case Irole.SA:
			view = inflater.inflate(R.layout.framg_sa_jd, container, false);
			break;
		case Irole.CT:
			view = inflater.inflate(R.layout.framg_ct_by, container, false);
			break;
		case Irole.FM:
			view = inflater.inflate(R.layout.framg_fm_zj, container, false);
			break;
		case Irole.TC:
			view = inflater.inflate(R.layout.framg_tc_zy, container, false);
			break;
		case Irole.PS:
			view = inflater.inflate(R.layout.framg_sa_xiche, container, false);
			break;
		case "155":
			view = inflater.inflate(R.layout.framg_sa_jc, container, false);
			mauth = 9;
			break;
		default:
			view = inflater.inflate(R.layout.framg_sa_jd, container, false);
		}
		btnevent(view);
<<<<<<< HEAD
		// v = (TextView) getActivity().findViewById(R.id.my_car_number);
=======
		v = (TextView) getActivity().findViewById(R.id.my_car_number);
>>>>>>> origin/master

		mLoading = new LoadingDialog(getActivity());
		return view;
	}

	private void btnevent(View view) {
		Button btninsert = (Button) view.findViewById(R.id.Btn_Insert);
		// 移交专用
		Button btnremove = (Button) view.findViewById(R.id.Btn_Remove);

		btnappend = (Button) view.findViewById(R.id.btn_apptime);
		btnstopappend = (Button) view.findViewById(R.id.btn_stopapptime);
		benstop = (Button) view.findViewById(R.id.btn_stopresumed);
		btnresumed = (Button) view.findViewById(R.id.btn_resumed);
		btnover = (Button) view.findViewById(R.id.btn_over);

		Button btncheckpre = (Button) view.findViewById(R.id.btn_checkpre);
		Button btncheckend = (Button) view.findViewById(R.id.btn_checkend);
		Button btnnotice = (Button) view.findViewById(R.id.btn_notice);
		Button btncatstart = (Button) view.findViewById(R.id.btn_catstart);
		Button btncat0ver = (Button) view.findViewById(R.id.btn_catover);
		if (btncat0ver != null)
			btncat0ver.setOnClickListener(this);
		if (btncheckpre != null)
			btncheckpre.setOnClickListener(this);

		if (btncheckend != null)
			btncheckend.setOnClickListener(this);
		if (btnnotice != null)
			btnnotice.setOnClickListener(this);
		if (btncatstart != null)
			btncatstart.setOnClickListener(this);

		if (btninsert != null)
			btninsert.setOnClickListener(this);

		if (btnremove != null)
			btnremove.setOnClickListener(this);

		if (btnappend != null)
			btnappend.setOnClickListener(this);

		if (btnstopappend != null)
			btnstopappend.setOnClickListener(this);

		if (benstop != null)
			benstop.setOnClickListener(this);

		if (btnresumed != null)
			btnresumed.setOnClickListener(this);

		if (btnover != null)
			btnover.setOnClickListener(this);

	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		// 实施交车前检查
		case R.id.btn_checkpre:
			status = Istatus.checkcat;
			process = 6;
			dialogs("确认交车前检查", new transferTask(), arg0);
			break;
		// 实施交车前检查
		case R.id.btn_checkend:
			status = Istatus.finishcheckcat;
			process = 6;
			dialogs("确认完成交车前检查", new transferTask(), arg0);
			break;
		// 实施交车前检查
		case R.id.btn_notice:
			status = Istatus.notice;
			process = 6;
			dialogs("确认通知客户交车", new transferTask(), arg0);
			break;
		// 实施交车前检查
		case R.id.btn_catstart:
			status = Istatus.catstart;
			process = 6;
			dialogs("确认交车开始", new transferTask(), arg0);
			break;

		case R.id.btn_catover:
			status = Istatus.catover;
			process = 6;
<<<<<<< HEAD
=======
			// 这里还要判断是否已经输入了车牌
			EditText tx = (EditText) getActivity().findViewById(
					R.id.my_car_number);

			if (tx.getText().toString().length() == 0) {
				Toast.makeText(getActivity(), "请输入车牌号", Toast.LENGTH_LONG)
						.show();
				return;
			}
>>>>>>> origin/master
			dialogs("确认交车结束", new transferTask(), arg0);
			break;

		// 追加时间
		case R.id.btn_apptime:

			status = Istatus.appendstart;
			process = 3;
			dialogs("确认发行追加时间", new transferTask(), arg0);
			break;

		// 追加时间
		case R.id.btn_stopapptime:

			status = Istatus.appendend;
			process = 3;
			dialogs("确认SA答复追加时间", new transferTask(), arg0);
			break;

		// 中断作业
		case R.id.btn_stopresumed:

			status = Istatus.workstop;
			process = 3;
			dialogs("确认中断作业", new transferTask(), arg0);
			break;

		// 恢复作业
		case R.id.btn_resumed:

			status = Istatus.workresumed;
			process = 3;
			dialogs("确认恢复作业", new transferTask(), arg0);
			break;

		case R.id.btn_over:

			status = Istatus.workend;
			process = 3;
			dialogs("确认作业结束", new transferTask(), arg0);
			break;

		// 完成专用
		case R.id.Btn_Insert:
			status = Istatus.workend;
<<<<<<< HEAD
			switch (String.valueOf(SpManager.getauth(getActivity()))) {
			case Irole.CT:
				process = 2;
=======
			switch (String.valueOf(SpManager.gettempauth(getActivity()))) {
			case Irole.CT:
				process = 2;
				// 这里还要判断是否已经输入了车牌
				EditText tx1 = (EditText) getActivity().findViewById(
						R.id.my_car_number);

				if (tx1.getText().toString().length() == 0) {
					Toast.makeText(getActivity(), "请输入车牌号", Toast.LENGTH_LONG)
							.show();
					return;
				}

>>>>>>> origin/master
				dialogtask("确认派工结束", new Task(), arg0);
				break;
			case Irole.TC:
				process = 3;
				dialogtask("确认作业结束", new Task(), arg0);
				break;
			case Irole.FM:
				process = 7;
				dialogtask("确认作业结束", new Task(), arg0);
				break;
			case Irole.PS:
				process = 5;
				dialogtask("确认洗车结束", new Task(), arg0);
				break;
			case Irole.SA:
				process = 1;
				dialogtask("确认接待结束", new Task(), arg0);
				break;

			default:
				break;
			}

			break;

		case R.id.Btn_Remove:
			status = Istatus.worktransfer;
<<<<<<< HEAD
			switch (String.valueOf(SpManager.getauth(getActivity()))) {
=======
			switch (String.valueOf(SpManager.gettempauth(getActivity()))) {
>>>>>>> origin/master
			case Irole.CT:
				process = 3;
				dialogtask("确认移交保养", new Task(), arg0);
				break;
			case Irole.TC:
				process = 7;
				dialogtask("确认移交质检", new Task(), arg0);
				break;
			case Irole.FM:
				process = 5;
				dialogtask("确认移交洗车", new Task(), arg0);
				break;
			case Irole.SA:
				process = 2;
				dialogtask("确认移交调度", new Task(), arg0);
				break;
			case Irole.PS:
				process = 6;
				dialogtask("确认移交SA（交车）", new Task(), arg0);
				break;

			default:
				break;
			}

			break;

		default:
			break;
		}
	}

	protected void dialogs(String message, final transferTask mytask,
			final View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle(message);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@SuppressLint("ResourceAsColor")
			public void onClick(DialogInterface dialog, int whichButton) {
				mytask.execute();
				if (status == Istatus.workend) {
					view.setBackgroundColor(R.color.gray_btn_bg);
					view.setEnabled(false);
				}

				if (process == Integer.parseInt(Iprocess.delivery)) {
					view.setBackgroundColor(R.color.gray_btn_bg);
					view.setEnabled(false);
				}
				// 追加作业时间
				if (process == 3 && status == 5) {
					btnstopappend.setVisibility(View.VISIBLE);
					btnappend.setVisibility(View.GONE);
				}
				// sa答复追加意见
				if (process == 3 && status == 6) {
					btnstopappend.setVisibility(View.GONE);
					btnappend.setVisibility(View.VISIBLE);
				}

				// 中断作业
				if (process == 3 && status == 3) {
					btnresumed.setVisibility(View.VISIBLE);
					benstop.setVisibility(View.GONE);
				}

				// 恢复作业
				if (process == 3 && status == 4) {
					benstop.setVisibility(View.GONE);
					btnresumed.setVisibility(View.VISIBLE);
				}

			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// 这里添加点击确定后的逻辑

			}
		});
		builder.create().show();
	}

	public class transferTask extends AsyncTask<Object, Object, Object> {

		@Override
		protected void onPreExecute() {
			// TODO 自动生成的方法存根
			super.onPreExecute();
			mLoading.start("操作中。。");
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (mLoading.isShowing()) {
				mLoading.stop();
			}

			if (result instanceof String
					&& ((String) result).startsWith("error")) {
				Toast.makeText(getActivity(), (String) result,
						Toast.LENGTH_SHORT).show();
			} else {

				// 这里成功

				Toast.makeText(getActivity(), "成功完成操作", Toast.LENGTH_SHORT)
						.show();
				WorkModel model = (WorkModel) result;
				// v.setText("车牌号：" + model.getPlatenumber());
				// 跳转
				if (status == Istatus.catover) {

					Intent itemlist = new Intent(getActivity(),
							MainActivity.class);
					startActivity(itemlist);
					getActivity().finish();
				}
			}
		}

		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO 自动生成的方法存根
			Date date = new Date();
			SimpleDateFormat sdformat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			try {
				return UserAPI.insertwork(SpManager.getdlrcode(getActivity()),
						SpManager.getnum(getActivity()),
<<<<<<< HEAD
						SpManager.getcatnum(getActivity()),
						sdformat.format(date), status,
						SpManager.getauth(getActivity()),
						SpManager.getUsername(getActivity()),
						String.valueOf(process),
						SpManager.getwaiting(getActivity()),
						SpManager.getappointment(getActivity()),getActivity());
=======
						v.getText().toString(), sdformat.format(date), status,
						SpManager.gettempauth(getActivity()),
						SpManager.getUsername(getActivity()),
						String.valueOf(process),
						SpManager.getwaiting(getActivity()),
						SpManager.getappointment(getActivity()), getActivity());
>>>>>>> origin/master
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error" + e.getMessage();
			}

		}

	}

	// 提醒
	protected void dialogtask(String message, final Task mytask, final View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle(message);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@SuppressLint("ResourceAsColor")
			public void onClick(DialogInterface dialog, int whichButton) {
				mytask.execute();
				view.setBackgroundColor(R.color.gray_btn_bg);
				view.setEnabled(false);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// 这里添加点击确定后的逻辑

			}
		});
		builder.create().show();
	}

	// （完成移交）专用
	public class Task extends AsyncTask<Object, Object, Object> {

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (mLoading.isShowing()) {
				mLoading.stop();
			}

			if (result instanceof String
					&& ((String) result).startsWith("error")) {
				Toast.makeText(getActivity(), (String) result,
						Toast.LENGTH_SHORT).show();
			} else {

				// 这里成功

				Toast.makeText(getActivity(), "成功完成操作", Toast.LENGTH_SHORT)
						.show();
				WorkModel model = (WorkModel) result;
				// v.setText("车牌号：" + model.getPlatenumber());
				// 特效跳转
<<<<<<< HEAD
				if (String.valueOf(SpManager.getauth(getActivity())).equals(
						"155")
						&& status == Istatus.workstop) {

=======
				if (String.valueOf(SpManager.gettempauth(getActivity()))
						.equals("155") && status == Istatus.workstop) {

					Intent itemlist = new Intent(getActivity(),
							MainActivity.class);
					startActivity(itemlist);
					getActivity().finish();
				}

				// 调度 完成跳转
				if (String.valueOf(SpManager.gettempauth(getActivity()))
						.equals(Irole.CT)
						|| String.valueOf(SpManager.gettempauth(getActivity()))
								.equals(Irole.PS)) {
>>>>>>> origin/master
					Intent itemlist = new Intent(getActivity(),
							MainActivity.class);
					startActivity(itemlist);
					getActivity().finish();
				}

				if (status == Istatus.worktransfer) {

					Intent itemlist = new Intent(getActivity(),
							MainActivity.class);
					startActivity(itemlist);
<<<<<<< HEAD
=======

					getActivity().finish();
>>>>>>> origin/master
				}

			}

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			mLoading.start("操作中。。");
		}

		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			Date date = new Date();
			SimpleDateFormat sdformat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			try {

				// 作业,移交 完成
				return UserAPI.insertwork(SpManager.getdlrcode(getActivity()),
<<<<<<< HEAD
						SpManager.getnum(getActivity()),
						SpManager.getcatnum(getActivity()),
						sdformat.format(date), status,
						SpManager.getauth(getActivity()),
						SpManager.getUsername(getActivity()),
						String.valueOf(process),
						SpManager.getwaiting(getActivity()),
						SpManager.getappointment(getActivity()),getActivity());
=======
						SpManager.getnum(getActivity()), v.getText().toString()
								.trim(), sdformat.format(date), status,
						SpManager.gettempauth(getActivity()),
						SpManager.getUsername(getActivity()),
						String.valueOf(process),
						SpManager.getwaiting(getActivity()),
						SpManager.getappointment(getActivity()), getActivity());
>>>>>>> origin/master
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return "error" + e.getMessage();

			}

		}

	}

}
