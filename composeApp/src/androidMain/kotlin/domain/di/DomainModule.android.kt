package domain.di

import domain.usecase.IsUserActiveUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.login.LoginViewModel

actual val domainModule = module {

    single { IsUserActiveUseCase(get()) }

    viewModelOf(::LoginViewModel)
}