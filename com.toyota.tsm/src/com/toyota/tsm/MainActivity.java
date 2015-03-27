package com.toyota.tsm;

import com.zxing.activity.CaptureActivity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private MainActivity vThis = this;
	private final int REQUEST_OPEN_CAMERA = 1;
	private TextView mTxtResult;
	private Button mBtnTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();
	}

	/**
	 * 初始化view
	 */
	private void initview() {
		mTxtResult = (TextView) findViewById(R.id.main_scan_txt);
		mBtnTest = (Button) findViewById(R.id.btn_test);
		mBtnTest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCameraIntent = new Intent(vThis,
						CaptureActivity.class);
				startActivityForResult(openCameraIntent, REQUEST_OPEN_CAMERA);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 处理扫描结果（在界面上显示）
		if (requestCode == REQUEST_OPEN_CAMERA && data != null) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			mTxtResult.setText(scanResult);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {

			Intent openCameraIntent = new Intent(vThis, CaptureActivity.class);
			startActivityForResult(openCameraIntent, REQUEST_OPEN_CAMERA);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
