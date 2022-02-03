[![wakatime](https://wakatime.com/badge/user/25e36a94-826e-4b90-bf37-6d78d9028422/project/95a7afd7-03a0-430d-968a-bb8bdc0646d2.svg)](https://wakatime.com/badge/user/25e36a94-826e-4b90-bf37-6d78d9028422/project/95a7afd7-03a0-430d-968a-bb8bdc0646d2.svg)
# 🎯Halan Callange Task🎯

## For Screen and Video as Demo for the Application 🎉
you will find the video in youtube [View Video 1](https://youtu.be/yC1y_bIpO4E) [View Video 2](https://youtu.be/hO8-jMFO-hg)  , for picture i will give you some picture 👀 too .

[<img align="center" alt="Splash" height="1024" width="512" src="https://i.imgur.com/fBx7tUn.jpg">](https://i.imgur.com/fBx7tUn.jpg)

[<img align="center" alt="Auth" height="1024" width="512" src="https://i.imgur.com/HV6onef.jpg">](https://i.imgur.com/HV6onef.jpg)

[<img align="center" alt="Products" height="1024" width="512" src="https://i.imgur.com/xSXNyB0.jpg">](https://i.imgur.com/xSXNyB0.jpg)

[<img align="center" alt="ProductDetail" height="1024" width="512" src="https://i.imgur.com/tJjUvLI.jpg">](https://i.imgur.com/tJjUvLI.jpg)

## If you want show some Testing Reports , I got your back 🖐🚀
I used Github Actions for this task particularly , so go to action tabs and run the flow GO TO ACTIONS  [View Actions](https://github.com/Devil2020/Halan/actions/workflows/test.yml)
## Let's explore Architecture Components

According to  [Android Documentation](https://developer.android.com/topic/libraries/architecture), Architecture Components are a set of Android libraries for structuring your app in a way that is robust, testable, and maintainable

[![](https://i.imgur.com/yhWLiwN.png)](https://i.imgur.com/yhWLiwN.png)

# []() Presentation Layer Architecture patter is MVi and Clean Architecute 

## []()UI Controllers

are activities or fragments. The only job of UI controllers is to know how to display data and pass on UI events, such as the user pressing a button. UI Controllers neither contain the UI data, nor directly manipulate data.

## []()Repository
This class is the single source of truth for all of our app's data and acts as a clean API for the UI to communicate with . Presenter simply request data usecase then it request it to repository. They do not need to worry about whether the repository should load from the database or network, or how or when to persist the data. The repository manages all of this. As part of this responsibility, the repository is a mediator between the different data sources.

## []()Remote Network Data Source

Manages data from a remote data source, such as the internet.


## []()Local Database Data Source

Manages data from a local data source, such as SharedPreference.

## []()Okay Now 🖐 , Happy Watching 🧨🎯

### []()License:
Copyright 2021 Mohammed Morse

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
