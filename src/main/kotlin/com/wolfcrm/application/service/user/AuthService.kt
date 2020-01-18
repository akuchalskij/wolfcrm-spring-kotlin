package com.wolfcrm.application.service.user

import com.wolfcrm.application.domain.User
import com.wolfcrm.application.dto.UserAuth
import com.wolfcrm.application.http.response.JWT
import com.wolfcrm.application.http.response.Message
import com.wolfcrm.application.repository.RoleRepository
import com.wolfcrm.application.repository.UserRepository
import com.wolfcrm.application.security.JWTProvider

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import java.util.*
import java.util.stream.Collectors

@Service
class AuthService {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var encoder: PasswordEncoder

    @Autowired
    lateinit var tokenProvider: JWTProvider

    fun authenticate(loginData: UserAuth): ResponseEntity<*> {
        val userCandidate: Optional<User> = userRepository.findByEmail(loginData.email!!)

        return if (userCandidate.isPresent) {
            val user: User = userCandidate.get()
            val authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(loginData.email, loginData.password)
            )
            SecurityContextHolder.getContext().authentication = authentication

            val jwt: String = tokenProvider.createToken(user.email!!)
            val authorities: List<GrantedAuthority> = user.roles!!.stream().map {
                role -> SimpleGrantedAuthority(role.name)
            }.collect(Collectors.toList<GrantedAuthority>())

            ResponseEntity(JWT(jwt, user.email, authorities), HttpStatus.OK)
        } else {
            ResponseEntity(Message("User not found!"), HttpStatus.BAD_REQUEST)
        }
    }

    fun register(registerData: UserAuth): ResponseEntity<*> {
        val userCandidate: Optional <User> = userRepository.findByEmail(registerData.email!!)

        return if (!userCandidate.isPresent) {
            val user = User(
                    0,
                    registerData.email!!,
                    null,
                    null,
                    null,
                    null,
                    encoder.encode(registerData.password),
                    true
                    )
            user.roles = listOf(roleRepository.findByName("ROLE_USER"))

            userRepository.save(user)

            ResponseEntity(Message("User registered successfully!"), HttpStatus.OK)
        } else {
            ResponseEntity(Message("User already exists!"), HttpStatus.BAD_REQUEST)
        }
    }

}