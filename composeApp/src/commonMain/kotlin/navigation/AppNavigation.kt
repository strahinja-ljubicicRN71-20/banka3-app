package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import domain.model.payment.PaymentScreenInfo
import kotlinx.serialization.json.Json
import ui.MainScreen
import ui.home.HomeScreen
import ui.login.LoginScreen
import ui.payment.NewPaymentScreen
import ui.payment.PaymentScreen
import ui.profile.ProfileScreen
import ui.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.Auth.path
    ) {
        navigation(
            startDestination = AppDestinations.SplashScreen.path,
            route = NavigationDestinations.Auth.path
        ) {
            composable(AppDestinations.SplashScreen.path) {
                SplashScreen(
                    onSplashScreenEnd = { screenPath ->
                        navigateAndForget(
                            navController = navController,
                            destination = screenPath,
                            route = NavigationDestinations.Auth.path
                        )
                    }
                )
            }
            composable(AppDestinations.Login.path) {
                LoginScreen(
                    onSuccessfulLogin = {
                        navigateAndForget(
                            navController = navController,
                            destination = AppDestinations.MainScreen,
                            route = NavigationDestinations.Auth.path
                        )
                    }
                )
            }
        }
        navigation(
            startDestination = AppDestinations.MainScreen.path,
            route = NavigationDestinations.App.path
        ) {
            composable(AppDestinations.MainScreen.path) {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MainDestinations.HomeScreen.path
    ) {
        composable(MainDestinations.HomeScreen.path) {
            HomeScreen(
                onNewPaymentClick = {
                    navController.navigate("${MainDestinations.NewPayment.path}/$it")
                }
            )
        }
        composable(MainDestinations.PaymentScreen.path) {
            PaymentScreen()
        }
        composable(MainDestinations.ProfileScreen.path) {
            ProfileScreen()
        }
        composable("${MainDestinations.NewPayment.path}/{paymentScreenInfo}") { backStackEntry ->
            val paymentScreenInfo = backStackEntry.arguments?.getString("paymentScreenInfo")
            NewPaymentScreen(navController = navController, paymentScreenInfo = Json.decodeFromString(paymentScreenInfo!!))
        }
    }
}

fun navigateAndForget(navController: NavController, destination: IDestination, route: String) {
    navController.navigate(destination.path) {
        popUpTo(route) {
            inclusive = true
        }
    }
}

