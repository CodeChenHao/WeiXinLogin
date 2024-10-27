package com.chenhao.weixinlogin.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信平台申请的临时账号信息
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("tencent.webchat")
public class WebChatAccount {

    private String appId;
    private String appSecret;
    private String domain;
    private String redirectUri;
}
