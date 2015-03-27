package com.toyota.tsm.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

import com.toyota.tsm.BuildConfig;
import com.toyota.tsm.common.FileUtils;

/**
 * @description 未捕获异常处理
 * @created 2014-12-23 上午11:20:06
 * @author ZZB
 */
public class UncaughtCrashHandler implements UncaughtExceptionHandler {

	public UncaughtCrashHandler() {
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		FileUtils.writeErrorToFile((Exception) ex);
		// 输出错误Log
		if (BuildConfig.DEBUG) {
			StringWriter localStringWriter = new StringWriter();
			PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
			ex.printStackTrace(localPrintWriter);

		}
		System.exit(0);
	}

}
