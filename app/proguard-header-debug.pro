-dontoptimize
-dontobfuscate
-dontwarn javax.annotation.**

-dontwarn kotlin.reflect.jvm.internal.**


# Retrofit rules
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions

# OkHttp rules
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

-dontwarn sun.misc.Unsafe
-keep class rx.internal.util.unsafe.** { *; }

# Gson rules
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keepclassmembers class com.wiscosoft.ridefree.model.** { !static !private <fields>; }

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keep class .R
-keep class **.R$* { <fields>; }

# Rxjava rules
-dontwarn rx.internal.util.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

-keep class dmax.dialog.** { *; }
-dontwarn com.google.errorprone.annotations.*

-keepattributes Signature

-dontwarn rx.internal.util.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

-keep class * extends com.airbnb.epoxy.EpoxyController { *; }
-keep class * extends com.airbnb.epoxy.ControllerHelper { *; }
-keepclasseswithmembernames class * { @com.airbnb.epoxy.AutoModel <methods>; }
-keepclasseswithmembernames class * { @com.airbnb.epoxy.AutoModel <fields>; }