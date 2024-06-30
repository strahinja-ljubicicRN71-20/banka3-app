package domain.repository

import domain.model.user.User

interface IUserPreference {
    suspend fun storeToken(token: String)
    suspend fun getToken(): String
    suspend fun storeUserData(user: User)
    suspend fun getUserData(): User
}