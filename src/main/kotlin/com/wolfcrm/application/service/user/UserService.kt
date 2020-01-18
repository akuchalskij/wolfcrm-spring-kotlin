package com.wolfcrm.application.service.user

import com.wolfcrm.application.domain.user.User
import com.wolfcrm.application.dto.user.Account
import com.wolfcrm.application.repository.user.RoleRepository
import com.wolfcrm.application.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    fun getCurrentUser(authentication: Authentication) : Account {
        val user: User = userRepository.findByEmail(authentication.name).get()

        return Account(
                user.email,
                user.firstName,
                user.lastName,
                user.middleName,
                user.phone
        )
    }
}