package com.tencent.wxcloudrun.config;


import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableConfigurationProperties(WxProperties.class)
//@ConditionalOnClass(WxPayService.class)
//@ConditionalOnProperty(prefix = "wx.pay", value = "enabled", matchIfMissing = true)
public class WxPayAutoConfiguration {


//    @Qualifier("wxProperties")
    @Autowired
    private WxPayPaletteConfig properties;

    @Autowired
    public WxPayAutoConfiguration(WxPayPaletteConfig properties){
        this.properties = properties;
    }

    /**
     * 构造微信支付服务对象.
     *
     * @return 微信支付service
     */
    @Bean
    @ConditionalOnMissingBean(WxPayService.class)
    public WxPayService wxPayService() {
        final WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(this.properties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(this.properties.getMchId()));
        //以下是apiv3以及支付分相关
        payConfig.setPrivateKeyPath(StringUtils.trimToNull(this.properties.getPrivateKeyPath()));
        payConfig.setPrivateCertPath(StringUtils.trimToNull(this.properties.getPrivateCertPath()));
        payConfig.setCertSerialNo(StringUtils.trimToNull(this.properties.getCertSerialNo()));
        payConfig.setApiV3Key(StringUtils.trimToNull(this.properties.getApiv3Key()));

        wxPayService.setConfig(payConfig);

        return wxPayService;
    }
}
