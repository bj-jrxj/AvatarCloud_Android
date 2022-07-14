# AvatarCloud_Android
头像云 Android SDK

# AvatarCloudSDK



* **具体内测申请流程请访问官网**


|SDK|下载地址|集成指引|
|-|-------:|:------:|
|iOS|https://github.com/bj-jrxj/AvatarCloud_iOS|[pod集成](#SDK集成)<br>[手动集成](#手动集成)|
|Adnroid|https://github.com/bj-jrxj/AvatarCloud_Android|gradle集成|




### 头像云

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
        implementation('com.jrxj.avatar:avatarcloud:1.0.0@aar') {transitive = true }
}

```


###### 头像云平台申请**appID** 和 **secretID**

###### 在项目Application中初始化

```
AvatarSelectClient.registerAvatarSelect(this, "appID", "secretID");

```

###### 使用

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
