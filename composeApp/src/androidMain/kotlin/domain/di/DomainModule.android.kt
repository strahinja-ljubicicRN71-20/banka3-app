package domain.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.login.LoginViewModel
import ui.splash.SplashViewModel

actual val domainModule = module {

    viewModelOf(::LoginViewModel)
    viewModelOf(::SplashViewModel)
}