package com.toyota.tsm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zcproject.library.pulltorefresh.library.PullToRefreshBase;
import com.zcproject.library.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

import com.toyota.tsm.ShowFragment.Task;
import com.toyota.tsm.adapter.MoveItemadapter;
import com.toyota.tsm.api.UserAPI;
import com.toyota.tsm.common.SpManager;
import com.toyota.tsm.common.Const.Istatus;
import com.toyota.tsm.model.ListModel;
import com.toyota.tsm.model.WorkModel;
import com.zcproject.library.controls.LoadingDialog;
import com.zcproject.library.pulltorefresh.library.PullToRefreshListView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MoveActivity extends Activity implements OnClickListener {

	private PullToRefreshListView mPullRefreshScrollview;
	private MoveActivity mContext = this;
	private ListView mListView;
	private MoveItemadapter mAdapter;
	private int mPageIndex = 1;
	private final int PAGE_SIZE = 20;
	private List<ListModel> mCachePosts;
	private boolean mIsLoadingPosts, mIsRefresh = true;
	private LoadingDialog mLoading;
	private int process = 1;
	private int status = Istatus.workend;

	private Boolean mControlShow = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_move);

		initview();
		inittitlebar();

	}

	// 初始化view
	private void initview() {
		mPullRefreshScrollview = (PullToRefreshListView) mContext
				.findViewById(R.id.list);
		mListView = mPullRefreshScrollview.getRefreshableView();

		mAdapter = new MoveItemadapter(mContext);
		mListView.setAdapter(mAdapter);
		mLoading = new LoadingDialog(mContext);

		// 第一次加载事件
		loadData(true);
		// 上拉 下拉事件
		mPullRefreshScrollview
				.setOnRefreshListener(new OnRefreshListener2<ListView>() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						mControlShow = true;
						loadData(true);

					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stubd
						loadData(false);
					}
				});

		RadioButton radiobtn = (RadioButton) findViewById(R.id.main_rdo_menu1);
		radiobtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

				builder.setTitle("是否注销");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@SuppressLint("ResourceAsColor")
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Intent intent = new Intent(mContext,
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
		registerForContextMenu(mListView);
	}

	private void loadData(boolean isRefresh) {

		mIsRefresh = isRefresh;
		if (isRefresh) {
			mPageIndex = 1;
			new Task(Step.LOAD_DATA).execute();
		} else {
			mPageIndex++;
			new Task(Step.LOAD_DATA).execute();
		}
	}

	private static enum Step {
		LOAD_DATA
	}

	private class Task extends AsyncTask<Object, Object, Object> {

		private Step mStep;

		public Task(Step step) {
			mStep = step;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mLoading.start("加载数据");
		}

		@Override
		protected Object doInBackground(Object... params) {
			try {

				switch (mStep) {
				case LOAD_DATA:
					return UserAPI.getlist(mPageIndex,
							SpManager.getdlrcode(mContext), mContext);

				}
			} catch (Exception e) {
				e.printStackTrace();
				return "error:" + e.getMessage();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {

			if (mLoading.isShowing()) {
				mLoading.dismiss();
			}

			if (result instanceof String
					&& ((String) result).startsWith("error:")) {
				Toast.makeText(mContext, (String) result, Toast.LENGTH_SHORT)
						.show();
			}
			switch (mStep) {
			case LOAD_DATA:
				onHotpostsLoaded(result);
				break;
			}
		}

	}

	private void onHotpostsLoaded(Object result) {
		mIsLoadingPosts = false;
		if (!(result instanceof List)) {

		} else {

			List<ListModel> data = (List<ListModel>) result;

			int dataSize = data.size();
			if (dataSize == 0) { // mLvData.setCanLoadMore(false);
				Toast.makeText(mContext, "没有数据啦", Toast.LENGTH_SHORT).show();
			} else {

				if (mIsRefresh) {

					/*
					 * mAdapter.addDataToHead(data); List<ShopItemListModel>
					 * newData = new ArrayList<ShopItemListModel>(new
					 * LinkedHashSet<ShopItemListModel>( mAdapter.getData()));
					 */
					mAdapter.setData(data);
				} else {
					ListModel newLastPost = data.get(dataSize - 1);
					ListModel oldLastPost = mAdapter.getLastItem();
					if (newLastPost != null && newLastPost.equals(oldLastPost)) {

					} else {
						mAdapter.addDataToTail(data);
					}
				}
				// mCachePosts = mAdapter.getData();
				mAdapter.notifyDataSetChanged();

			}
		}
		mPullRefreshScrollview.onRefreshComplete();

	}

	// 提醒
	protected void dialogtask(String message, final DoTask doTask) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

		builder.setTitle(message);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@SuppressLint("ResourceAsColor")
			public void onClick(DialogInterface dialog, int whichButton) {
				doTask.execute();

			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// 这里添加点击确定后的逻辑

			}
		});
		builder.create().show();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		final ListModel tobeDeleteCons = (ListModel) mAdapter
				.getItem(((AdapterContextMenuInfo) menuInfo).position - 1);
		if (tobeDeleteCons.getProcesstype() == 1) {
			menu.add(0, 1, 0, "移交调度");
		} else if (tobeDeleteCons.getProcesstype() == 3) {
			menu.add(0, 2, 0, "移交洗车");
			menu.add(0, 3, 0, "移交质检");
		}

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		final ListModel tobeDeleteCons = (ListModel) mAdapter
				.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position - 1);
		status = Istatus.worktransfer;
		switch (item.getItemId()) {

		case 1:
			// sa移交调度
			process = 2;
			dialogtask("确认移交调度", new DoTask(tobeDeleteCons));
			break;

		case 2:
			// 移交洗车
			process = 5;
			dialogtask("确认移交洗车", new DoTask(tobeDeleteCons));
			break;

		case 3:
			// 移交质检
			process = 7;
			dialogtask("确认移交质检", new DoTask(tobeDeleteCons));
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	// 初始化titlebar
	private void inittitlebar() {
		TextView title = (TextView) findViewById(R.id.tv_title);
		title.setText("移交列表");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	// （完成移交）专用
	public class DoTask extends AsyncTask<Object, Object, Object> {
		private ListModel mModel;

		public DoTask(ListModel model) {
			mModel = model;
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
				Toast.makeText(mContext, (String) result, Toast.LENGTH_SHORT)
						.show();
			} else {

				// 这里成功  
			     mAdapter.getData().remove(mModel);
			     mAdapter.notifyDataSetChanged();
				// v.setText("车牌号：" + model.getPlatenumber());

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

			try {

				// 作业,移交 完成
			
				return UserAPI.insertwork(SpManager.getdlrcode(mContext),
						mModel.getBarcode(), mModel.getPlatenumber(),
						mModel.getStartservice(), status,
						SpManager.gettempauth(mContext),
						SpManager.getUsername(mContext),
						String.valueOf(process), false, false, mContext);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return "error" + e.getMessage();

			}

		}

	}

}
