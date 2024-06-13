package domain.repository

interface IUserPreference {
    fun storeToken(token: String)
    fun getToken(): String
}