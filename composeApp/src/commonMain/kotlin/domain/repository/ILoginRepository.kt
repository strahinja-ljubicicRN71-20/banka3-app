package domain.repository

interface ILoginRepository {
    suspend fun isUserActive(email: String)
}