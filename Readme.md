# Android development Test

This is a sample app that provides a search functionality through the balldontlie API to get players of nba, also allows the users to see the detail of a selected player. 
For the development of this project were used:
 
 * [Kotlin](http://kotlinlang.org/) as main language
 * [RX Java](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-3.0) for execute asynchronous tasks
 * [Android ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) for the MVVM pattern
 * [Android Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) for the app navigation
 * [Retrofit](https://square.github.io/retrofit/) for networking
 * [Glide](https://github.com/bumptech/glide) for image loading
 * [Dagger](https://github.com/google/dagger) for dependency injection
 * [mockito](https://site.mockito.org/) for unit testing
 
 ## App architecture
 
 The app was developed using the MVVM pattern and trying to follow Clean architecture and SOLID principles. Also uses the single activity pattern so we only have the home activity that in this case is in charge of handling the search intents and hosting the navigation component.  
 
<details>
  <summary>Project Structure</summary>
 
   ```
    ¦   NBAApplication.kt // application file
    ¦   
	+---common  // package that contains the common files or classes of the application
	¦   +---di // common classes related to dependency injection
	¦	¦
	¦	+---ext // common files related extensions functions
	¦	¦
	¦	+---logger // common classes to handle the log utility for application
	¦	¦
	¦	+---observers // common classes to handle the lifecycle observes of the app
	¦
    +---data  // package that contains the data layer related files
    ¦   +---network // contains the network interaction files
    ¦   ¦   ¦   
    ¦   ¦   +---models // models for the network interaction
    ¦   ¦       
    ¦   +---providers // Provider of application.
    ¦           
    +---di // dependency injection related files
    ¦
    +---domain  // package that contains the domain layer related files
    ¦   +---interactors // Interactors of application
    ¦       
    +---ui // user interface related files
    ¦   ¦   
    ¦   +---adapters // recycler view adapters
	¦   ¦   ¦      
    ¦   ¦   +---viewholders // view holder for recycler views
    ¦   ¦       
    ¦   +---detail
    ¦   ¦       
    ¦   +---home
    ¦   ¦ 	
    ¦   +---models // modles of ui.
    ¦   ¦       
    ¦   +---navigator
    ¦   ¦       
    ¦   +---search
    ¦
   ```
 </details>
 
 
 
 