# Asteroid Application
This application is a listing of Daily Asteroids using NASA's Asteroids-api\
the application categorize the asteroids the ones that are dangerous(_near Earth_) from the ones that are not(_far away_)
### Describtion
* The application consists of main listing screen of clickable asteroids
* A detail screen shows thhe details of the asteroid once it has been clicked
* Downloading and pursing data of the NASA api
* once downloaded saves data in the Room Database which is linked to the lisiting screen to be displayed
* Caching data using worker. It downloads and store new data in database as well as removing old asteroid data of the passing day when the phone is charging and connected to wifi.
## Built with
* [Kotlin](https://kotlinlang.org/): Default language used to build this project
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started): Android Jetpack's Navigation component, used for Fragment-based navigation
* [Retrofit](https://github.com/square/retrofit) - a type-safe HTTP client for Android and Java
* [Moshi](https://github.com/square/moshi) - a modern JSON library for Android and Java, that makes it easy to parse JSON into Java or Kotlin objects
* [Picasso](https://square.github.io/picasso) - a powerful image downloading and caching library for Android
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture): a collection of libraries that help design robust, testable, and maintainable apps: Room (a SQLite object mapping library), LiveData (builds data objects that notify views when the underlying database changes), ViewModel (stores UI-related data that isn't destroyed on app rotations)
* [MVVM](https://developer.android.com/jetpack/guide): The architecture pattern used in the app (Model-View-ViewModel), that incorporates the Android Architecture Components
* [Data-Binding](https://developer.android.com/topic/libraries/data-binding): A Jetpack support library that allows to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
* [Navigation Component](https://developer.android.com/guide/navigation): Android Jetpack's Navigation component, used for Fragment-based navigation
## ScreenShots
![photo1](/screenshots/screen_1.png)
![photo2](/screenshots/screen_2.png)
![photo3](/screenshots/screen_3.png)
![photo4](/screenshots/screen_4.png)
