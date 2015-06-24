package com.toyota.tsm;

import com.toyota.tsm.api.UserAPI;
import com.toyota.tsm.common.Const;
import com.toyota.tsm.common.SpManager;
import com.toyota.tsm.common.ViewHub;
import com.toyota.tsm.model.UserModel;
import com.zcproject.library.controls.LoadingDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends FragmentActivity implements OnClickListener {

	private Button mBtnlogin;
	private LoginActivity vThis = this;
	private LoadingDialog mLoading = null;
	private EditText mUsername;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mBtnlogin = (Button) findViewById(R.id.btn_login);
		mBtnlogin.setOnClickListener(this);
		mUsername = (EditText) findViewById(R.id.et_user_name);
		mLoading = new LoadingDialog(vThis);

		mUsername.setText(SpManager.getUsername(vThis) != null ? SpManager
				.getUsername(vThis) : "");
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btn_login:

			if (mUsername.getText().toString().trim().length() == 0) {
				ViewHub.seterror("请输入用户名", mUsername);
				return;
			} else {
				new task().execute();

			}

			break;

		default:
			break;
		}
	}

	public class task extends AsyncTask<Object, Object, Object> {

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

				UserModel model = (UserModel) result;

				SpManager.setShopInfo(vThis, model);

				// Intent itemlist = new Intent(vThis, MainActivity.class);
				if (model.getAuth() == 18) {
					Intent itemlist = new Intent(vThis, MoveActivity.class);
					startActivity(itemlist);
				} else {
					Intent itemlist = new Intent(vThis, MainActivity.class);
					startActivity(itemlist);
				}

			}

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mLoading.start("正在登陆中。。。");
		}

		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO Auto-generated method stub

			try {
				return UserAPI.getlogin(mUsername.getText().toString().trim(),
						vThis);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error" + e.getMessage();
			}

		}

	}

}
