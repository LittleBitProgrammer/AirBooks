# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.squareup.okhttp3.** {
*;
}

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

 -keep class cn.pedant.SweetAlert.Rotate3dAnimation {
    public <init>(...);
}

-keep public class * extends android.support.v7.widget.RecyclerView$LayoutManager {
    public <init>(...);
}

-keep class pkg.of.the.pojos.** { *; }

-keep class **.CountryPojo { *; }

# don't process support library
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }


-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepattributes *Annotation*
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keepattributes Exceptions,InnerClasses,Signature

#-keep class au.com.flightcentre.fragment.** { *; }

# Preserve the special static methods that are required in all enumeration classes.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep class * implements android.os.Serializable {
  public static final android.os.Serializable$Creator *;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}

-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
-keep class com.google.** { *; }
-keep class com.android.** { *; }
-keep class android.support.v7.** { *; }
-keep class java.lang.** { *; }

-dontwarn android.support.v7.**

-keepattributes Signature
-keepattributes *Annotation*

# Retrofit
-keep class com.google.gson.** { *; }
-keep public class com.google.gson.** {public private protected *;}
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.xml.stream.** { *; }
-keep class retrofit.** { *; }
-keep class com.google.appengine.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.squareup.okhttp.*
-dontwarn rx.**
-dontwarn javax.xml.stream.**
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.**



-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-keep class it.corelab.studios.airbooks.model.data.LOGIN.SIGNIN.PostSignInResponse
-keep class it.corelab.studios.airbooks.model.data.LOGIN.SIGNIN.PostSignIn
-keep class it.corelab.studios.airbooks.model.data.LOGIN.SIGNIN.AutomaticSignInResponse
-keep class it.corelab.studios.airbooks.model.data.LOGIN.RECOVERPASSWORD.PostRecoverResponse
-keep class it.corelab.studios.airbooks.model.data.LOGIN.Result
-keep class it.corelab.studios.airbooks.model.data.LOGIN.SIGNUP.PostSignUp
-keep class it.corelab.studios.airbooks.model.data.LOGIN.SIGNUP.PostSignUpResponse
-keep class it.corelab.studios.airbooks.model.data.REVIEW.Entity
-keep class it.corelab.studios.airbooks.model.data.REVIEW.Error
-keep class it.corelab.studios.airbooks.model.data.HOME.Best
-keep class it.corelab.studios.airbooks.model.data.HOME.Genre
-keep class it.corelab.studios.airbooks.model.data.HOME.GetHome
-keep class it.corelab.studios.airbooks.model.data.HOME.ItemBest
-keep class it.corelab.studios.airbooks.model.data.HOME.ItemReading
-keep class it.corelab.studios.airbooks.model.data.HOME.Reading
-keep class it.corelab.studios.airbooks.model.data.HOME.Result
-keep class it.corelab.studios.airbooks.model.data.HOME.Showcase
-keep class it.corelab.studios.airbooks.model.data.EXPLORE.GetExplore
-keep class it.corelab.studios.airbooks.model.data.EXPLORE.Item
-keep class it.corelab.studios.airbooks.model.data.EXPLORE.Recents
-keep class it.corelab.studios.airbooks.model.data.EXPLORE.Result
-keep class it.corelab.studios.airbooks.model.data.EXPLORE.world
-keep class it.corelab.studios.airbooks.model.data.REVIEW.GetReviews
-keep class it.corelab.studios.airbooks.model.data.REVIEW.Item
-keep class it.corelab.studios.airbooks.model.data.REVIEW.Result
