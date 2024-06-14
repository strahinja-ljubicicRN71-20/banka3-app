package data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import domain.repository.IUserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreference(
    private val preferences: DataStore<Preferences>
) : IUserPreference {

    private val tokenKey = stringPreferencesKey("TOKEN")

    override suspend fun storeToken(token: String) {
        preferences.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    override suspend fun getToken(): String {
        return preferences.data.map { preferences ->
            preferences[tokenKey] ?: ""
        }.first()
    }
}