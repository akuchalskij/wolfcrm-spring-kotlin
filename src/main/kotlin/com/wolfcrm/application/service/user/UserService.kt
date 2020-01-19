package com.wolfcrm.application.service.user

import com.wolfcrm.application.domain.user.User
import com.wolfcrm.application.dto.user.Account
import com.wolfcrm.application.dto.user.ManagedAccount
import com.wolfcrm.application.http.response.Message
import com.wolfcrm.application.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

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

    fun updateUser(authentication: Authentication, managedAccount: ManagedAccount) : Account {
        val user: User = userRepository.findByEmail(authentication.name).get()

        user.firstName = managedAccount.firstName
        user.lastName = managedAccount.lastName
        user.middleName = managedAccount.middleName
        user.phone = managedAccount.phone

        userRepository.save(user)

        return Account(
                user.email,
                user.firstName,
                user.lastName,
                user.middleName,
                user.phone
        )
    }

    fun changePassword(
            authentication: Authentication, password: String, newPassword: String, confirmPassword: String
    ) : ResponseEntity<*> {
        val user: User = userRepository.findByEmail(authentication.name).get()

        return if (passwordEncoder.matches(password, user.password)) {
            if (newPassword == confirmPassword) {
                user.password = passwordEncoder.encode(newPassword)
                userRepository.save(user)

                ResponseEntity(Message("Password has been changed"), HttpStatus.OK)
            } else {
                ResponseEntity(Message("Password are not match"), HttpStatus.BAD_REQUEST)
            }
        } else {
            ResponseEntity(Message("Password in correct"), HttpStatus.BAD_REQUEST)
        }
    }
}
