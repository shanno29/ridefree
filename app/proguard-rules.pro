
-dontoptimize

-dontobfuscate

-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn kotlin.reflect.jvm.internal.**
-dontwarn retrofit2.Platform$Java8
-dontwarn javax.annotation.**
-dontwarn sun.misc.**
-dontwarn okio.**


-keep class android.arch.** { *; }

-keepattributes Signature

-keep public class com.google.android.gms.* { public *; }

-dontwarn com.google.android.gms.**

