package com.toyota.tsm.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toyota.tsm.common.Const.Iprocess;
import com.toyota.tsm.common.Const.Istatus;
import com.toyota.tsm.common.GsonHelper;
import com.toyota.tsm.common.Const.Irole;
import com.toyota.tsm.common.NetWorkHelper;
<<<<<<< HEAD
=======
import com.toyota.tsm.model.ListModel;
import com.toyota.tsm.model.ReturnDataModel;
>>>>>>> origin/master
import com.toyota.tsm.model.UserModel;
import com.toyota.tsm.model.WorkModel;

import android.R.bool;
import android.content.Context;

public class UserAPI {

	public static UserModel getlogin(String username, final Context context)
			throws Exception {
		NetWorkHelper.checkNetwork(context);
		String url = "http://gzmeo.vicp.cc:8099/account/getlogin";

		// String url = "http://172.16.135.47:8081/account/getlogin";

		Map<String, String> params = new HashMap<String, String>();
		params.put("account", username);
		return GsonHelper.jsonToObject(HttpUtils.get(url, params),
				UserModel.class);

	}

	public static List<ListModel> getlist(int pageindex, String dlrcode,
			final Context context) throws Exception {
		NetWorkHelper.checkNetwork(context);
		String url = "http://gzmeo.vicp.cc:8099//manhour/getlist";

		// String url = "http://172.16.135.47:8081/account/getlogin";

		Map<String, String> params = new HashMap<String, String>();
		params.put("dlrcode", dlrcode);
		params.put("pageindex", pageindex + "");
		ReturnDataModel item = GsonHelper.jsonToObject(
				HttpUtils.get(url, params), ReturnDataModel.class);

		return item.getWorklist();
	}

	public static WorkModel insertwork(String dlrcode, String barcode,
			String number, String time, int Worktype, int auth, String account,
<<<<<<< HEAD
			String processtype, boolean waiting, boolean appointment,Context context)
			throws Exception {
		
		NetWorkHelper.checkNetwork(context);
		String url = "http://gzmeo.vicp.cc:8099/manhour/InsertWork";
=======
			String processtype, boolean waiting, boolean appointment,
			Context context) throws Exception {

		NetWorkHelper.checkNetwork(context);
		String url = "http://gzmeo.vicp.cc:8099/manhour/InsertWork";
		// String url = "http://172.16.135.47:8081/manhour/InsertWork";
>>>>>>> origin/master
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date LgTime = sdformat.parse(time);
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", account);
		params.put("dlrcode", dlrcode);
		params.put("barcode", barcode);
		params.put("platenumber", number);
		params.put("time", sdformat.format(LgTime));
		params.put("Worktype", String.valueOf(Worktype));
		params.put("auth", String.valueOf(auth));
<<<<<<< HEAD
		if (String.valueOf(auth) == Irole.SA && processtype == Iprocess.receive) {
			params.put("waiting", String.valueOf("0"));
			params.put("appointment", String.valueOf("0"));
		}
=======
		if (String.valueOf(auth).equals(Irole.SA)
				&& processtype.equals(Iprocess.receive)) {
			params.put("waiting", String.valueOf(waiting));
			params.put("appointment", String.valueOf(appointment));
		}

>>>>>>> origin/master
		// if(Worktype==Istatus.worktransfer){
		params.put("processtype", processtype);
		// }
		return GsonHelper.jsonToObject(HttpUtils.httpPost(url, "", params),
				WorkModel.class);

	}
}
