package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.currentKoinScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T> ()
    }
}

@Composable
expect fun <T> StateFlow<T>.collectAsStateMultiplatform(
    context: CoroutineContext = EmptyCoroutineContext,
): State<T>