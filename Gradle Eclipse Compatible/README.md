# Gradle Eclipse Compatible

当你的项目从 `Eclipse` 中使用 `Generate Gradle build files` 导出的时候。为了兼容 `Eclipse` 的文件结构， `Gradle` 对资源文件目录、代码文件目录等目录进行了设置。

默认的，导出项目中没有 `{@projectName}/settings.gradle` 文件，而且 `{@projectName}/build.gradle` 和 `{@moduleName}/build.gradle` 文件进行了合并。合并后的文件内容如下：

```
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.0.0'
    }
}
apply plugin: 'android'

dependencies {
    compile fileTree(dir: 'libs', include: '*.jar')
}

android {
    compileSdkVersion 21
    buildToolsVersion "21.1.2"

    sourceSets {
        main {
            manifest.srcFile 'AndroidManifest.xml'
            java.srcDirs = ['src']
            resources.srcDirs = ['src']
            aidl.srcDirs = ['src']
            renderscript.srcDirs = ['src']
            res.srcDirs = ['res']
            assets.srcDirs = ['assets']
        }

        // Move the tests to tests/java, tests/res, etc...
        instrumentTest.setRoot('tests')

        // Move the build types to build-types/<type>
        // For instance, build-types/debug/java, build-types/debug/AndroidManifest.xml, ...
        // This moves them out of them default location under src/<type>/... which would
        // conflict with src/ being used by the main source set.
        // Adding new build types or product flavors should be accompanied
        // by a similar customization.
        debug.setRoot('build-types/debug')
        release.setRoot('build-types/release')
    }
}
```

和默认的 Android Studio `{@moduleName}/build.gradle` 文件相比， `sourceSets` 算是最大的区别了。 `sourceSets` 用于设置文件目录。

`main` 元素表示默认的主干，出了 `main` 之外，默认的会有 `release` 和 `debug` 分支。如果 `release` 和 `debug` 分支中有些文件所在的目录不在默认目录同时也不再 `main` 所设置的目录，你可以在对应的分支中进行设置。