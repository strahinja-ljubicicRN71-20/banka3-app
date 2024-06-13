package navigation

sealed class AppDestinations(val path: String) {
    object SplashScreen : AppDestinations("splash_screen")
    object Login : AppDestinations("login")
}