package di.shared

import data.di.factory.HttpClientFactory
import data.preference.UserPreference
import data.repository.login.remote.LoginRepository
import data.repository.user.UserInfoMapper
import data.repository.user.remote.UserRepository
import domain.repository.ILoginRepository
import domain.repository.IUserPreference
import domain.repository.IUserRepository
import domain.usecase.login.IsUserActiveUseCase
import domain.usecase.login.LoginUseCase
import domain.usecase.splash.CheckIfUserAlreadyLoggedInUseCase
import domain.usecase.user.GetUserInfoUseCase
import domain.usecase.user.StoreUserInfoUseCase
import io.ktor.client.HttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val mapperModule = module {
    single { UserInfoMapper() }
}

private val httpClientModule = module {
    single<HttpClient>(named("user-service")) {
        HttpClientFactory.getHttpClient(
            "banka-3.si.raf.edu.rs",
            "user-service"
        )
    }
    single<HttpClient>(named("bank-service")) {
        HttpClientFactory.getHttpClient(
            "https://banka-3.si.raf.edu.rs",
            "bank-service"
        )
    }
    single<HttpClient>(named("exchange-service")) {
        HttpClientFactory.getHttpClient(
            "https://banka-3.si.raf.edu.rs",
            "exchange-service"
        )
    }
    single<HttpClient>(named("email-service")) {
        HttpClientFactory.getHttpClient(
            "https://banka-3.si.raf.edu.rs",
            "email-service"
        )
    }

    single<ILoginRepository> { LoginRepository(get(named("user-service"))) }
    single<IUserRepository> { UserRepository(get(named("user-service")), get()) }
}

val sharedModule = module {

    includes(mapperModule)

    includes(httpClientModule)

    //preferences
    single<IUserPreference> { UserPreference(get()) }

    //use-case
    single { IsUserActiveUseCase(get()) }
    single { LoginUseCase(get(), get()) }
    single { CheckIfUserAlreadyLoggedInUseCase(get()) }
    single { StoreUserInfoUseCase(get(), get()) }
    single { GetUserInfoUseCase(get()) }
}
