package com.cbf.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SmsUtil {

    /**
     * 发送短信验证码
     * @param args
     * @throws ClientException
     */
//    public static void main(String[] args) throws ClientException {
//        sendSms("18305958724","123456");
//    }


    /** 区域，目前只提供两个固定值，default和cn-hangzhou */
    public static String regionId = "cn-hangzhou";

    /** 短信accessKey */
    public static String accessKey = "LTAI4GDR6uuFW7UThQLBjiPC";

    /** 短信secret */
    public static String secret = "m6maYPzUV7xhdFYEO8xL2dmh56WnZs";

    /** 域名（固定） */
    public static String domain = "dysmsapi.aliyuncs.com";

    /** 阿里云短信发送版本，官网提供不可随意更改* */
    public static String version = "2017-05-25";

    /** 短信签名 */
    private static String signName = "wmkFirstStud";

    /** 短信模板code */
    private static String templateCode = "SMS_201650354";

    /**
     * 发送短信
     *
     * @param phoneNum 手机号
     * @param code 人名
     * @return
     */
    public static void sendSms(String phoneNum, String code) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKey, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        // 需要调用方法值，单一发送固定值
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        // 阿里云短信服务中申请的签名
        request.putQueryParameter("SignName", signName);
        // 阿里云短信服务中申请的短信模板代码
        request.putQueryParameter("TemplateCode", templateCode);
        // 短信模板中的变量使用Json方式替换
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        request.putQueryParameter("TemplateParam", jsonObject.toJSONString());

            CommonResponse response = client.getCommonResponse(request);

    }
}
