package domain.di

import domain.usecase.login.IsUserActiveUseCase
import ui.login.LoginViewModel
import org.koin.dsl.module

actual val domainModule = module {
    factory { IsUserActiveUseCase() }
    factory { LoginViewModel(get()) }
}