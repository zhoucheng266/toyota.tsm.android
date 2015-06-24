package com.toyota.tsm.common;

import com.toyota.tsm.model.UserModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Description: 对SharedPreference进行统一管理 2014-7-4下午3:46:34
 */
public class SpManager {
	private static final String SAVE_USERNAME = "save_username";

	private static final String SAVE_DLRCODE = "save_dlrcode";

	private static final String SAVE_AUTH = "save_auth";

	private static final String SAVE_ST = "save_station";
<<<<<<< HEAD
	private static final String SAVE_PROCESS="save_process";
	
	private static final String SAVE_WAITING="save_waiting";
	private static final String SAVE_APPOINTMENT="save_appointment";
=======
	private static final String SAVE_PROCESS = "save_process";

	private static final String SAVE_WAITING = "save_waiting";
	private static final String SAVE_APPOINTMENT = "save_appointment";
>>>>>>> origin/master
	private static final String RETURN_NUM = "return_num";
	private static final String RETURN_CATNUM = "return_catnum";
	private static final String RETURN_TIME = "return_time";
	private static final String RETURN_TEMPAUTH = "return_tempauth";

	public static void setnum(Context context, String station) {
		setString(context, RETURN_NUM, station);
	}

	public static String getnum(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(RETURN_NUM, "");
	}

	public static void setcatnum(Context context, String station) {
		setString(context, RETURN_CATNUM, station);
	}

	public static String getcatnum(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(RETURN_CATNUM, "");
	}

	public static void settime(Context context, String station) {
		setString(context, RETURN_TIME, station);
	}

	public static String gettime(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(RETURN_TIME, "");
	}
	
	public static String getprocess(Context context) {
		SharedPreferences sp=PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(SAVE_PROCESS, "1");
	}
	
	public static void setprocess(Context context,String process) {
		setString(context, SAVE_PROCESS, process);				
	}
	
	public static boolean getwaiting(Context context) {
		SharedPreferences spPreferences=PreferenceManager
				.getDefaultSharedPreferences(context);
		return spPreferences.getBoolean(SAVE_WAITING, false);
	}
	
	public static void setwaiting(Context context, boolean waiting) {
		setBoolean(context, SAVE_WAITING, waiting);
	}
	
	public static boolean getappointment(Context context) {
		SharedPreferences sp=PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getBoolean(SAVE_APPOINTMENT, false);
	}
	
	public static void setappointment(Context context,boolean appointment) {
		setBoolean(context, SAVE_APPOINTMENT, appointment);
	}

	public static String getprocess(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(SAVE_PROCESS, "1");
	}

	public static void setprocess(Context context, String process) {
		setString(context, SAVE_PROCESS, process);
	}

	public static boolean getwaiting(Context context) {
		SharedPreferences spPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return spPreferences.getBoolean(SAVE_WAITING, false);
	}

	public static void setwaiting(Context context, boolean waiting) {
		setBoolean(context, SAVE_WAITING, waiting);
	}

	public static boolean getappointment(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getBoolean(SAVE_APPOINTMENT, false);
	}

	public static void setappointment(Context context, boolean appointment) {
		setBoolean(context, SAVE_APPOINTMENT, appointment);
	}

	/**
	 * @description 保存店铺信息
	 * @created 2015-3-2 下午2:24:42
	 * @author ZZB
	 */
	public static void setShopInfo(Context context, UserModel shopinfo) {
		if (shopinfo != null) {
			setstation(context, shopinfo.getStation());
			setdlrcode(context, shopinfo.getDlrcode());
			setauth(context, shopinfo.getAuth());
			settempauth(context, shopinfo.getAuth());
			setUsername(context, shopinfo.getAccount());
		}
	}

	public static String getstation(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(SAVE_ST, "");
	}

	public static void setstation(Context context, String station) {
		setString(context, SAVE_ST, station);
	}

	public static int getauth(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getInt(SAVE_AUTH, 0);
	}

	public static void setauth(Context context, int setauth) {
		setInt(context, SAVE_AUTH, setauth);
	}

	public static int gettempauth(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getInt(RETURN_TEMPAUTH, 0);
	}

	public static void settempauth(Context context, int setauth) {
		setInt(context, RETURN_TEMPAUTH, setauth);
	}

	public static String getdlrcode(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(SAVE_DLRCODE, "");
	}

	public static void setdlrcode(Context context, String dlrcode) {
		setString(context, SAVE_DLRCODE, dlrcode);
	}

	/**
	 * Description:登陆成功，拿用户名
	 * 
	 * @author ZZB
	 */
	public static String getUsername(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(SAVE_USERNAME, "");
	}

	/**
	 * Description:登陆成功，保存用户名
	 * 
	 * @author ZZB
	 */
	public static void setUsername(Context context, String Username) {
		setString(context, SAVE_USERNAME, Username);
	}

	private static String getString(Context context, String key, String defValue) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString(key, defValue);
	}

	private static void setString(Context context, String key, String value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		sp.edit().putString(key, value).commit();
	}

	private static boolean getBoolean(Context context, String key,
			boolean defValue) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getBoolean(key, defValue);
	}

	private static void setBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		sp.edit().putBoolean(key, value).commit();
	}

	private static int getInt(Context context, String key, int defValue) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getInt(key, defValue);
	}

	private static void setInt(Context context, String key, int value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		sp.edit().putInt(key, value).commit();
	}

	private static long getLong(Context context, String key, long defValue) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getLong(key, defValue);
	}

	private static void setLong(Context context, String key, long value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		sp.edit().putLong(key, value).commit();
	}

	/**
	 * @description 删除用户信息
	 * @created 2014-8-27 上午10:02:14
	 * @author ZZB
	 */
	public static void clearUserInfo(Context context) {
		remove(context, SAVE_DLRCODE, RETURN_NUM, RETURN_CATNUM, RETURN_TIME);
	}

	private static void remove(Context context, String... keys) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor edit = sp.edit();
		for (String key : keys) {
			edit.remove(key);
		}
		edit.commit();
	}

}
