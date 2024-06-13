package domain.di

import ui.login.LoginViewModel
import domain.usecase.IsUserActiveUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val domainModule = module {

    // use-case
    singleOf(::IsUserActiveUseCase)

    // viewmodel
    singleOf(::LoginViewModel)
}