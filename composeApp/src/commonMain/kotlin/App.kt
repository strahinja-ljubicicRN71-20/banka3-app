import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.login.LoginScreen
import navigation.AppDestinations
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import ui.splash.SplashScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = AppDestinations.SplashScreen.path
            ) {
                composable(AppDestinations.SplashScreen.path) {
                    SplashScreen(navController = navController)
                }
                composable(AppDestinations.Login.path) {
                    LoginScreen(navController = navController)
                }
            }
        }
    }
}