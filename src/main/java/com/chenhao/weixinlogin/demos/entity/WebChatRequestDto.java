package com.chenhao.weixinlogin.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装微信校验接口,微信传递的参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebChatRequestDto {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;
}
