# Gradle Hello World

一个 Android Studio 项目中，会存在多个 `.gradle` 文件。其中， `project` 目录下存在一个 `build.gradle` 文件和一个 `settings.gradle` 文件；每一个 `module` 会存在一个 `build.gradle` 文件。

本文只是简略的讲解一下默认生成的 `.gradle` 文件的内容，更多 `Gradle Plugin` 的知识，请看[这里](http://tools.android.com/tech-docs/new-build-system/user-guide)。

##  `{@projectName}/build.gradle`

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.0.0'
    }
}
 
allprojects {
    repositories {
        jcenter()
    }
}
```

默认的 `project` 目录下的 `build.gradle` 文件内容如上。

* `buildscript` ：用于设置驱动构建过程的代码。
* `jcenter()`：声明使用 `maven` 仓库。在老版本中，此处为 `mavenCentral()`。
    * `mavenCentral()` ：表示依赖从 `Central Maven 2` 仓库中获取。
    * `jcenter()` ：表示依赖从 `Bintary’s JCenter Maven` 仓库中获取。
    * `mavenLocal()` ：表示依赖从本地的Maven仓库中获取。
* `dependencies` ：声明了使用 Android Studio `gradle` 插件版本。一般升级AS或者导入从Eclipse中生成的项目时需要修改下面gradle版本。具体的版本对应关系，请[点击](http://tools.android.com/tech-docs/new-build-system/version-compatibility)。
* `allprojects`：设置每一个 `module` 的构建过程。在此例中，设置了每一个 `module` 使用 `maven` 仓库依赖。

在景德镇，默认的maven源可能无法访问，可以通过以下的方式设置其他的maven源。当然，你也可以设置依赖本地库。

```
maven {
    url "http://xx.xxx.xxx/xxx"
}
```

开源中国的源地址为：

```
http://maven.oschina.net/content/groups/public/
```

开源中国的thirdparty源地址为：

```
http://maven.oschina.net/content/repositories/thirdparty/
```

你可以为 `repositories` 设置多个库。 `Gradle` 会根据依赖定义的顺序在各个库里寻找它们。在第一个库里找到就不会再在第二个库里进行寻找。

##  `{@projectName}/settings.gradle`

```
include ':app'
```

默认的 `project` 目录下的 `settings.gradle` 文件内容如上。可有可能默认情况下， `project` 目录下的 `settings.gradle` 文件不存在，你可以自己创建。

* `include ':app'`：表示当前 `project` 下有一个名称为 `app` 的 `module` 。

如果你的一个 `module` 并不是 `project` 根目录下，你可以这么设置。

```
include ':app2'
project(':app2').projectDir = new File('path/to/app2')
```

## `{@projectName}/gradle`、`{@projectName}/gradlew`、`{@projectName}/gradlew.bat`

这3个文件及文件夹我们不需要进行处理。

有一处比较特别的就是：  
`{@projectName}/gradle/wrapper/gradle-wrapper.properties` 这个配置文件中配置了 `Gradle` 构建当前项目时使用的 `Gradle` 版本。我们需要注意的就是 Android Studio 、Gradle 、 Gradle Plugin 这3个的版本需要对应起来，具体的对应关系请[点此](http://tools.android.com/tech-docs/new-build-system/version-compatibility)。

我们可以使用 `{@projectName}/gradlew.bat` 在命令行中进行 `Gradle` 任务。但是，建议使用 Android Studio 的 `Gradle` 功能面板进行 `Gradle` 任务。

##  `{@moduleName}/build.gradle`

```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 21
    buildToolsVersion "21.1.2"

    defaultConfig {
        applicationId "cc.bb.aa.myapplication"
        minSdkVersion 10
        targetSdkVersion 21
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
}
```

默认的 `module` 目录下的 `build.gradle` 文件内容如上。

* `apply plugin: 'com.android.application'`：  
    表示使用 `com.android.application` 插件。也就是表示，这是一个 `android application module` 。 `com.android.library` 表示，这是一个 `android library module` 。
* `android`：  
    配置所有 android 构建过程需要的参数。
* `compileSdkVersion`：  
    用于编译的 `SDK` 版本。 
* `buildToolsVersion`：  
    用于 `Gradle` 编译项目的工具版本。在 SDK 中可以查看本机已安装的版本。
* `defaultConfig`：  
    Android 项目默认设置。
    * `applicationId`：应用程序包名。
    * `minSdkVersion`：最低支持 Android 版本。
    * `targetSdkVersion`：目标版本。实际上应为测试环境下测试机的 Android 版本。
    * `versionCode`：版本号。
    * `versionName`：版本名称。
* `buildTypes`:
    编译类型。默认有两个： `release` 和 `debug` 。我们可以在此处添加自己的 `buildTypes` ，可在 `Build Variants` 面板看到。
    * `minifyEnabled`：  
        是否使用混淆。在老版本中为 `runProguard` ，新版本之所换名称，是因为新版本支持去掉没使用到的资源文件，而 `runProguard` 这个名称已不合适了。
    * `proguardFiles`：  
        使用的混淆文件，可以使用多个混淆文件。此例中，使用了 `SDK` 中的 `proguard-android.txt` 文件以及当前 `module` 目录下的 `proguard-rules.pro` 文件。
* `dependencies`：  
    用于配制引用的依赖。
    * `compile fileTree(dir: 'libs', include: ['*.jar'])`：  
        引用当前 `module` 目录下的 `libs` 文件夹中的所有 `.jar` 文件。
    * `compile 'com.android.support:appcompat-v7:21.0.3'`：  
        引用 `21.0.3`版本的 `appcompat-v7` （也就是常用的 `v7` library 项目）。
        > 在 `Eclipse` 中，使用 `android support` ，需要在 SDK 中下载 `Android Support Library` 。在 Android Studio中，使用 `android support` ，需要在 SDK 中下载 `Android Support Repository` ，且项目中使用的版本不能大于 SDK 中的版本。