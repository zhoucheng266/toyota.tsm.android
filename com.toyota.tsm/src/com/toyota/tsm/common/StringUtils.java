package com.toyota.tsm.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

public class StringUtils {
    /**\
     * @description 字符串拼接
     * @created 2015-3-19 下午6:07:56
     * @author ZZB
     */
    public static String append(String fullStr, String str, String seperator){
        if(TextUtils.isEmpty(fullStr)){
            return str;
        }else{
            fullStr += (seperator + str);
            return fullStr;
        }
    }
    /**
     * @description 判断是不是emoji表情
     * @created 2014-12-22 下午2:58:05
     * @author ZZB
     */
    public static boolean isEmojiCharacter(char codePoint) {
        boolean result = (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
        return !result;
    }
    /**
     * @description substring 如果end小于str.len,则end = str.len
     * @created 2014-11-6 下午4:52:44
     * @author ZZB
     */
    public static String substring(String str, int start, int end){
        if(str == null){
            return str;
        }
        return str.substring(start, end > str.length() ? str.length() : end);
    }
    /**
     * @description 根据一个key删除内容
     * @created 2014-10-11 上午10:07:14
     * @author ZZB
     */
    public static String deleteKV(String str, String key){
        String newStr = str.replaceAll("'" + key + "':[^,]*,", "");
        return newStr;
    }
    /**
     * @description 字符串组成 "k1":"v1","k2":"v2"，键不能重复，否则有bug
     * @created 2014-9-22 下午2:43:12
     * @author ZZB
     */
    public static String getValue(String str, String key){
        boolean empty = TextUtils.isEmpty(str);
        if(empty || !str.contains("'" + key + "':")){
            return "";
        }else {
            String[] stateArr = str.split(",");
            for(String ste : stateArr){
                if(ste.contains("'" + key + "':")){//
                    return ste.split(":")[1].replace(",", "");
                }
            }
            return "";
        }
    }
    /**
     * @description 字符串组成 "k1":"v1","k2":"v2"，键不能重复，否则有bug
     * @created 2014-9-22 下午2:43:12
     * @author ZZB
     */
    public static String getValue(String str, int key){
        return getValue(str, key + "");
    }
    /**
     * @description 字符串组成 "k1":"v1","k2":"v2"，键不能重复，否则有bug
     * @created 2014-9-22 下午2:46:11
     * @author ZZB
     */
    public static String insertOrUpdateKV(String str, String key, String value){
        boolean empty = TextUtils.isEmpty(str);
        String newValue = "'" + key + "'" + ":" + value + ",";
        if(empty || !str.contains("'" + key + "':")){//不存在，直接插入
            str += newValue;
        }
        else{
            String reg = "(?<=" + key + ".:).*?(?=,)";//存在，把值更新了
            str = str.replaceAll(reg, value);
        }
        return str;
    }
    /**
     * @description 字符串组成 "k1":"v1","k2":"v2"，键不能重复，否则有bug
     * @created 2014-9-22 下午2:46:11
     * @author ZZB
     */
    public static String insertOrUpdateKV(String str, int key, String value){
        return insertOrUpdateKV(str, key + "", value);
    }
    /**
     * Description: 删除一个字符串src最后出现的指定字符串deleteStr
     * 2014-7-13 下午10:43:17
     */
    public static String deleteEndStr(String src, String deleteStr) {
        if (!TextUtils.isEmpty(src) && !TextUtils.isEmpty(deleteStr) && src.endsWith(deleteStr)) {
            int lastIndex = src.lastIndexOf(deleteStr);
            return src.substring(0, lastIndex);
        } else {
            return src;
        }
    }
    /**
     * Description:决断去除空格后是不是空
     * 2014-7-18下午4:56:46
     * @author ZZB
     */
    public static boolean isEmptyWithTrim(String str){
        if (str == null || str.trim().length() == 0)
            return true;
        else
            return false;
    }
    
    /**
     * Description:判断在以separator拼接fullStr的字符串中，是否包含有str
     * 2014-7-24 下午7:14:29
     * @author ZZB
     */
    public static boolean contains(String fullStr, String str, String separator){
        if(TextUtils.isEmpty(fullStr) || TextUtils.isEmpty(str)){
            return false;
        }else if(fullStr.equals(str)){
            return true;
        }else if(fullStr.startsWith(str + separator) || fullStr.endsWith(separator + str)){
            //fullStr a,b, str a || fullStr a,b str b
            return true;
        }else if(fullStr.contains(separator + str + separator)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Description:以separator拼接fullStr的字符串中，包含有str则删除
     * 2014-7-24 下午7:23:51
     * @author ZZB
     */
    public static String remove(String fullStr, String str, String separator){
		if (TextUtils.isEmpty(fullStr) || TextUtils.isEmpty(str)){
            return fullStr;
        }else if(fullStr.startsWith(str + separator)){
            return fullStr.substring(str.length() + separator.length(), fullStr.length());
        }else if( fullStr.endsWith(separator + str)){
            return fullStr.substring(0, fullStr.length() - str.length() - separator.length());
        }else if(fullStr.contains(separator + str + separator)){
            return fullStr.replace(separator + str + separator, ",");
        }else{
            return fullStr;
        }
    }

    public static <T> String toString(T[] arr){
        if(arr == null || arr.length == 0){
            return "";
        }else{
            StringBuilder sb = new StringBuilder();
            for(T obj : arr){
                sb.append(obj).append(",");
            }
            return deleteEndStr(sb.toString(), ",");
        }
            
    }
    /**
     * @description 决断是否是正数
     * @created 2014-8-13 下午5:24:48
     * @author ZZB
     */
    public static boolean isPositiveNumber(String str){
        Pattern pattern=Pattern.compile("^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$");
        Matcher match=pattern.matcher(str);
        return match.matches();
    }
}