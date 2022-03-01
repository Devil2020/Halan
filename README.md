<h1 align="center">Hallan Challange Task</h1>

<p align="center">
 <img alt="Medium" src="https://github.com/mohamedebrahim96/Namshi-Customer/workflows/Android%20CI/badge.svg"/></a>
     <a href="https://github.com/devil2020?tab=followers"><img alt="API" src="https://img.shields.io/github/stars/devil2020?style=social"/></a>
     <a href="https://github.com/Devil2020/Halan/actions/workflows/test.yml"><img alt="API" src="https://img.shields.io/github/v/release/devil2020/halan?color=7885FF&label=Halan%20App&logo=android%22"/></a>

</p>



<p align="center">  
   upon running the application you will face a login screen where you are supposed to write a user name and a password (following the criteria of user name must be 6    to 15 char in length and only alphanumeric ) . 
   after logging in successfully you will find data of the profile as well as a list of products, which you can click any of the products to navigate to the product      details screen.
   
   the challenge is to fix the issue which will face you upon running the application , fix and improve the networking part and then proceed into refactoring the        application into a better version which will allow us to proceed with adding new features, change UI design, remove features, test the app without any problems .
   
   you are expected to use the challenge to demonstrate your technical skills using all of your preferred libraries eg. (jetpack components libraries), design            patterns. also, it is expected from you to showcase your understanding/implementation of Clean architecture, SOLID principles, reactive programming, write clean      code be as close as you can to the design attached and write test cases for your work .
</p>
</br>
<p align="center">
<img src=".images/Screening OnBoarding Picture.png"/>
</p>

## Download
Go to the [Releases](https://github.com/Devil2020/Halan/actions/workflows/test.yml) to download the latest APK.


<img src="/namshigif.gif" align="right" width="32%"/>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
    - Lifecycle - dispose of observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
    - Room Persistence - construct a database using the abstract layer.
- Architecture
    - MVVM Architecture (View - DataBinding - ViewModel - Model)
    - [Bindables](https://github.com/skydoves/bindables) - Android DataBinding kit for notifying data changes to UI layers.
    - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich) - construct lightweight http API response and handling error responses.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Glide](https://github.com/bumptech/glide)
- [WhatIf](https://github.com/skydoves/whatif) - checking nullable object and empty collections more fluently.
- [Timber](https://github.com/JakeWharton/timber) - logging.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
