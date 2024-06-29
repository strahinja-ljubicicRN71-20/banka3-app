import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import navigation.AppNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            AppNavigation()
        }
    }
}