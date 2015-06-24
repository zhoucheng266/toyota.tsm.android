package com.toyota.tsm.api;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.toyota.tsm.common.FileUtils;
import com.toyota.tsm.common.GsonHelper;
import com.toyota.tsm.common.StringUtils;
import com.toyota.tsm.model.ReturnDataModel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class HttpUtils {

	private static final String TAG = "HttpUtils";

	private static final String URL = "";

	private static final String SERVERURL = URL;

	private static final String SERVERURL_V1 = "";

	private static final String SERVER_404 = "未找到服务器地址。\n请稍后再试";
	private static final String SERVER_UNKONW_ERROR = "访问失败。\n请稍后再试";
	private static final String SERVER_CONNECT_ERROR = "访问服务器失败。\n请稍后再试";
	private static final String SERVER_500 = "啊哦，服务器出现问题了，正在维护中";
	private static final String SERVER_403 = "服务器拒绝您的访问。\n请稍后再试";
	private static final String SERVER_ERROR = "啊哦，访问服务器失败了呢。\n请稍后再试";
	private static final String SERVER_TIME_OUT = "读取数据失败，请检查您的网络是否正常";
	private static final String SERVER_NO_NETWORK = "您未连接网络，请接入网络后再试";
	private static final String SERVER_NOT_FOUND_SERVER = "您的网络访问不到服务器，请切换另一个网络试试";
	private static final String SERVER_401="扫描码重复";

	private static final int REQUEST_TIMEOUT = 20 * 1000;// 设置请求超时10秒钟
	private static final int SO_TIMEOUT = 60 * 1000; // 设置等待数据超时时间10秒钟

	public static String get(String url) throws IOException {
		HttpGet httpRequest = new HttpGet(url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse httpResponse = httpclient.execute(httpRequest);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			String strContent = EntityUtils.toString(httpResponse.getEntity(),
					"UTF-8");
			return strContent;
		} else {
			throw new RuntimeException(statusCode + "");
		}

	}

	/**
	 * @description http get请求，没有预设host
	 * @created 2014-12-19 下午4:57:53
	 * @author ZZB
	 */
	public static String get(String url, Map<String, String> params)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue())
					.append("&");
		}

		String result = httpGet(url,
				StringUtils.deleteEndStr(sb.toString(), "&"), "", false);
		return result;
	}

	public static String httpGet(String method, Map<String, String> params)
			throws Exception {
<<<<<<< HEAD
		
=======

>>>>>>> origin/master
		String result = httpGet(method, params, "");
		return result;

	}

	public static String httpGet(String method, Map<String, String> params,
			String cookie) throws Exception {
<<<<<<< HEAD
		
=======

>>>>>>> origin/master
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey());
			sb.append("=");
			if (method.contains("pay/")) {
				if (entry.getKey().equals("user_name")
						|| entry.getKey().equals("desc")
						|| entry.getKey().equals("trade_name")
						|| entry.getKey().equals("buyer_user_name")
						|| entry.getKey().equals("seller_user_name")
						|| entry.getKey().equals("buyer_order_url")
						|| entry.getKey().equals("seller_order_url")) {

					sb.append(URLEncoder.encode(entry.getValue(), "utf-8"));
				} else {
					sb.append(entry.getValue());
				}
			} else {
				sb.append(entry.getValue());
			}
			sb.append("&");
		}

		String result = httpGet(method,
				StringUtils.deleteEndStr(sb.toString(), "&"), cookie, true);
		return result;

	}

	/**
	 * @description get请求
	 * @time 2014-8-13 下午2:12:30
	 * @author ZZB
	 */
	public static String httpGet(String method, String params, String cookie,
			boolean useDefaultHost) throws Exception {

		String url = "";
		String host = useDefaultHost ? SERVERURL : "";
		url = host + method + "?" + params;

		Log.d(TAG, "get-url:" + url);
		HttpGet httpRequest = new HttpGet(url);
		if (!TextUtils.isEmpty(cookie)) {
			httpRequest.addHeader("Cookie", cookie);
		}
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			switch (statusCode) {
			case 200:
				String strContent = EntityUtils.toString(httpResponse
						.getEntity());

				return getReturnDataJson(strContent);
			case 500:
				throw new Exception(SERVER_500 + statusCode);
			case 404:
				throw new Exception(SERVER_404 + statusCode);
			case  401:
				throw new Exception(SERVER_401 + statusCode);
			case 403:
				throw new Exception(SERVER_403 + statusCode);
			default:
				throw new Exception(SERVER_ERROR + statusCode);
			}
		} catch (ClientProtocolException e) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", TAG, method,
					"ClientProtocolException"));
			throw new Exception("连接服务器异常:ClientProtocolException");
		} catch (IOException e) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", TAG, method,
					"IOException"));
			throw new Exception("连接服务器异常:IOException");
		} catch (JSONException ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", TAG, method,
					"数据转换失败"));
			throw new Exception("数据转换失败");
		} catch (Exception e) {
			if (e.getMessage().contains("Unable to resolve host")) {
				Log.e(TAG, e.getMessage());
				throw new Exception(SERVER_NOT_FOUND_SERVER);
			} else {
				throw e;
			}
		}
	}

	/**
	 * 将Json格式的字符串转换成所需的Json数据
	 * 
	 * @param json
	 *            Json格式字符串
	 * */
	public static String getReturnDataJson(String json) throws Exception {
		try {
			String jsonData = "";
			ResultData resultData = GsonHelper.jsonToObject(json,
					ResultData.class);

			if (resultData.getStatus() == 1
					&& resultData.getReturnval() != null) {
				jsonData = GsonHelper.objectToJson(resultData.getReturnval());
			} else {
				throw new Exception(resultData.getError_msg());

			}

			return jsonData;
		} catch (Exception ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", TAG,
					"getReturnDataJson", ex.getMessage()));
			ex.printStackTrace();
			throw ex;
		}
	}

	class ResultData {
		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getError_msg() {
			return error_msg;
		}

		public void setError_msg(String error_msg) {
			this.error_msg = error_msg;
		}

		public Object getReturnval() {
			return returnval;
		}

		public void setReturnval(Object returnval) {
			this.returnval = returnval;
		}

		@Expose
		private int status;
		@Expose
		private String error_msg;
		@Expose
		private Object returnval;

	}

	public static String httpPost(String website, String method,
			Map<String, String> params) throws Exception {
		String result = httpPost(website, method, params, true, "");
		return result;
	}

	public static String httpPost(String method, Map<String, String> params)
			throws Exception {
		String result = httpPost("", method, params, false, "");
		return result;
	}

	public static String httpPost(String method, Map<String, String> params,
			String cookie) throws Exception {
		String json = httpPost("", method, params, false, cookie);
		return json;
	}

	public static String httpPostWithJson(String method,
			Map<String, String> params, String cookie) throws Exception {
		String json = httpPost("", method, params, true, cookie);
		return json;
	}

	/**
	 * Http Post请求，并返回JSON格式的数据
	 * 
	 * @param url
	 *            网址
	 * @param method
	 *            方法名
	 * @param params
	 *            参数
	 * @param isJson
	 *            表示参数的格式：true-json格式，false-普通url格式
	 * @param cookie
	 *            cookie值
	 * */
	private static String httpPost(String website, String method,
			Map<String, String> params, boolean isJson, String cookie)
			throws Exception {

		try {

			// API服务地址
			String url = "";
			if (!TextUtils.isEmpty(website)) {
				url = website + method;
				if (params != null && params.size() > 0)
					url += "?";
			} else {
				url = SERVERURL + method + "?";
			}
			
			// Post请求
			HttpPost request = new HttpPost(url);
			//FileUtils.writeFilepost("url:" + url + "\r\n");
			for (Map.Entry<String, String> keyValue : params.entrySet()) {
				String key = keyValue.getKey();
				String value = keyValue.getValue();
				// paramJson.put(key, value);

				FileUtils.writeFilepost(key + ":"+value+"\r\n");
			

			}
			FileUtils.writeFilepost("--------------------------");
			// cookie值不为空的情况下传递cookie值
			if (!TextUtils.isEmpty(cookie)) {
				request.addHeader("Cookie", cookie);
			}

			if (isJson == true) {
				// Json格式作为参数
				request.addHeader("content-type", "application/json");
				if (params != null && params.size() > 0) {
					JSONObject paramJson = new JSONObject();
					for (Map.Entry<String, String> keyValue : params.entrySet()) {
						String key = keyValue.getKey();
						String value = keyValue.getValue();
						paramJson.put(key, value);
					}
					StringEntity entity = new StringEntity(
							paramJson.toString(), HTTP.UTF_8);
					request.setEntity(entity);
				}
			} else {
				request.addHeader("content-type",
						"application/x-www-form-urlencoded");
				if (params != null && params.size() > 0) {
					List<NameValuePair> paramList = new ArrayList<NameValuePair>();
					for (Map.Entry<String, String> keyValue : params.entrySet()) {
						String key = keyValue.getKey();
						String value = keyValue.getValue();
						BasicNameValuePair object = new BasicNameValuePair(key,
								value);
						paramList.add(object);
					}
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
							paramList, HTTP.UTF_8);
					request.setEntity(entity);
					Log.d(TAG, "entity：" + params.toString());
				}
			}
			// 发送请求
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,
					REQUEST_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
			HttpProtocolParams.setUseExpectContinue(httpParams, false);
			HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {

				@Override
				public boolean retryRequest(IOException arg0, int arg1,
						HttpContext arg2) {
					// TODO Auto-generated method stub
					// retry a max of 5 times
					if (arg1 >= 3) {
						return false;
					}

					return false;
				}

			};
			httpClient.setHttpRequestRetryHandler(retryHandler);
			HttpResponse httpResponse = httpClient.execute(request);

			// 获取状态码
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			Log.v(TAG, "statusCode + " + statusCode);
			if (statusCode == 200) {
				// 获取请求返回的内容
				String strContent = EntityUtils.toString(httpResponse
						.getEntity());
				// strContent = URLDecoder.decode(strContent, HTTP.UTF_8);
				// 将json字符串序列化为对象
				String strJosn = getReturnDataJson(strContent);

				return strJosn;
			} else {
				throw new Exception(SERVER_ERROR + statusCode);
			}
		} catch (ConnectTimeoutException ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", "post",
					method, "连接服务器超时"));
			throw new Exception(SERVER_TIME_OUT);
		} catch (ConnectException ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", "post",
					method, "无法连接到服务器"));
			throw new Exception(SERVER_ERROR);
		} catch (JSONException ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", "post",
					method, "数据转换失败"));
			throw new Exception("数据转换失败");
		} catch (Exception e) {
			if (e.getMessage().contains("Unable to resolve host")) {
				Log.e(TAG, e.getMessage());
				throw new Exception(SERVER_NOT_FOUND_SERVER);
			} else {
				throw e;
			}
		}
		// catch (Exception ex) {
		// // if(FunctionHelper.IsNetworkOnline(PublicData.mainActivity))
		// // {
		// // Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", "post",
		// method, ex.getMessage()));
		// // ex.printStackTrace();
		// // throw ex;
		// // }
		// // else{
		// // Exception networkOfflineEx = new Exception("您还没有连接网络哟");
		// // throw networkOfflineEx;
		// // }
		// }
	}
}
