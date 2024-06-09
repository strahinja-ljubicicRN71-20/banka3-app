package di

import login.LoginViewModel
import org.koin.dsl.module

actual val viewModelModule = module {
    factory { LoginViewModel() }
}