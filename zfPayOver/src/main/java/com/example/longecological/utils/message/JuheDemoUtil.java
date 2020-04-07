package com.example.longecological.utils.message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.utils.string.StringUtil;

import net.sf.json.JSONObject;
 

/**
*短信API服务调用示例代码 �? 聚合数据
*在线接口文档：http://www.juhe.cn/docs/54
**/
public class JuheDemoUtil {
    
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //聚合：配置您申请的KEY
    public static final String APPKEY ="253c6a976c748a0011651eea90b0f1ac";
    
    //中付短信用户名
    public static final String USERNAME = "lcw";
    //中付短息密码
    public static final String USERPWD = "904157";
    
  	
    /**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method 请求方法
    * @return  网络请求字符�?
    * @throws Exception
    */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    
    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    /**
     * 给账户发送短信验证码（聚合数据的）
     * @param account
     * @param code
     * @param ModelId 
     * @return
     */
	public static R sendMesg(String account, String code, String ModelId) {
        String result =null;
        String url ="http://v.juhe.cn/sms/send";
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("mobile",account);
        params.put("tpl_id",ModelId);
        params.put("tpl_value","#code#="+code);
        params.put("key",APPKEY);
        params.put("dtype","json");
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
            	return R.ok(CommonCodeConstant.COMMON_CODE_999999);
            }else{
            	return R.error(MsgImgCodeConstant.MESSAGE_CODE_999899, object.get("reason").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(MsgImgCodeConstant.MESSAGE_CODE_999898, MsgImgCodeConstant.MESSAGE_MSG_999898);
        }
	}
	
	
	/**
  	 * 发送短信验证码，中付的
  	 * @param tel：手机号
  	 * @param code：验证码
  	 * @return
  	 */
 	public static R sendMesg(String account, String code) {
 		Map<String, Object> map=new HashMap<>();
		map.put("mobiles", account);
		map.put("content", "尊敬用户您的验证码是 "+code +"该验证码5分钟内有效");
        return sendMesgUtil(map);
 	}
	
	
	/**
	 * 给账号发送短信验证码方法封装
	 * @param paraMap
	 * @return
	 */
	public static R sendMesgUtil(Map<String, Object> paraMap) {
 		String result = null;
 		String url = "http://sms.ue35.net/sms/interface/sendmess.htm";// 请求接口地址
 		Map<String, String> params = new HashMap();// 请求参数               
 		params.put("username", USERNAME);
 		params.put("userpwd", USERPWD);
		params.put("mobiles", paraMap.get("mobiles").toString());
		params.put("content", paraMap.get("content").toString());
 		try {
 			result = net(url, params, "POST");
 			//错误代码
 			String errorcode = StringUtil.subString(result, "<errorcode>", "</errorcode>");
 			//消息说明
 			String message = StringUtil.subString(result, "<message>", "</message>");
 			if("1".equals(errorcode)) {
 				return R.ok(CommonCodeConstant.COMMON_CODE_999999);
 			}else {
 				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999899,message);
 			}
 		} catch (Exception e) {
            e.printStackTrace();
            return R.error(MsgImgCodeConstant.MESSAGE_CODE_999898, MsgImgCodeConstant.MESSAGE_MSG_999898);
 		}
 	}
	
	
	public static void main(String[] args) {
		System.out.println(sendMesg("18772101525","666666"));
	}
	
}