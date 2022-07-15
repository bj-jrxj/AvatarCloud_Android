# AvatarCloud_Android
头像云 Android SDK

# AvatarCloudSDK



* **具体内测申请流程请访问官网**



---
## 介绍

### [云头像平台](https://fc.faceface2.com)

云头像平台提供海量免费原创IP头像,包含卡通、二次元、像素、写实等丰富多元的风格，实现自定义头像、智能生成头像、保存头像等独特功能。

通过与国内知名影视、动画、潮玩等行业头部公司合作，创作出以IP形象为核心的头像，深受用户喜爱。已帮助众多知名央国企平台升级原创IP头像服务。

本平台所使用的头像及美术素材，均已签订协议并获得授权，平台内的头像可直接作为用户头像使用。如需二次编辑或衍生品开发，请联系[**云头像平台**](https://fc.faceface2.com)。



* **具体内测申请流程请访问官网: https://fc.faceface2.com**


|SDK|下载地址|集成指引|
|-|-------:|:------:|
|iOS|https://github.com/bj-jrxj/AvatarCloud_iOS|[pod集成](#SDK集成)<br>[手动集成](#手动集成)|
|Adnroid|https://github.com/bj-jrxj/AvatarCloud_Android|gradle集成|




### 安装

---


#### SDK集成

###### gradle 集成 添加依赖


```
项目build.gradle添加如下

allprojects {
 	repositories {
 		maven { url "https://raw.githubusercontent.com/bj-jrxj/AvatarCloud_Android/main" }
 	}
 }

```
```
app build.gradle添加如下

dependencies {
        implementation('com.jrxj.avatar:avatarcloud:1.0.0')
}

```
### 使用
---
###### 头像云平台申请**appID** 和 **secretID**

###### 在项目Application中初始化

```
AvatarSelectClient.registerAvatarSelect(this, "appID", "secretID");

```

###### 调用

```
 AvatarSelector.create(this)
                        .setShapeType(AvatarConfig.AVATAR_SHAPE_CIRCLE_CODE)
                        .setShapeRadius(defaultBean.radius)
                        .setBearingView(imageView)
                        .forResult(data -> {
                            selectData = data;
                        });


```
###### Api

```
 AvatarSelector.class
 setShapeType(int shapeMode); //设置图片加载模式，圆形，方形。
 setShapeRadius(int radius);  //设置圆角度数，方形模式下有效。
 setBearingView(ImageView bearingView);//设置加载图片的ImageView。
 forResult(CommonCallBack<Bitmap> listener);//获取选择的图片bitmap回调

 AvatarConfig.class
 public final static int AVATAR_SHAPE_CIRCLE_CODE = 1;//shapeMode圆形模式
 public final static int AVATAR_SHAPE_ROUND_CODE = 2;//shapeMode方形模式


```

### Permission
* AndroidManifest.xml中添加

```
<uses-permission android:name="android.permission.INTERNET" />
```

* 更多高级功能配置请参考demo工程相关文档

