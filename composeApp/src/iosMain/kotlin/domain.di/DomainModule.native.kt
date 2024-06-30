package domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ui.login.LoginViewModel
import ui.splash.SplashViewModel
import ui.home.HomeViewModel

actual val domainModule = module {

    // viewmodel
    singleOf(::LoginViewModel)
    singleOf(::SplashViewModel)
    singleOf(::HomeViewModel)
}