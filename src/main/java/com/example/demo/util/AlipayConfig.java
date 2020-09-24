package com.example.demo.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000116662710";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChxY33xdbii5biFhaD1OSXoVfRJQ32LByhPjd2SaQZrj7C0TF+dHCmSvEHIZgZF6FnZkGh7Y9cnI9ALgXtToNmMTnA776ReK87HZg5vVlEL1QvAWgWaDn3q6SqKAUQ5P2IX425QziJX4o3EKye99pAG38zEEtSyR/y1MOV1ZKld4J6wAOZDKnjG5nisyFoUqplqlwhBWM1mz9BiRazgvjinFOfEPmtoVcRHtH3XHmSoil0jpe1Owf5EXIBoJQaiyab/0+dB8xPPH7iM9uE/Wqce/X2vzfXrKyHUiwjJTWvNJr5im6Qx9KnP7g5/HwBp50C15ojI+PjPYZmj4Rx6ByxAgMBAAECggEAf83Tmyxx2Ajv6y4IG57V+fU9q73aieHPvJOqvYjwXFmMu90JfR4A4V/TjA6+WxSQL2BZ+dP0rvYBNnMlu5WLufCp8AN4SvVNeyamXPd6zqOVno097nXpVgIE1WlIl1BR/KuBDBxPTjhsuZQyzGSDyKHwnCcDWyQKWY499uIEOTQouJQ1PP0KYTozFFHzgdWaBxVP0eo+XcmECqdjDRr4EY6Elo3p5EyyZ4XkR/w+NhrwIricnrCHKLB/tZA0llJMK2JJFg49hLqiSuYNQhlLlvgN9d80Np4xewAlldCpvQJ1EBjJnq4tpakygzopg4mabiBAKLVcRDiIpmIMJ6nBAQKBgQD78bZO2WJ9+pr15TQLz3f7Fe36D+qR33ig/u2ikmI5kmApXmtQSEZi+si+VVGo5zPNNF5zYWYgco7of06QKgMEwM9Hjp0xQueVfelovlrzd9ZqmluH6eBiGRltEDTNGUYE9L3gVtdvfCJ7hkdDd6o9btYDAuwpJOlf//NuBVVY3wKBgQCkYDt6H0wnAw+SMqIrpmqG+1QpOud5U5CSc8HVVVmuUmrjgMr/2isPCvuEN469by7iHc+hhcOhJqt5f7XNUkcXbNY4MW0KV13lQCzmZPsAPU+bRbLn1LVPDXZKg/TXRad0s2fM7HOmJ9Y8KnftPNeRndeXZeLmIKODSAU2VifsbwKBgQCwFaOBQFWiDsC3E1Zo2rvUdOM6Xl1nTnSUsReNBBo6ep9SynbeSWVIUFZ1EFReZBjEVtGcenpSOz6+KBvUjkhFziwQqxAaGdSZmsLDPid6+kqVfwRx3XfBmCE5JJaCsiNJ23PJk2hGts+y3Yp5EO2euhRBTo2XxACH4+9S+c9IqQKBgQCZ1jJz5cyoijXNdrPNtE7oi1lFyQoPw4R/e/TBisKG4j2FM+lIsE8yIe4o3LIrWl3/YzUKDx16ua0r5bkpMsK0CrkmGCPb1M5cmsYqzgEXRUo2oHGChNE27ftyW7MOsfK5xpnz8Kt/qrLW1kws7I83igjWeZV0GMHPQN0X8GQm4wKBgFi0RMiUJUcIGm1nPqpx8pdH+lU3Vt8wl+pxw9aH6RtTCaszyzwPWS56hK5VF3aceRCn9Ufpfr9RMpBy9ge/FADPA+Qlu6NVENTO60YYpFv/06tCOhtoGzljCpWQc1+gJjAAlYW5v1+VbaXU58BBn+npVf0Aofn+m0Tv+mNHViO6";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlt+uIspKk7vTOLiop8T+LDVBSiDSd2VcmS/sOxe6BDpQDb3P5G6EXQ6+OBkPVGMwF2bIJwMj66hvjHxO65s0uH6ednU+hxCl5n/pXRu4StYcBjluVoahPJdQv96h/qXWIVKYA+YEkTcdXhTYughaPvgB+p4j8yrmMqIm3PoJJP/NSXDViqopWho1oWom86YlMHcMehVp2M87mWtwUNoFpZLKuMsiwC9CCXT/m8gsKg6kHn7DIw9uAK2opwID3WitT5hkXPy3dTX6rTo2Ok6hcYPvxvxbXgCyDPQTs7OrI9gskryIm+0Vh2DiYsMna0QLrVbfMcwvXL/UZjsXRr37jwIDAQAB+wtExfnRwpkrxByGYGRehZ2ZBoe2PXJyPQC4F7U6DZjE5wO++kXivOx2YOb1ZRC9ULwFoFmg596ukqigFEOT9iF+NuUM4iV+KNxCsnvfaQBt/MxBLUskf8tTDldWSpXeCesADmQyp4xuZ4rMhaFKqZapcIQVjNZs/QYkWs4L44pxTnxD5raFXER7R91x5kqIpdI6XtTsH+RFyAaCUGosmm/9PnQfMTzx+4jPbhP1qnHv19r8316ysh1IsIyU1rzSa+YpukMfSpz+4Ofx8AaedAteaIyPj4z2GZo+EcegcsQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

