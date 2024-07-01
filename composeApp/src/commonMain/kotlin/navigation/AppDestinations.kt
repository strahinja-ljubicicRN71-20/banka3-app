package navigation

interface IDestination {
    val path: String
}

sealed class AppDestinations(override val path: String) : IDestination {
    data object SplashScreen : AppDestinations("splash_screen")
    data object Login : AppDestinations("login")
    data object MainScreen : AppDestinations("main")
}

sealed class MainDestinations(override val path: String) : IDestination {
    data object HomeScreen : MainDestinations("home")
    data object PaymentScreen : MainDestinations("payment")
    data object ProfileScreen : MainDestinations("profile")
    data object NewPayment : MainDestinations("new_payment")
}

sealed class NavigationDestinations(override val path: String) : IDestination {
    data object Auth : NavigationDestinations("auth")
    data object App : NavigationDestinations("app")
}

