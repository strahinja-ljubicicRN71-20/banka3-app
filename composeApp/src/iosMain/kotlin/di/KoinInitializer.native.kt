package di

import data.di.dataModule
import domain.di.domainModule
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                domainModule,
                dataModule
            )
        }
    }
}