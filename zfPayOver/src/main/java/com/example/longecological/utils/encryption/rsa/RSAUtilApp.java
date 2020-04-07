package com.example.longecological.utils.encryption.rsa;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.utils.encryption.md5.MD5Utils;

public class RSAUtilApp {

	public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    //加密最大长度（1024位密钥）
    private static final int maxEncryptLength = 117;

    //解密最大长度（1024位密钥）
    private static final int maxDecryptLength = 128;
    

    //解密base64
    public static byte[] decryptBASE64(String key) {
        return Base64.decode(key);
    }

    //加密base64
    public static String encryptBASE64(byte[] bytes) {
        return Base64.encode(bytes);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        System.out.println("解密transformation："+keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance(/*keyFactory.getAlgorithm()*/"RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return shardingProcess(cipher,maxDecryptLength,data);
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key)
            throws Exception {
        return decryptByPrivateKey(decryptBASE64(data),key);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        System.out.println("解密transformation："+keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm()/*"RSA/ECB/PKCS1Padding"*/);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return shardingProcess(cipher,maxDecryptLength,data);
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte data[], String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密

        Cipher cipher = Cipher.getInstance(/*keyFactory.getAlgorithm()*/"RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return shardingProcess(cipher,maxEncryptLength,data);
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        System.out.println("加密transformation："+keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance(/*keyFactory.getAlgorithm()*//*"RSA/ECB/PKCS1Padding"*/"RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return shardingProcess(cipher,maxEncryptLength,data);
    }



    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
        return keyMap;
    }

    private static byte[] shardingProcess(Cipher cipher, int MaxLength, byte[] data) throws Exception{
        //分段加密
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length = data.length;
        int offset = 0;
        int i = 0;
        byte[] cache;
        while(length - offset > 0){
            if(length - offset > MaxLength){  //加密数据长度大于最大加密长度
                cache = cipher.doFinal(data,offset,MaxLength);
            }else{
                cache = cipher.doFinal(data,offset,length-offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MaxLength;
        }
        cache = out.toByteArray();
        out.close();
        return cache;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Key> keyMap = initKey();
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCK1Gp19GyVp3MRSjaTNd29YM3n0MfCfkoulSd1HvQl8fN/aCN3oevSy6Evv9+RH6zXyXfUHvqD9f+LNxh6kC9R9ShnmLhe7isb3KrNKlpOCpp8zAlH2a24ddaRqN4acH/PxfM4W+Z4c4+EhBgMLsSI+FTGDVlcImBuHtbaFLHa+QIDAQAB";
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIrUanX0bJWncxFKNpM13b1gzefQx8J+Si6VJ3Ue9CXx839oI3eh69LLoS+/35EfrNfJd9Qe+oP1/4s3GHqQL1H1KGeYuF7uKxvcqs0qWk4KmnzMCUfZrbh11pGo3hpwf8/F8zhb5nhzj4SEGAwuxIj4VMYNWVwiYG4e1toUsdr5AgMBAAECgYBoXlqOxKthMpqh8+3la8iAl6cUUHPstq6kKIOaXCPl5uhM7whC5tr4BRfvYsr7Ohnhu9c3A/cHf8eWbFwdwAmh8haKGJons1evdaE6OFy7pbBqJSa67Thii+rsHULZD17Llq1VIMm/dA9wm3peZLvesNZETjBMIdCFUBkTRCb4hQJBAM5HrLorMfLnYKVB0ZL2rU8NwgzJlsMLa1IIjUccTJdib9I0SF3eK+olHO4ryM+6X1WaDyAThh35nd/nGbocDf8CQQCsSsip9OQLDEPyViiYgwGZStscG0XYDVrhsa2buTKqDj6PdtGIbssXOCKpXcezmPNkLOPT+HNprDsbqLsldIcHAkBnLExFNVOfRYD+RwRWeFDIqH31PWLNnUmJV0OqXIomcZKyVXoYkQXv764fpgCjfXkvbIXCKmG7Xl1LXuBsSJnrAkA9gPi/Cwe5FejPgog9YU7ZzQoIr4ewR5wwwz+pPmRipnEYv56u6HYhVM2yziJy71+7W/b8fU2d5Hi5N/OOvFlTAkBsHmLXxlM/GAuXeTZ4l0rqKGDt3SmeDE3+x0M3e76gKxMvB4N8G6BNK9/P3YDeVIOXIPHoTNBPz3IM7q6evaLz";
        publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKai2MWCcCzrw7HAd4hGlipvU2EnLe+VeAwSfzsymHJ5o6D9g+jxtCTvvkukN8mQi9LoHmjW0JPiL/DWSSuTSkx6uDhPVvs8eUcvjXDyXwfeo8M+rfld/S3TTs7v315kWtxEx8j2QTkGMTqD0iVgtwxR1qzkZnTJe5s97fJB8vsQIDAQAB";
        privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIpqLYxYJwLOvDscB3iEaWKm9TYSct75V4DBJ/OzKYcnmjoP2D6PG0JO++S6Q3yZCL0ugeaNbQk+Iv8NZJK5NKTHq4OE9W+zx5Ry+NcPJfB96jwz6t+V39LdNOzu/fXmRa3ETHyPZBOQYxOoPSJWC3DFHWrORmdMl7mz3t8kHy+xAgMBAAECgYBQqW/Byd1yFWg8SilQYlnH0+Qyjq3CoywLLIVLz6zzxnBqRbGPQ3ZvPfgtYx7RDZ26IdAxu1k/eaaLXBKwLp4uS7BaXh8YWuyKxxBhPLphBRAhKO5gROiVT1qt3HnYDbbM2/Ot2rwEx7RR8S6mI8usjd3ufto06ecYH2Aa7IuAAQJBANZD2WYS1yUPhE5MxfACvz5m2BoiqsGJDwhZ9yMURiWxM1rnk9QnaUjRFN2PfwOwcR1crEfdDRLQ9p5JrMxfaIECQQClYB3Z3IvxPygkfXc9oAstz0fVd+xqUafk1dS3YHyLZJMq3X813K6LZaMqVtNaLCENdejoznYwlHMIw+M3bK8xAkAtnvCw/R319CHVkh8rBUq2/KrSlcMJxib/PjiSrYRqDhrDJj+hkmqmaG4q5pzCqlTLTaoY+wYHlZqNBV7XfREBAkAsCH2qRqkUYUalAKtBQ132opxz4GyYmoljyvPKp+xBiVQOM8/tXYc0IqnhWiEIf3uFhAGyC1c49C31BYN4fH3hAkEAxZMfcJDPdOSYD0xdmnwv2mXMGDzf4Pl06ap0clV0a1NYEaxOAjnWhr2bsCJL+17kwI51JilX9+4NfL0s29BBFQ==";

        
        Map<String, String> keyMap1 = new HashMap<>();
//        keyMap1.put("token", "3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076");
//        keyMap1.put("cash_money", "800");
//        keyMap1.put("card_id", "1452");
//        keyMap1.put("pay_password", "hr2013125118");
        
        keyMap1.put("login_type", "account");
        keyMap1.put("sys_user_account", "18772101525");
        keyMap1.put("login_password", MD5Utils.MD5Encode("hr2013125118").toUpperCase());
        
        
        /*keyMap1.put("sys_user_account", "1661605308@qq.com");
        keyMap1.put("sys_user_account", "11065131");
        keyMap1.put("bus_type", "FrontForgetPass");
        keyMap1.put("img_id", "20190613182504453982631");
        keyMap1.put("img_code", "KWSS");*/
        
        
        
        /*keyMap1.put("user_account", "18772101525");
        keyMap1.put("bus_type", "FrontRegister");
        keyMap1.put("img_id", "20190529175340009344090");
        keyMap1.put("img_code", "4GMT");*/
        /*keyMap1.put("login_type", "token");*/
        //keyMap1.put("token", "5|1201ZJFESFR3GUBYJIVK3O5AECZL5SBB");
       /* keyMap1.put("bus_type", "FrontModifyPayPass");
        keyMap1.put("img_id", "20190529175340009344090");
        keyMap1.put("img_code", "4GMT");*/
        //keyMap1.put("nonce", "5a88b9dae5123e8af3cc83ccf7d35ab0");
//        String code = EncryptionUtils.getSh1ParamsCode(keyMap1,publicKey);
//        System.out.println("sign:"+code);
        //keyMap1.put("sign", "CE16D45793819AAF9F0EC45046DCBB3DFF3F4078");
//        String map2json = JsonUtil.map2json(keyMap1);
        String map2json = JSONObject.toJSONString(keyMap1);
        System.out.println(map2json);
//        byte[] passStr = encryptByPrivateKey(map2json.getBytes(),privateKey);
        byte[] passStr = encryptByPublicKey(map2json.getBytes(),SysSecurityKeyConstant.publicKey_app);
        System.out.println("加密"+passStr);

        //String hexString = bytesToHexString(passStr);
        String hexString = Base64.encode(passStr);
        System.out.println("加密String---"+hexString);
        
//        String hexString;
//        hexString = "024BB0F4F51736BBF9C9839113F2082405461EC2D36184FDCC13993B28D612B4008403708CA484257AF455E34701A6C6360F223AF7D0F3B4DFF149E315B2521421A502552929F93730312F4AEF91594F8AC1572DDDDF8ACCFFACA428053A25DFE7F50EBF8D4CA590CAE246583F001A6661723B1B558FECF84E886D1DA3004EE11C73B1C3F60A0C209A7C46B688848A7812DE56ED782138FF0356297330637A02558F427BA2BB87ABD2D5FEC5EE0EA35CAE686D39FFF7297F8AD4E06DB63C4B3E7C4F0EABCFFCB30F106B30C73B5DA56D5A2593BB8CB3F140ACAD3F451C0613BCD4DE936D67D0A12DE1127C665D69974D547AEF4773AF40BDEF08003A5F99826C";
//        hexString = "899B86396EFE8C4B3DF734ACF7500C21E887FB5F55E5D51F33CCFF4E0A74C6224F550ED5BDDABF1FF0B71C69725187C5CA68AA7AE26534711BEDDAE510D7AEBAD3FAF2516F77055073F7BC744BB9ED03A1D368F66D206643B85207797531C7514CED4D151C89B3723C82AA9D9E33B2DA55F50ED0BF567EC0005BA944790ADD35624BD76D5236E7B7673E5A6EB2CCCA0113BB49946676DDDCCA6C58007599806D98B23A7ED01A2530EFC1C968D1BAEF155B7FF63D5EDBC59F10DF995558DAD4DC47F48AB3D691A7066A9F7687FFE83EC870246FADA5DE4590101919A0F919553436D1669C0603619A6ABCE5096EC65CCF68BAAE06B6245BF1091A7AEF3643D1F2";
//        hexString = "06F4ADAA4ED7E559882881764E39891CFAD2E69FEB1D59F90550AB7B2BD939A1E7821CC31125FB0EF4DD5939FA817D9A1A8C4B2162E1AD77AFFDF757D2A6B924F87DB3047DFE2B5EBACBF28A163A336A8AC44D9DE583B66830F5DD5B464C60E1BFB8F99BBC5DD8F2417975CCACFF9AA9D99231B055EEE4D6961DC400103B53E4";
//        hexString = "81841C1F3CA2AEF9918F2368035B2499C841D35E15E8E8CEA571A109724499975831422C7A719A72A5C8E15AF470A5D795A818B1F9C9F83EF94E43E2A75D7636CB5D7AFE35E7AED9DA4269D87DE538D30B3CBC2859907F3D00B3413303A74F64B92DE83AD4065C9357F13D3BEE6D681DD00531F5B04EB7AE722F8DA889B64203";
        //byte[] hexStringToBytes = hexStringToBytes(hexString);
        byte[] hexStringToBytes = Base64.decode(hexString);
        System.out.println("加密byte[]---"+hexStringToBytes);
//        byte[] decryptByPrivateKey = decryptByPublicKey(hexStringToBytes,publicKey);
        byte[] decryptByPrivateKey = decryptByPrivateKey(hexStringToBytes,SysSecurityKeyConstant.privateKey_app);
        System.out.println("解密："+new String(decryptByPrivateKey));

        /****/
       /* String aesPassword = "AAAAAAAAAAAAAAAAAAAZD1OF4UGZD1OF4UGZD1OF4UGZD1OF4UGZD1OF4UGZD1OF4UGZD1OF4UGZD1OF4UGZD1OF4UGZD1OF4UGZD1OF4UGZD1O" +
                "F4UGZD1OF4UGZD1OF4UGZD1OFABCDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD";

        //私钥加密，并将加密后的数据进行私钥签名
        byte[] passStr = encryptByPrivateKey(aesPassword.getBytes(),privateKey);
        String sign = sign(passStr,privateKey);
        //客户端收到数据，用公钥进行签名校验，校验通过后，用公钥进行解密
        System.out.println("publicKey:" + publicKey);
        System.out.println("privateKey:" + privateKey);
        System.out.println("sign:" + sign);
        System.out.println("passStr:" + passStr);

        boolean verify = verify(passStr,publicKey,sign);
        if(verify){
            System.out.println("verify pass");
            byte[] data = decryptByPublicKey(passStr,publicKey);
            System.out.println("data:" + new String(data));
        }else{
            System.out.println("verify default");
        }*/
        
    }
    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7'
            , '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    public static String bytesToHexString(byte[] data) {
        String string = "";
        try {
            char[] chars = new char[data.length << 1];
            //十六进制数一个四位，byte一个八位
            //data存在负数 java内部采用补码形式 无符号右移会出问题
            for (int i = 0, j = 0; i < data.length; i++) {
                chars[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
                chars[j++] = DIGITS[data[i] & 0x0F];
            }
            string = new String(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static byte[] hexStringToBytes(String data) {
        //两个四位十六进制字符合成一个八位byte
        byte[] bytes = new byte[data.length() / 2];
        char[] chars = data.toCharArray();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((hexCharToByte(chars[i * 2]) << 4) | hexCharToByte(chars[i * 2 + 1]));
        }
        return bytes;
    }
    
    private static byte hexCharToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
