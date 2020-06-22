package com.ruoyi.common.utils.sms2.yun;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class SmsQiXinSenderUtil {

    String CorpID;
    String Pwd;
    String url = "http://101.200.29.88:8082/SendMT/SendMessage";
    //String url = "http://127.0.0.1:8080/live.kewail.com/sms/v1/sendsinglesms";

    SmsSenderUtil util = new SmsSenderUtil();

    public SmsQiXinSenderUtil(String CorpID, String Pwd) throws Exception {
        this.CorpID = CorpID;
        this.Pwd = Pwd;
    }

    /**
     * 普通单发短信接口，明确指定内容，如果有多个签名，请在内容中以【】的方式添加到信息内容中，否则系统将使用默认签名
     * @param type 短信类型，0 为普通短信，1 营销短信
     * @param nationCode 国家码，如 86 为中国
     * @param phoneNumber 不带国家码的手机号
     * @param msg 信息内容，必须与申请的模板格式一致，否则将返回错误
     * @param Cell 扩展码，可填空
     * @return {@link}SmsSingleSenderResult
     * @throws Exception
     */
    public SmsSingleSenderResult send(
            String nationCode,
            String phoneNumber,
            String msg,
            String Cell) throws Exception {
        if (null == Cell) {
            Cell = "";
        }
        // 按照协议组织 post 请求包体
        long random = util.getRandom();
        long curTime = System.currentTimeMillis()/1000;

        JSONObject data = new JSONObject();

        JSONObject tel = new JSONObject();
        tel.put("nationcode", nationCode);
        tel.put("mobile", phoneNumber);

        data.put("msg", msg);
        data.put("sig", util.strToHash(String.format(
                "CorpID=%s&random=%d&mobile=%s",
                CorpID, random, curTime, phoneNumber)));
        data.put("tel", tel);
        data.put("time", curTime);
        data.put("Cell", Cell);

        // 与上面的 random 必须一致
        String wholeUrl = String.format("%s?CorpID=%s&random=%d", url, CorpID, random);
        HttpURLConnection conn = util.getPostHttpConn(wholeUrl);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
        wr.write(data.toString());
        wr.flush();

        System.out.println(data.toString());

        // 显示 POST 请求返回的内容
        StringBuilder sb = new StringBuilder();
        int httpRspCode = conn.getResponseCode();
        SmsSingleSenderResult result;
        if (httpRspCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            System.out.println(sb.toString());
            JSONObject json = JSONObject.parseObject(sb.toString());
            result = util.jsonToSmsSingleSenderResult(json);
        } else {
            result = new SmsSingleSenderResult();
            result.result = httpRspCode;
            result.errMsg = "http error " + httpRspCode + " " + conn.getResponseMessage();
        }

        return result;
    }
}
