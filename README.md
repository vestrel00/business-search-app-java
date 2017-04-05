# Yelp Search

The purpose of this project is to demonstrate how I build Android applications from the ground up.
It is less about the application content and more about the code (and processes). 

The goal is to showcases my knowledge of Java object oriented programming in Android, including my knowledge of:

- Reactive programming with [RxJava 2](https://github.com/ReactiveX/RxJava/tree/2.x) and 
  [RxAndroid 2](https://github.com/ReactiveX/RxAndroid/tree/2.x)
- Dependency injection with [Dagger 2](https://github.com/google/dagger/tree/dagger-2.10-rc4)
- View binding with [Butterknife](https://github.com/JakeWharton/butterknife/tree/butterknife-parent-7.0.1)
- Networking with [Retrofit 2](https://github.com/square/retrofit/tree/parent-2.2.0)
- Mapping with [Google Maps APIs](https://developers.google.com/maps/documentation/android-api/)
- Testing with [JUnit 4](https://github.com/junit-team/junit4/blob/master/doc/ReleaseNotes4.12.md), 
  [Mockito 2](https://github.com/mockito/mockito/tree/release/2.x), 
  [Robolectric 3](https://github.com/robolectric/robolectric/tree/robolectric-3.3.2), and
  [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/index.html)
- [Clean, modular architecture](https://github.com/android10/Android-CleanArchitecture)
- [Model-View-Presenter](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) (MVP) pattern
- [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (MVVM) pattern
- Java and Android best practices, including programming patterns/styles
- Creating readable, focused, and well-documented pull requests


## Application and Features

Using the [Yelp Fusion (v3) REST API](https://www.yelp.com/developers/documentation/v3), 
I have created a simple Yelp search application with the following features:

- Display places around the user's current location in a list and map views
- Show more details on a specific place when clicking on a list or map item

I kept the application short and simple with no advanced UI for brevity. I am not using the 
[Yelp Android API](https://github.com/Yelp/yelp-android) so that I am able to demonstrate my knowledge of 
[Retrofit 2](https://github.com/square/retrofit/tree/parent-2.2.0)


## Architecture

TODO


## Tests

TODO

#### Jacoco Test Report

TODO


## Static Analysis

The following static analysis checks are used to enforce high code quality and compliance to standard Java (and Android) 
code style and patterns:

- [Checkstyle](http://checkstyle.sourceforge.net/)
- [Findbugs](http://findbugs.sourceforge.net/)
- [PMD](https://pmd.github.io/)
- [Android lint](http://tools.android.com/tips/lint)

You can read more about these tools in this 
[blog post](http://vincentbrison.com/2014/07/19/how-to-improve-quality-and-syntax-of-your-android-code/).

To run all static analysis checks for all build variants,

```
./gradlew checkQuality
```

The above will run checkstyle, findbugs, PMD, and lint for all build variants. 
To run build variant specific static analysis checks,

```
./gradlew check<build_variant>Quality
```

For example, to run static analysis checks for the MVP Debug build variant,

```
./gradlew checkMvpDebugQuality
```

You may run individual static analysis checks separately. 
To see the list of all gradle tasks,

```
./gradlew tasks
```


## Javadoc

Javadoc is available. To generate the Javadocs, 

```
./gradlew generate<build_variant>JavaDoc
```

For example, to generate Javadocs for MVP Debug build variant,

```
./gradlew generateMvpDebugJavaDoc
```

## Code Styling

Read *STYLE.md* 