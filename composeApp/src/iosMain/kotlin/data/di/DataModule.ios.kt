package data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.preference.createDataStore
import domain.usecase.login.LoginUseCase
import org.koin.dsl.module

actual val dataModule = module {
    single<DataStore<Preferences>> {
        createDataStore()
    }

}