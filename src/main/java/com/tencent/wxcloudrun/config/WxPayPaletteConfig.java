package com.tencent.wxcloudrun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayPaletteConfig {
    /**
     * 设置微信公众号或者小程序等的appid.
     */
    private String appId;

    /**
     * 微信支付商户号.
     */
    private String mchId;

    /**
     * 证书序列号
     */
    private String certSerialNo;

    /**
     * apiV3秘钥
     */
    private String apiv3Key;

    /**
     * apiv3 商户apiclient_key.pem
     */
    private String privateKeyPath;

    /**
     * apiv3 商户apiclient_cert.pem
     */
    private String privateCertPath;
}
