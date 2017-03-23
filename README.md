# 功能

- 打开App后自动打开Wifi
- 监听Wifi状态，当检测到Wifi被关闭，自动重新打开Wifi
- 适用于Monkey测试，在测试前打开本App，保持后台运行即可

# 启动命令

``` sh
adb shell am start -n com.ntflc.wifimanager/.MainActivity
```

# demo下载

见demo文件夹内app-debug.apk

# 说明

本App设计初衷是为了解决持续集成Monkey测试中，Wifi经常被自动关闭的问题。推荐同时使用[simiasque](https://github.com/Orange-OpenSource/simiasque)，具体用法详见该项目的README
