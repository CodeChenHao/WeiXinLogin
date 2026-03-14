# 微信扫码登录学习项目

## 项目简介

本项目是一个基于Spring Boot实现的微信扫码登录示例，展示了如何通过微信开放平台的OAuth2.0协议实现网页端的微信扫码登录功能。

## 技术栈

- Spring Boot 2.x
- Hutool（二维码生成）
- FastJSON（JSON解析）
- Apache HttpClient（HTTP请求）
- Lombok（简化代码）

## 功能说明

1. **微信平台校验接口**：用于微信平台配置时的验证
2. **生成微信登录二维码**：通过Hutool生成包含授权链接的二维码
3. **处理微信登录回调**：接收微信返回的code并获取用户信息

## 配置说明

在 `src/main/resources/application.yml` 文件中配置以下信息：

```yaml
server:
  port: 80
tencent:
  webchat:
    app-id: 你的微信公众号appId
    app-secret: 你的微信公众号appSecret
    domain: 你的域名（例如：http://example.com）
    redirect-uri: /webchat/login
```

## 快速开始

1. **克隆项目**

```bash
git clone https://github.com/你的用户名/WeiXinLogin.git
cd WeiXinLogin
```

2. **配置微信公众号信息**

在 `application.yml` 文件中填写你的微信公众号相关信息。

3. **启动项目**

```bash
mvn spring-boot:run
```

4. **访问测试**

- 生成二维码：访问 `http://localhost/webchat/qrcode`
- 扫码登录：使用微信扫描生成的二维码
- 查看结果：登录成功后，控制台会输出用户信息

## 项目结构

```
WeiXinLogin/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/chenhao/weixinlogin/
│   │   │       ├── WeiXinLoginApplication.java       # 应用主类
│   │   │       └── demos/
│   │   │           ├── entity/                        # 实体类
│   │   │           │   ├── WebChatAccount.java        # 微信账号配置
│   │   │           │   ├── WebChatRequestDto.java     # 微信请求参数
│   │   │           │   ├── WebChatToken.java          # 微信Token
│   │   │           │   └── WebChatUser.java           # 微信用户信息
│   │   │           ├── utils/
│   │   │           │   └── HttpRequestUtils.java      # HTTP请求工具
│   │   │           └── web/
│   │   │               └── BasicController.java       # 控制器
│   │   └── resources/
│   │       ├── static/
│   │       │   └── index.html                        # 静态页面
│   │       └── application.yml                       # 配置文件
│   └── test/
│       └── java/com/chenhao/weixinlogin/
│           └── WeiXinLoginApplicationTests.java      # 测试类
├── .gitignore
├── README.md
└── pom.xml
```

## 使用流程

1. **生成二维码**：调用 `/webchat/qrcode` 接口生成微信登录二维码
2. **用户扫码**：用户使用微信扫描二维码并确认登录
3. **回调处理**：微信会重定向到配置的 `redirect-uri`，并携带 `code` 参数
4. **获取Token**：使用 `code` 调用微信API获取 `access_token` 和 `openid`
5. **获取用户信息**：使用 `access_token` 和 `openid` 获取用户详细信息

## 注意事项

1. **微信公众号配置**：
   - 需要在微信公众平台（https://mp.weixin.qq.com/）注册并获取appId和appSecret
   - 需要在公众号后台配置回调域名，确保与 `application.yml` 中的 `domain` 一致

2. **本地测试**：
   - 由于微信回调需要域名，本地测试时可以使用内网穿透工具（如ngrok）
   - 或者在微信公众平台的测试账号中进行测试

3. **安全考虑**：
   - 本项目仅作为学习示例，实际生产环境中需要添加更多安全措施
   - 例如：state参数的验证、access_token的存储和管理等

## 参考文档

- [微信公众平台开发文档](https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html)
- [Spring Boot官方文档](https://spring.io/projects/spring-boot)

## 许可证

本项目仅供学习使用，无特定许可证。