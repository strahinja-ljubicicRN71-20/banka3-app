package domain.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.login.LoginViewModel

actual val domainModule = module {

    viewModelOf(::LoginViewModel)
}