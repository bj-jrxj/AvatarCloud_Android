# AvatarCloudSDK

[![CI Status](https://img.shields.io/travis/bj-jrxj/AvatarCloudSDK.svg?style=flat)](https://travis-ci.org/bj-jrxj/AvatarCloudSDK)
[![Version](https://img.shields.io/cocoapods/v/AvatarCloudSDK.svg?style=flat)](https://cocoapods.org/pods/AvatarCloudSDK)
[![License](https://img.shields.io/cocoapods/l/AvatarCloudSDK.svg?style=flat)](https://cocoapods.org/pods/AvatarCloudSDK)
[![Platform](https://img.shields.io/cocoapods/p/AvatarCloudSDK.svg?style=flat)](https://cocoapods.org/pods/AvatarCloudSDK)

---
## 介绍

### [云头像平台](https://fc.faceface2.com)

云头像平台提供海量免费原创IP头像,包含卡通、二次元、像素、写实等丰富多元的风格，实现自定义头像、智能生成头像、保存头像等独特功能。

通过与国内知名影视、动画、潮玩等行业头部公司合作，创作出以IP形象为核心的头像，深受用户喜爱。已帮助众多知名央国企平台升级原创IP头像服务。

本平台所使用的头像及美术素材，均已签订协议并获得授权，平台内的头像可直接作为用户头像使用。如需二次编辑或衍生品开发，请联系[**云头像平台**](https://fc.faceface2.com)。


### 需求痛点
基本所有的应用都需要头像.  对于头像,大家的做法都是用户上传一张图片的方式, 如下图:

![image](http://oss.faceface2.com/facecloud/pic/3ecaAa.jpeg)

这样的体验方式,用户需要时刻在相册中保留一样图片,并记住在哪里.

为什么没有一种方式,为用户提供很好看的头像, 直接让用户选择呢? 于是就有了该项目.

### 云头像截图
![image](http://oss.faceface2.com/facecloud/pic/6dd0f7fa-46f8-4782-9ac8-ce8d3fb49c82.png)
![image](http://oss.faceface2.com/facecloud/pic/34612097-e78e-49b1-9347-2a2018fc95fe.png)
![image](http://oss.faceface2.com/facecloud/pic/5ae8db45-e2b5-49aa-9199-2b9f91a6f4ec.png)

### 申请流程
 请访问官网: https://fc.faceface2.com



|功能|SDK|下载地址|集成指引|
|-|-|-------:|:------:|
|云头像|iOS|https://github.com/bj-jrxj/AvatarCloud_iOS|[集成](https://github.com/bj-jrxj/AvatarCloud_iOS)|
|云头像+一键登录|iOS|https://github.com/bj-jrxj/AvatarCloud-Login_iOS|[集成](https://github.com/bj-jrxj/AvatarCloud-Login_iOS)|
|云头像|Android|https://github.com/bj-jrxj/AvatarCloud_Android|gradle集成|
|云头像+一键登录|Android|https://github.com/bj-jrxj/AvatarCloud_Android/blob/main/README16.md|gradle集成|



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




 




 























