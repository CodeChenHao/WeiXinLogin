package com.chenhao.weixinlogin.demos.web;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.alibaba.fastjson.JSON;
import com.chenhao.weixinlogin.demos.entity.WebChatAccount;
import com.chenhao.weixinlogin.demos.entity.WebChatRequestDto;
import com.chenhao.weixinlogin.demos.entity.WebChatToken;
import com.chenhao.weixinlogin.demos.entity.WebChatUser;
import com.chenhao.weixinlogin.demos.utils.HttpRequestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/webchat")
public class BasicController {

    @Autowired
    private WebChatAccount webChatAccount;

    /**
     * 微信平台校验接口,返回给微信自己生成的echostr才能在微信平台配置成功
     */
    @RequestMapping
    public String chekWebChat(WebChatRequestDto webChatRequestDto){
        return webChatRequestDto.getEchostr();
    }

    /**
     * 生成微信登录二维码
     */
    @RequestMapping("/qrcode")
    public String getQrCode(){
        String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=chenhao&forcePopup=true#wechat_redirect";
        content = content.replace("APPID",webChatAccount.getAppId()).replace("REDIRECT_URI",webChatAccount.getDomain() + webChatAccount.getRedirectUri());

        QrConfig config = new QrConfig();
        config.setWidth(200);
        config.setHeight(200);

        return QrCodeUtil.generateAsBase64(content,config,"png");
    }

    /**
     * 用户扫描二维码后的回调
     * @param code 换取微信Token的code
     */
    @GetMapping("/login")
    public String login(String code) throws IOException {
        String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        tokenUrl = tokenUrl.replace("APPID",webChatAccount.getAppId()).replace("SECRET",webChatAccount.getAppSecret()).replace("CODE", code);
        HttpResponse tokenResponse = HttpRequestUtils.doGet(tokenUrl);
        int tokenStatusCode = tokenResponse.getStatusLine().getStatusCode();
        if (tokenStatusCode == 200){
            HttpEntity tokenEntity = tokenResponse.getEntity();
            String tokenString = EntityUtils.toString(tokenEntity);
            WebChatToken webChatToken = JSON.parseObject(tokenString, WebChatToken.class);

            String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            userInfoUrl = userInfoUrl.replace("ACCESS_TOKEN",webChatToken.getAccess_token()).replace("OPENID",webChatToken.getOpenid());

            HttpResponse userInfoResponse = HttpRequestUtils.doGet(userInfoUrl);
            int userInfoStatusCode = userInfoResponse.getStatusLine().getStatusCode();
            if (userInfoStatusCode == 200){
                HttpEntity userInfoEntity = userInfoResponse.getEntity();
                String userInfoString = EntityUtils.toString(userInfoEntity);
                WebChatUser webChatUser = JSON.parseObject(userInfoString, WebChatUser.class);
                System.out.println(webChatUser);
            }

        }

        return "success";
    }

}
