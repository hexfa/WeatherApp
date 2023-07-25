# Weather App

![Weather App](weather_app_banner.png)

## Details 📜

This Weather App is a user-friendly Android application built using Kotlin that allows users to access real-time weather information for their current location. The app fetches weather data from an external API based on the user's latitude and longitude and presents essential weather details such as temperature, weather conditions, humidity, wind speed, and more. It follows the MVVM (Model-View-ViewModel) architecture to ensure a well-organized and maintainable codebase. The app leverages several powerful libraries like Retrofit, Glide, Kotlin Coroutines, Room, and Koin Dependency Injection to enhance its functionality and performance.

## Table of Contents

- [Features](#features-)
- [App Architecture](#app-architecture-)
- [Libraries Used](#libraries-used-)
- [Preview](#preview-)
- [Build](#build-)

## Features 🚀

- **Get Current Weather:** The app utilizes the device's location services to determine the user's current location and fetches real-time weather data from a weather API. This ensures that users receive accurate weather updates for their specific location.

- **Display Weather Details:** The Weather App presents detailed weather information, including the current temperature, weather conditions (e.g., sunny, cloudy, rainy), humidity level, wind speed, and other relevant data. This allows users to stay informed about the weather conditions at any given moment.

- **Responsive UI:** The app's user interface is designed to be responsive and adaptive, ensuring a seamless user experience across different screen sizes and orientations. Users can comfortably use the app on various Android devices.

## App Architecture 🏗️

The Weather App follows the MVVM (Model-View-ViewModel) architecture, which facilitates a clear separation of concerns and promotes code organization. The key components of this architecture are:

- **Model:** Represents the data and business logic. It handles data retrieval from the API and local storage using Room, ensuring data consistency and providing data to the ViewModel.

- **View:** Represents the user interface. It observes the ViewModel for changes in data and updates the UI accordingly. The View also handles user interactions and triggers actions in the ViewModel.

- **ViewModel:** Acts as an intermediary between the Model and the View. It holds the app's state, processes user interactions, and communicates with the Model to fetch data and update the View.

## Libraries Used 🛠️

The Weather App is built using the following tools and libraries:

- **Kotlin:** Kotlin is the primary programming language used for Android development. It offers modern language features, concise syntax, and strong type checking, making the code more maintainable and efficient.

- **Retrofit:** Retrofit is a widely-used library for making network requests in Android apps. It simplifies the process of fetching data from the weather API and handling API responses.

- **Glide:** Glide is an image loading library used to efficiently load and display images in the app. It optimizes image loading to provide a smooth and fast user experience.

- **Kotlin Coroutines:** Kotlin Coroutines are used in conjunction with Retrofit to handle asynchronous operations in a more concise and readable manner. It allows for responsive and non-blocking code execution.

- **Room:** Room is an Android database library that provides an abstraction layer over SQLite. It allows the app to store user data locally for offline access and persistence.

- **Koin Dependency Injection:** Koin is a lightweight dependency injection framework used to manage dependencies in the app. It simplifies the process of providing and injecting dependencies, leading to a more modular and testable codebase.

- **Fused Location Provider Client:** The Fused Location Provider Client is used to access the device's location services and retrieve the user's latitude and longitude. This enables the app to fetch weather data specific to the user's current location.

- **Android Support Libraries:** The app uses various Android Support Libraries to enhance its functionality, compatibility, and user interface design.
## Preview 📱

### Screenshots 🖼️

<div style="display: flex; justify-content: center;">
  <img src="https://github.com/hexfa/WeatherApp/blob/master/photo.jpg" alt="Rick and Morty App hexfa" style="max-width: 100%; height: auto;">
</div>
## Build 🛠️

To build the Weather App, follow these steps:

1. Clone this repository:

```bash
git clone https://github.com/hexfa/WeatherApp
```

2. Open the project in Android Studio.

3. Build and run the application on an Android device or emulator.

