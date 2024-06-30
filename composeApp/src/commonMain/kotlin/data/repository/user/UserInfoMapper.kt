package data.repository.user

import data.model.user.UserInfoDto
import domain.model.user.User
import utils.IMapper

class UserInfoMapper : IMapper<UserInfoDto, User> {
    override fun map(value: UserInfoDto): User {
        return User(
            firstName = value.firstName,
            lastName = value.lastName,
            jmbg = value.jmbg,
            dateOfBirth = value.dateOfBirth,
            phoneNumber = value.phoneNumber,
            address = value.address,
            email = value.email,
            id = value.id
        )
    }
}