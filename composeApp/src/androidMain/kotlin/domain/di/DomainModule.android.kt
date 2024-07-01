package domain.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.login.LoginViewModel
import ui.splash.SplashViewModel
import ui.home.HomeViewModel
import ui.payment.PaymentViewModel

actual val domainModule = module {

    viewModelOf(::LoginViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::PaymentViewModel)
}