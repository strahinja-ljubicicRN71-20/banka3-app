package domain.repository

interface IUserPreference {
    suspend fun storeToken(token: String)
    suspend fun getToken(): String
}