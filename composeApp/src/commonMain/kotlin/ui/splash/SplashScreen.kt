package ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import banka3_app.composeapp.generated.resources.Res
import banka3_app.composeapp.generated.resources.background
import banka3_app.composeapp.generated.resources.logofull
import kotlinx.coroutines.delay
import navigation.AppDestinations
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun SplashScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = null) {
        delay(800)
        navController.navigate(AppDestinations.Login.path) {
            popUpTo(0.toString()) {
                inclusive = true
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