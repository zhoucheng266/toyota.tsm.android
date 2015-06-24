package com.toyota.tsm.common;

import java.io.IOException;
import java.net.ConnectException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

/**
 * http 通用访问接口
 */
public class HttpUtils {
	private static final String TAG = HttpUtils.class.getSimpleName();

	private static final String SERVER_ERROR = "啊哦，访问服务器失败了呢。\n请稍后再试";
	private static final String SERVER_TIME_OUT = "连接超时";

	private static final int REQUEST_TIMEOUT = 20 * 1000; // 设置请求超时3秒钟
	private static final int SO_TIMEOUT = 20 * 1000; // 设置等待数据超时时间3秒钟
	private static final int RETRY_TIME = 3;

	/**
	 * Http Post请求
	 * 
	 * @param url
	 *            网址
	 * @param isJson
	 *            表示参数的格式：true-json格式，false-普通url格式
	 * @param cookie
	 *            cookie值
	 * */
	private static String httpPost(String url, Map<String, String> params,
			boolean isJson, String cookie) throws Exception {
		HttpResponse httpResponse = baseHttpPost(url, params, isJson, cookie);
		// 获取状态码
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Log.v(TAG, "statusCode + " + statusCode);
		if (statusCode == 200) {
			// 获取请求返回的内容
			String strContent = EntityUtils.toString(httpResponse.getEntity());
			return strContent;
		} else {
			throw new Exception(SERVER_ERROR + statusCode);
		}
	}

	public static HttpResponse baseHttpPost(String url,
			Map<String, String> params, boolean isJson, String cookie)
			throws Exception {
		boolean hasParams = (params != null && params.size() > 0);
		try {
			// API服务地址
			Log.i(TAG, "post-url：" + url + params);
			// Post请求
			HttpPost request = new HttpPost(url);
			// cookie值不为空的情况下传递cookie值
			if (!TextUtils.isEmpty(cookie)) {
				request.addHeader("Cookie", cookie);
			}

			if (isJson == true) {
				// Json格式作为参数
				request.addHeader("content-type", "application/json");
				if (hasParams) {
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
				if (hasParams) {
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
				public boolean retryRequest(IOException exception,
						int executionCount, HttpContext context) {
					// retry a max of 5 times
					if (executionCount >= RETRY_TIME) {
						return false;
					}
					if (exception instanceof NoHttpResponseException) {
						return true;
					} else if (exception instanceof ClientProtocolException) {
						return true;
					}
					return false;
				}

			};
			httpClient.setHttpRequestRetryHandler(retryHandler);
			HttpResponse httpResponse = httpClient.execute(request);
			return httpResponse;

		} catch (ConnectTimeoutException ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}发生异常：{2}", "post", url,
					"连接服务器超时"));
			throw new Exception(SERVER_TIME_OUT);
		} catch (ConnectException ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}发生异常：{2}", "post", url,
					"无法连接到服务器"));
			throw new Exception(SERVER_ERROR);
		} catch (ClientProtocolException ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}发生异常：{2}", "post", url,
					"无法连接到服务器"));
			throw ex;
		}
	}
}
