Movies App
======================
<p float="left">
  <img src="/screenshots/popular.jpeg" width="150" />
  <img src="/screenshots/movie.jpeg" width="150" /> 
  <img src="/screenshots/no_movies.jpeg" width="150" /> 
</p>


## Features

The application presents a list of popular movies from The Movie Database, together with their basic details.
(First page is fetched, ideally we should implement PagedAdapter to load movies based on user interaction. This is work under progress.)

Assumptions: 

* The API does not return page_size, but we can compute it  by `total_result/total_pages= 20`.
(total_results": 10000,
  "total_pages": 500)

* this app is single user only.

## Development Environment

The app is written entirely in Kotlin and uses the Gradle build system.
Latest Android Studio 3.5.3, Gradle 5.4.1, Gradle plugin 3.5.3 were used for development.


## Architecture

MVVM architecture, LiveData and Data Binding used to bind UI components in layouts to the app's data sources.
Repository later handles data operations: online fetching the popular movies via a Retrofit client, and offline fetching from a local cache using Room.

##  Running the App

* Clone the repository and import `movies` project.

* Use your ApiKey for The Movie Database API:
Copy-paste  API Key in `gradle.properties`:
 `API_KEY` = 
 The project already contains my own personal API_KEY.
 In an app no API_KEY should not be shared, but I did embed it here for the ease of running the demo.

* To build the app, use the `gradlew clean build` command.

* Now you can deploy the application on your device.

 Run monkey runner with run_monkey.sh

##  QA Plan

* Break down the test scenarios by us use cases and functionality.
 For example: As a user I want to see a list of popular movies.

* Run static code analysis in order to: catch code smells & vulnerabilities, but also to promote certain coding standards within the team.

* Run load tests to check how application performs under a heavy load.

* Perform regular security tests :
 - perform input validation to avoid SQL injection.
 - insecure files I/O access avoiding MODE_WORLD_READABLE/MODE_WORLD_WRITABLE, SharedPreferences that are not encrypted.
 - avoid JavaScript injection in WebViews.
 - use signature protection level on permissions for IPC communication between apps.
 - use authorization tokens rather than storing credentials on the device.

* Setup mock local service for integration tests, to avoid hitting Production during development phase.

* Performance tests & benchmarking: establish tresholds for app launch, operations (independent of network quality) to ensure a good user experience.

* If the app depended on more user input, Data-Driven tests would come in handy, as input data sets are external to the test codebase.

* Setup device farm to target more devices.


## Ideas of improvements !

### In terms of implementation:

* [IN PROGRESS] PagedAdapter for Movies screen. At the moment we only fetch first page, but we should use a PagedAdapter with RecyclerView to load pages based on user interaction.

* [IN PROGRESS] The user can mark any movie as favourite and find them in My Movies screen.

* To avoid Glide having to resize the image to fit the size of the ImageView, we should have an ImageService in charge of image manipulation and storage of different sizes (large, medium, thumbnail etc). And based on where we want to display the image, use the respective image representation.

* Display placeholder image in case the movie image is unavailable for download.

* Create more drawables for different res, and use copyright images in a production app.

* Modularization for network layer, so we can easily decouple it from the rest.

* To gain insight on app usage and user engamenent integrate Analytics using Firebase.

* Configure Sonarqube to monitor code vulnerabilities.

### In terms of features:

* most certainly the popular movies list is not immutable, so we could refresh the list in the background, at a certain interval that makes sense for the product.

* add filtering capabilities, such as release date or language.

* store the date when user saved the movie so that we can organize the saved movies later on.

* depending on usage, we could decide to remove the least recently used cached items.



### In terms of QA:

* More code coverage, more integration tests and tests that mock the API.

