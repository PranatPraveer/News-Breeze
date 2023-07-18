# News-Breeze
News Breeze is a News app that contains information about different News, Users can view different news and also read detailed article related to the news. This app also contains the feature of saving News in Local Storage.

### Download and Test the app [here](https://drive.google.com/file/d/1S0Qp1FxIScwLQNwnebObAgs9OM43SQcR/view?usp=sharing)

## Built using - 
- Kotlin Programming Language
- Glide Image Library
- Retrofit Library
- [News api](https://newsapi.org/)
- Android Studio
- Hilt for Dependency Injection

## Features of the app - 
- The App uses MVVM architecure & coroutines to asynchronously call the API. 
- This App is based on single activity multiple fragment concept.
- Clicking on the Read News button it takes the user to the 'Read Fragment' which contains information regarding it like - the title, description and Image.
- Clicking on the Saved News Icon it takes user to the 'Book Mark Fragment' which consists of news saved by the user.


## Working of the app - 
<img src="https://github.com/PranatPraveer/News-Breeze/assets/68765059/aa86f300-e1bc-422b-9c20-d087461ae401" height = "400" width="200">

## Concepts used - 
- **MVVM Architecture** : Followed clean architecture and MVVM design pattern. Followed the respository pattern where API calls happen through repostiory and it becomes the single source of truth for the app. The ViewModels can access the repostiory and then provide the Livedata to the fragments to observe.
- **Dependency Injection** : Used Hilt library for dependency injection, made a Network Module class which provides instance of NewsAPI to Hilt. 
- **Coroutines** : Used coroutines to asynchronously call the API in background. 
- **Glide Image Library** : Used the famous Glide Library to parse the url of the images that are getting fetched from the API and then display it.
- **Retrofit Library** : Used the Retrofit library which is type-safe HTTP client for Android for interacting with API.

## Decisions & Assumptions -
- Used the Retrofit library instead of the Volley library.
- Did not work properly on the UI as time was pretty less so tried to make it as minimalistic as possible. 
