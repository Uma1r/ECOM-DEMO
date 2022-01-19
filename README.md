# ECOM-DEMO
A simple app showcasing a products listing page and a product details page utilising APIs from FakeStoreApi.com. Moreover, project architecture follows MVVM with clean architecture.

## Showcase
* Video: https://github.com/Uma1r/ECOM-DEMO/blob/main/art/ecom-demo-vid.mp4

<div align="center">
  <img src="https://github.com/Uma1r/ECOM-DEMO/raw/main/art/001_home_product_listing_dark.jpeg" width="230px" />  <img src="https://github.com/Uma1r/ECOM-DEMO/raw/main/art/001_home_product_listing_light.jpeg" width="230px" />  <img src="https://github.com/Uma1r/ECOM-DEMO/raw/main/art/002_product_detail_dark.jpeg" width="230px" /> <br>
  <img src="https://github.com/Uma1r/ECOM-DEMO/raw/main/art/002_product_detail_light.jpeg" width="230px" />  <img src="https://github.com/Uma1r/ECOM-DEMO/raw/main/art/003_home_product_listing_error_dark.jpeg" width="230px" />  <img src="https://github.com/Uma1r/ECOM-DEMO/raw/main/art/003_home_product_listing_error_light.jpeg" width="230px" /> <br>
</div>

<br/>

## Features
* Loads the FakeStoreApi products
* Loads the FakeStoreApi product details
* Light/Dark mode
* Shimmer loading effect
* Graceful error handling
* Dependency Injection
* Kotlin
* Unit Tests

## Download Demo APK on Android
Download the [APK file from here](https://github.com/Uma1r/ECOM-DEMO/raw/main/demo/app-release.apk) on your Android Device to be able to run ECOM-DEMO

## Architecture
* Modern MVVM Android development practices followed
* Repository and Usecase patterns utilized for data
* Unit tests for Viewmodels, Repository, API and Usecases

## Technology Stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Navigation Components](https://developer.android.com/guide/navigation/navigation-getting-started) - For Navigation between screens with Fragments
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Hilt](https://dagger.dev/hilt) - Easier way to incorporate Dagger DI into Android apps.
- [MockK](https://mockk.io) - For Mocking and Unit Testing
- [Coil](https://coil-kt.github.io/coil/) - For Image loading
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components
- [Facebook-Shimmer-Loading](https://github.com/facebook/shimmer-android) - Shimmer is an Android library that provides an easy way to add a shimmer effect to any view in your Android app.
