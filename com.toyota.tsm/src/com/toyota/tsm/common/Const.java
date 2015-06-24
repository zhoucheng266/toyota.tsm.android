package com.toyota.tsm.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import android.R.integer;

import com.toyota.tsm.R;
import com.toyota.tsm.model.TitleDataModel;

public class Const {
	public interface Istatus {
		int workstart = 1;
		int workend = 2;
		int workstop = 3;
		int workresumed = 4;
		int appendstart = 5;
		int appendend = 6;
		int worktransfer = 7;
		int checkcat = 8;
		int finishcheckcat = 9;
		int notice = 10;
		int catstart = 11;
		int catover = 12;

	}

	public interface Irole {
		String FM = "58";
		String SA = "9";
		String SM = "10";
		String TC = "14";
		String PS = "54";
		String CT = "55";
		String TO = "18";

	}

	// 作业进程常数（接待=1,调度=2,维修=3,追加=4,洗车=5,交车=6,质检=7）
	// CreateBy 倪炅平
	public interface Iprocess {
		String receive = "1";
		String dispatch = "2";
		String maintain = "3";
		String additional = "4";
		String cleanning = "5";
		String delivery = "6";
		String quality = "7";
	}

	public static Map<String, Integer> mappage = new LinkedHashMap<String, Integer>();
<<<<<<< HEAD

	public static Map<Integer, String> pagetemp = new LinkedHashMap<Integer, String>();

	public static Map<String, TitleDataModel> map = new LinkedHashMap<String, TitleDataModel>();
=======

	public static Map<Integer, String> pagetemp = new LinkedHashMap<Integer, String>();

	public static Map<String, TitleDataModel> map = new LinkedHashMap<String, TitleDataModel>();

	public static Map<String, TitleDataModel> Getmapbyauth(int auth) {
		if (auth == 9) {
			map.clear();
			TitleDataModel model1 = new TitleDataModel();
			model1.setTitlename("服务顾问（接待）");
			model1.setResoureid(R.drawable.logo);
			model1.setAuth("9");
			TitleDataModel model2 = new TitleDataModel();
			model2.setTitlename("服务顾问（交车）");
			model2.setResoureid(R.drawable.logo);
			model2.setAuth("155");
			map.put("9", model1);
			map.put("155", model2);

		} else {
			getall();

		}

		return map;
	}
>>>>>>> origin/master

	static {
		getall();
	}

	private static void getall() {
		TitleDataModel model1 = new TitleDataModel();
		model1.setTitlename("服务顾问（接待）");
		model1.setResoureid(R.drawable.logo);
		model1.setAuth("9");
		map.put("9", model1);
		TitleDataModel model3 = new TitleDataModel();
		model3.setTitlename("技师（TC）");
		model3.setResoureid(R.drawable.logo);
		model3.setAuth("14");
		map.put("14", model3);
		TitleDataModel model5 = new TitleDataModel();
		model5.setTitlename("调度(CT)");
		model5.setResoureid(R.drawable.logo);
		model5.setAuth("55");
		map.put("55", model5);
		TitleDataModel model6 = new TitleDataModel();
		model6.setTitlename("质检（FM）");
		model6.setResoureid(R.drawable.logo);
		model6.setAuth("58");
		map.put("58", model6);
		TitleDataModel model4 = new TitleDataModel();
		model4.setTitlename("洗车");
		model4.setResoureid(R.drawable.logo);
		model4.setAuth("54");
		map.put("54", model4);
<<<<<<< HEAD
		TitleDataModel model2 = new TitleDataModel();
		model2.setTitlename("服务顾问（交车）");
		model2.setResoureid(R.drawable.logo);
		model2.setAuth("155");
		map.put("155", model2);
=======
		TitleDataModel model12 = new TitleDataModel();
		model12.setTitlename("服务顾问（交车）");
		model12.setResoureid(R.drawable.logo);
		model12.setAuth("155");
		map.put("155", model12);
>>>>>>> origin/master
	}

}
