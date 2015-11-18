# Gradle FlavorDimensions

本文内容改写自 [http://avatarqing.github.io/Gradle-Plugin-User-Guide-Chinese-Verision/build_variants/multi-flavor_variants.html](http://avatarqing.github.io/Gradle-Plugin-User-Guide-Chinese-Verision/build_variants/multi-flavor_variants.html) 。

在一些情况下，一个应用可能需要基于多个标准来创建多个版本。

例如，有个 app 需要一个免费版本和一个付费的版本，并且需要在不同的 app 发布平台发布。这个 app 需要 2 个付费版和 2 个特定发布平台，因此就需要生成 4 个APK（不算 Build Types 生成的 Variant 版本）。

然而，这款 app 中，为 2 个发布平台构建的付费版本源代码都是相同，因此创建 4 个 flavor 来实现不是一个好办法。 如果使用两个 flavor 维度，两两组合，构建所有可能的 Variant 组合才是最好的。

这个功能的实现就是使用 Flavor Dimensions 。每一个 Dimensions 代表一个维度，并且 flavor 都被分配到一个指定的 Dimensions 中。

```
android {
    ...
    flavorDimensions 'price', 'store'

    productFlavors {
        google {
            flavorDimension 'store'
        }

        amazon {
            flavorDimension 'store'
        }

        free {
            flavorDimension 'price'
        }

        paid {
            flavorDimension 'price'
        }
    }
}
```

andorid.flavorDimensions 数组按照先后排序定义了可能使用的 Dimensions 。每一个 Product Flavor 都被分配到一个 Dimensions 中。

上面的例子中将 Product Flavor 分为两组（即两个维度），分别为 `price` 维度 [free, paid] 和 `store` 维度 [google, amazon] ，再加上默认的 Build Type 有 [debug, release] ，这将会组合生成以下的 Build Variant：

- free-google-debug
- free-google-release
- free-amazon-debug
- free-amazon-release
- paid-google-debug
- paid-google-release
- paid-amazon-debug
- paid-amazon-release

每一个 Variant 版本的配置由几个 Product Flavor 对象决定：

- android.defaultConfig
- 一个来自 `price` 组中的对象
- 一个来自 `store` 组中的对象

android.flavorDimensions 中定义的 Dimensions 排序非常重要（Variant 命名和优先级等）。

flavorDimensions 中的排序决定了哪一个 flavor 覆盖哪一个，这对于资源来说非常重要，因为一个 flavor 中的值会替换定义在低优先级的 flavor 中的值。

flavorDimensions 使用最高的优先级定义，因此在上面例子中的优先级为：

```
price > store > defaultConfig
```

flavorDimensions 项目同样拥有额外的 sourceSet ，类似于 Variant 的 sourceSet ，只是少了 Build Type：

- android.sourceSets.freeGoogle
    位于src/freeGoogle/
- android.sourceSets.paidAmazon
    位于src/paidAmazon/
等等...

这允许在 flavor-combination 的层次上进行定制。它们拥有过比基础的 flavor sourceSet 更高的优先级，但是优先级低于 Build Type 的 sourceSet 。

**重要说明**

在 Android Gradle Plugin 2.0 版本中，flavor 中的 `flavorDimension` 字段改为 `dimension` ，`flavorDimension` 无法继续使用，Android Gradle Plugin 1.3 已经支持 `dimension` 了，所以建议使用 `dimension` 。

在 Android Gradle Plugin 2.0 需要将样例改为：

```
android {
    ...
    flavorDimensions 'price', 'store'

    productFlavors {
        google {
            dimension 'store'
        }

        amazon {
            dimension 'store'
        }

        free {
            dimension 'price'
        }

        paid {
            dimension 'price'
        }
    }
}
```
