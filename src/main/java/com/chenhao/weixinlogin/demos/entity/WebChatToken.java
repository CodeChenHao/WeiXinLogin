package com.chenhao.weixinlogin.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信Token对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebChatToken {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String is_snapshotuser;
    private String unionid;
}
