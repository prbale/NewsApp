

# NewsApp

**News Application** is basically a sample news app which is built to demonstrate the Android Architectural Components.

**You can Install and test latest OnlyNews app from below 👇**

[![News Application](https://github.com/prbale/prbale/blob/main/apk_download.jpg)](https://github.com/prbale/newsapp/tree/develop/artifacts/newsapp.apk)


## Screenshots 
<p align="center">
<img src="artifacts/screenshot_1.png" width="235" height="500"/>
<img src="artifacts/screenshot_2.png" width="235" height="500"/>
</p>

## About

It loads the news articles using [news api](https://newsapi.org/). Using retrofit we fetch all the json data provided by the api and display on the user screen.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - Programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [ViewDataBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.

- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.


## Architecture
This app uses [MVVM (Model View View-Model)](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

## Future Scope
1) Save News Functionality
2) Schedule auto pull news 
3) Add different news categories provided by newsapi.org 



## Contributing and reporting issues

You can to contribute in this repository. Just make pull request.

I appreciate any question or comment.
