import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import login.LoginScreen
import navigation.AppDestinations
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = AppDestinations.Login.path
            ) {
                composable(AppDestinations.SplashScreen.path) {
                    //TODO: Implement SplashScreen
                }
                composable(AppDestinations.Login.path) {
                    LoginScreen()
                }
            }
        }
    }
}