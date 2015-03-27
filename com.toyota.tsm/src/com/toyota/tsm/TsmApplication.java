package com.toyota.tsm;

import com.toyota.tsm.exceptions.UncaughtCrashHandler;

import android.app.Application;

public class TsmApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		// 全局异常捕获
		new UncaughtCrashHandler();
	}

}
