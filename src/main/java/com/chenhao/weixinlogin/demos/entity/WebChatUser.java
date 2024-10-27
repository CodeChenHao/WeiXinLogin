package com.chenhao.weixinlogin.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信用户信息对象
 */
@AllArgsConstructor
@NoArgsConstructor
public class WebChatUser {

    // 微信号标识
    private String openid;
    // 微信用户名
    private String nickname;
    // 性别
    private String sex;
    // 省份
    private String province;
    // 城市
    private String city;
    // 国家
    private String country;
    // 头像
    private String headimgurl;
    private String unionid;
}
