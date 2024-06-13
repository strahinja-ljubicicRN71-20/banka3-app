package di.shared

import data.di.factory.HttpClientFactory
import data.repository.login.remote.LoginRepository
import domain.repository.ILoginRepository
import domain.usecase.login.IsUserActiveUseCase
import domain.usecase.login.LoginUseCase
import io.ktor.client.HttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

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
}

val sharedModule = module {

    includes(httpClientModule)

    //use-case
    single { IsUserActiveUseCase(get()) }
    single { LoginUseCase(get()) }

}
