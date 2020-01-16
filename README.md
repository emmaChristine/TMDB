Movies App
======================
<p float="left">
  <img src="/screenshots/popular.jpeg" width="150" />
  <img src="/screenshots/movie.jpeg" width="150" /> 
  <img src="/screenshots/favourites.jpeg " width="150" />
</p>


# Features

The application presents a list of popular movies from The Movie Database, together with their basic details.

[IN PROGRESS] The user is able to mark any movie as favourite and see the list of favourites at any time.
[IN PROGRESS] PagedAdapter.

Assumptions: 
* The API does not return page_size, but we can compute it  by `total_result/total_pages= 20`.
(total_results": 10000,
  "total_pages": 500)

* this app is single user only.

# Development Environment

The app is written entirely in Kotlin and uses the Gradle build system.
Latest Android Studio 3.5.3, Gradle 5.4.1, Gradle plugin 3.5.3 were used for development.


# Architecture

MVVM architecture, LiveData and Data Binding used to bind UI components in layouts to the app's data sources.
Repository later handles data operations: online fetching the popular movies via a Retrofit client, and offline fetching from a local cache using Room.
ViewPager and Navigation components are used for displaying and transitioning through the views.

#  Running the App

* Clone the repository and import `movies` project.

* Use your ApiKey for The Movie Database API:
Copy-paste  API Key in `gradle.properties`:
 `API_KEY` = 

* To build the app, use the `gradlew build` command or use "Import Project" in Android Studio. 

* Now you can deploy the application on your device.

#  CI Process
*  Execute built + lint for dev phase

*  Execute unit tests for staging phase

* Execute all tests for pre-release phase. Run monkey runner with run_monkey.sh


# Ideas of improvements !

## In terms of features:

* most certainly the popular movies list is not immutable, so we could refresh the list in the background, at a certain interval that makes sense for the product.

* add filtering capabilities, such as release date or language.

* provide share actions to other apps.

* add the date when user saved the movie so that we can organize the saved movies later on.

* depending on usage, we could decide to remove the least recently used cached items.

## In terms of implementation:

* To avoid Glide having to resize the image to fit the size of the ImageView, we should have an ImageService in charge of image manipulation and storage of different sizes (large, medium, thumbnail etc). And based on where we want to display the image, use the respective image representation.

* When user opens the MovieDetails and there is no network connection, we should fetch the information from local repository if available.
If the movie details are immutable then server-side, then every second time we open MovieDetails we could read from DB.

* Display placeholder image in case the movie image is unavailable for download.

* Show placeholder list items in the PagedList.

* Create more drawables for different res, and use copyright images in a production app.

* Modularization for network layer, so we can easily decouple it from the rest.

* To gain insight on app usage and user engamenent integrate Analytics using Firebase.

* Configure Sonarqube to monitor code vulnerabilities.

## In terms of QA:

* Add tests that parse local json to avoid hitting PROD.

