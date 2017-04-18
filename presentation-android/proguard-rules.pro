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
## Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
## Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
## Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
## Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
## For Okio
-dontwarn okio.**

# Jackson Databind 2.8.8
-dontwarn com.fasterxml.jackson.databind.ext.*

# Retrolambda 3.6.0 - https://github.com/evant/gradle-retrolambda#proguard
-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*