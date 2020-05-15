package com.ruoyi.common.utils.message;


import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.xml.XmlUtils;
import com.ruoyi.project.develop.common.domain.R;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class QiXinSmsUtil {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //用户名
    public static final String SPID = "313783";
    //密码
    public static final String USERPWD = "lNA5yNyv";
    //接入码
    public static final String AC = "1069313783";


    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符�?
     * @throws Exception
     */
    public static Map net(String strUrl, Map params, String method) throws Exception {
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
        return XmlUtils.xmlToMap(rs);
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
     * 发送短信验证码，中付的
     * @param account：手机号
     * @param code：验证码
     * @return
     */
    public static R sendMesg(String account, String code) {
        Map<String, Object> map=new HashMap<>();
        map.put("Mobile", account);
        map.put("Content", "【中付钱柜】您本次的验证码为 "+ code + "，验证码5分钟内有效，请妥善保管。回T退订");
        return sendMesgUtil(map);
    }


    /**
     * 给账号发送短信验证码方法封装
     * @param paraMap
     * @return
     */
    public static R sendMesgUtil(Map<String, Object> paraMap) {
        String url = "https://smsjm.jxtebie.com/sms/submit";// 请求接口地址
        Map<String, String> params = new HashMap();// 请求参数
        params.put("spid", SPID);
        params.put("password", USERPWD);
        params.put("ac", AC);
        params.put("mobiles", paraMap.get("Mobile").toString());
        params.put("content", paraMap.get("Content").toString());
        try {
            Map<String, Object> result = net(url, params, "POST");
            //System.out.println(result);
            //错误代码
            String message = StringUtil.getMapValue(result,"desc");
            //消息说明
            String code = StringUtil.getMapValue(result, "result");
            if("0".equals(code)) {
                return R.ok("操作成功");
            }else {
                return R.error(R.Type.WARN, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(R.Type.ERROR, "发送异常");
        }
    }

    public static void main(String[] args) {
        System.out.println(sendMesg("15915300343","666666"));
    }
}
