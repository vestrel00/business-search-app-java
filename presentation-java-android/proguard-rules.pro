# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/estrellv/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Retrofit 2.2.0 - http://square.github.io/retrofit/
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**

# Jackson Databind 2.8.8
-dontwarn com.fasterxml.jackson.databind.ext.*

# Retrolambda 3.6.0 - https://github.com/evant/gradle-retrolambda#proguard
-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*

# Dagger Android 2.11.x - https://google.github.io/dagger/android.html
-dontwarn dagger.android.*

# Fresco 1.3.0 - https://raw.githubusercontent.com/facebook/fresco/master/proguard-fresco.pro
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}
-keepclassmembers class * {
    native <methods>;
}
-dontwarn com.facebook.infer.**