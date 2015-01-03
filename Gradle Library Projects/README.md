# Gradle Library Projects

`Gradle` 项目可以依赖于其它组件。这些组件可以是外部二进制包，或者是其它的 `Gradle` 项目。

在本例中， `app/build.gradle` 中有以下内容：

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
    compile project(':library')
    compile 'com.nineoldandroids:library:2.4.0'
}
```

## Local packages（本地包）

```
compile fileTree(dir: 'libs', include: ['*.jar'])
```

引用 `libs` 目录下的所有的 `.jar` 文件。如果你指向引用 `libs` 目录下中一个指定的 `jar` ，你可以这么设置：

```
compile files('libs/xx.jar')
```

## Remote artifacts（远程文件）

```
compile 'com.android.support:appcompat-v7:21.0.3'
```
  
引用 `21.0.3` 版本的 `appcompat-v7` 。  
在 Android Studio中，使用 `android support` ，需要在 SDK 中下载 `Android Support Repository` ，且项目中使用的版本不能大于 SDK 中的版本。当你的 SDK 中已经下载指定版本的 `Android Support Repository` ，即使没有联网，你也是可以在 Android Studio 中依赖对应的文件。如果你的 SDK 没有下载指定版本的 `Android Support Repository` ，即使你现在连着网，也会出错。

```
compile 'com.nineoldandroids:library:2.4.0'
```  

引用 `2.4.0` 版本的 `NineOldAndroids` 。需要联网下载。

使用 `maven` 和 `ivy` 时需要在 `build.gradle` 中将仓库添加到列表中。

```
buildscript {
    repositories {
        jcenter()
    }
}

allprojects {
    repositories {
        jcenter()
    }
}
```

* `mavenCentral()` ：表示依赖从 `Central Maven 2` 仓库中获取。
* `jcenter()` ：表示依赖从 `Bintary’s JCenter Maven` 仓库中获取。
* `mavenLocal()` ：表示依赖从本地的Maven仓库中获取。

## Library Project（库项目）

`compile project(':library')`  
引用名称为 `library` 的 `module` 。需要注意的是，被引用的 `module` 需要在 `{@projectName}/settings.gradle` 文件中进行注册。

我们可以观察到，`library/build.gradle` 中使用的 `apply plugin` 为 `'com.android.library'`。用以标记这是一个 `Android Library Project` 。

当然，你也可以依赖一个 `Java Project` ，`apply plugin` 为 `'java'`。

## 补充内容

关于依赖更多的补充内容如下：

```
dependencies {
    // 引入 jar 包。
    // 引用某一个特定的jar。
    compile files('libs/xx.jar')
    // 引用libs文件夹下除xx.jar以外所有的jar。
    compile fileTree(dir: 'libs', include: ['*.jar'], exclude: ['xx.jar'])
 
    // so包在0.8版本的Android Studio中的目录更改为@{ModuleName}/src/main/jniLibs。且可以不用在此处配置so了。
    
    // 从 maven 库中引入。
    //compile 'com.github.chrisbanes.actionbarpulltorefresh:extra-abc:0.9.2'
 
    // 引用 lib 工程。
    compile project(':moduleName')
 
    // 引用users-library。users-library作用是，在编译时使用，但是jar不会打包到apk中，由Android或Android上安装的服务提供需要的内容。
    // 使用场景：
    // 1. 使用Android的framework-classes.jar中的一些隐藏的API。
    // 2. Google的服务框架或者其他服务框架。需要在AndroidMainFest.xml中配合uses-library使用。
    provided files('libs/xx.jar')
    provided 'aaa:bbb:x.x.x'
 
    // 在测试环境下引用依赖。
    // 引用jar文件。
    androidTestCompile files('libs/xx.jar')
    // 引用Maven。
    androidTestCompile 'junit:junit:4.11'
 
    // 在baidu productFlavors分支下引用依赖。
    // 引用jar文件。
    baiduCompile files('libs/xx.jar')
    // 引用Maven。
    baiduCompile 'aaa:bbb:x.x.x'
 
    // 在release buildTypes分支下引用依赖。
    // 引用jar文件。
    releaseCompile files('libs/xx.jar')
    // 引用Maven。
    releaseCompile 'aaa:bbb:x.x.x'
}
```
