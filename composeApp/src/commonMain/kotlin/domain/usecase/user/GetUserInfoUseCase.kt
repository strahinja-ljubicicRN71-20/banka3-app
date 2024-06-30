package domain.usecase.user

import domain.model.user.User
import domain.repository.IUserPreference

class GetUserInfoUseCase(
    private val preference: IUserPreference
) {
    suspend operator fun invoke(): User {
        return preference.getUserData()
    }
}