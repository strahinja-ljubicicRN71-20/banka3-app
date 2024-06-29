package domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ui.login.LoginViewModel
import ui.splash.SplashViewModel

actual val domainModule = module {

    // viewmodel
    singleOf(::LoginViewModel)
    singleOf(::SplashViewModel)
}