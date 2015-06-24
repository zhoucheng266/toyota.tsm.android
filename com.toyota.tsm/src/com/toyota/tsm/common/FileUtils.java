package com.toyota.tsm.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.zcproject.library.helper.SDCardHelper;

//import com.zcproject.library.helper.SDCardHelper;

import android.graphics.Bitmap;

public class FileUtils {

	/**
	 * @description 写文件
	 * @created 2015-1-5 上午9:24:43
	 * @author ZZB
	 */
	public static void writeFile(String content) {
		PrintWriter pw = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
			String date = sdf.format(new Date());
			String folderPath = SDCardHelper.getSDCardRootDirectory()
					+ "/tsm/logs";
			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String filePath = folderPath + "/" + date + "-log.txt";
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath, true), "utf-8")), true);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = sdf1.format(new Date());
			pw.append("==================" + time
					+ "==============================\r\n");
			pw.println(content);
			pw.flush();
			pw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * @description 写文件
	 * @created 2015-1-5 上午9:24:43
	 * @author ZZB
	 */
	public static void writeFilepost(String content) {
		PrintWriter pw = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
			String date = sdf.format(new Date());
			String folderPath = SDCardHelper.getSDCardRootDirectory()
					+ "/tsm/post";
			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String filePath = folderPath + "/" + date + "-log.txt";
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath, true), "utf-8")), true);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = sdf1.format(new Date());
		
			pw.println(content);
			pw.flush();
			pw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @description 把错误写到文件中
	 * @created 2014-12-23 上午10:33:28
	 * @author ZZB
	 */
	public static void writeErrorToFile(Exception e) {
		PrintWriter pw = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
			String date = sdf.format(new Date());
			String folderPath = SDCardHelper.getSDCardRootDirectory()
					+ "/tsm/logs";
			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();

			}
			String filePath = folderPath + "/" + date + "-log.txt";
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath, true), "utf-8")), true);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = sdf1.format(new Date());
			pw.append("==================" + time
					+ "==============================\r\n");
			e.printStackTrace(pw);
			pw.flush();
			pw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @description 保存bitmap
	 * @created 2014-11-19 下午5:23:03
	 * @author ZZB
	 */
	public static void saveBitmap(String savePath, Bitmap bitmap)
			throws Exception {
		saveBitmap(savePath, bitmap, 100);
	}

	/**
	 * @description 保存bitmap
	 * @param quality
	 *            0-100, 100是压缩最好的质量
	 * @created 2014-11-19 下午5:23:03
	 * @author ZZB
	 */
	public static void saveBitmap(String savePath, Bitmap bitmap, int quality)
			throws Exception {
		File f = new File(savePath);
		File parentFile = f.getParentFile();
		if (parentFile != null && !parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!f.exists()) {
			f.createNewFile();
		}
		FileOutputStream fileOut = new FileOutputStream(f);
		bitmap.compress(Bitmap.CompressFormat.PNG, quality, fileOut);
		fileOut.flush();
	}

}