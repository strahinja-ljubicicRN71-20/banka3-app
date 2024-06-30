package ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import banka3_app.composeapp.generated.resources.Res
import banka3_app.composeapp.generated.resources.logofull
import kotlinx.coroutines.delay
import navigation.AppDestinations
import navigation.IDestination
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.koinViewModel

@Composable
@Preview
fun SplashScreen(
    splashViewModel: SplashViewModel = koinViewModel(),
    onSplashScreenEnd: (IDestination) -> Unit
) {
    LaunchedEffect(key1 = null) {
        splashViewModel.checkIfUserLoggedIn()
        delay(800)
        splashViewModel.nextScreen.collect {
            when(it) {
                NextScreen.Login -> onSplashScreenEnd.invoke(AppDestinations.Login)
                NextScreen.Main -> onSplashScreenEnd.invoke(AppDestinations.MainScreen)
            }
        }
    }
    SplashScreenContent(
        modifier = Modifier
    )
}

@Composable
private fun SplashScreenContent(
    modifier: Modifier
) {
    Surface {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Res.drawable.logofull),
                modifier = Modifier.size(350.dp).padding(start = 30.dp),
                contentDescription = ""
            )
        }
    }
}