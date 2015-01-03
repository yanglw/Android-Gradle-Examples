# Gradle Build Configs

在 `app/build.gradle` 中，有以下内容：

```
android {
    signingConfigs {
        release {
            storeFile file('keystore')
            storePassword 'helloworld'
            keyAlias 'Android Release Key'
            keyPassword 'helloworld'
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'

            signingConfig signingConfigs.release
        }
        debug {
            applicationIdSuffix ".debug"
        }
    }
}
```

`signingConfigs` 元素用于设置签名文件信息。在本例中，我们使用了 `app/keystore` 文件为 `release` 分支进行签名。默认使用 SDK 中的 `debug.keystore` 为 `debug` 分支进行签名。

我们可以在 `buildTypes` 中对 APK 的一些信息可以设置，例如本例中将 `debug` 分支下 APK 包名在默认的包名后追加 `.debug` ，从而最终包名为 `cc.bb.aa.gradle_build_configs.debug`：

```
debug {
    applicationIdSuffix ".debug"
}
```

除了 `applicationIdSuffix` 之外，在 `buildTypes` 中还可以使用到下面元素：

Property Name|debug|release|说明
--|--|--|--
debuggable|true|false|标记是否为 debug 类型，影响 `BuildConfig` 类中的 `DEBUG`常量的值
jniDebugBuild|false|false|
applicationIdSuffix|null|null| APK 包名追加内容
versionNameSuffix|null|null|版本名称追加内容
signingConfig|android.signingConfigs.debug|null|签名信息
zipAlign|false|true|压缩对齐
minifyEnabled|false|false| APK 最小化处理，主要作用为是否开启混淆代码
shrinkResources|false|false|剔除没有使用到的资源文件，需要 `minifyEnabled` 为 `true`
proguardFile|N/A (set only)|N/A (set only)|混淆配置文件，一次选择一个，可多次选择，需要 `minifyEnabled` 为 `true`
proguardFiles|N/A (set only)|N/A (set only)|混淆配置文件，一次可选多个，可多次选择，需要 `minifyEnabled` 为 `true`