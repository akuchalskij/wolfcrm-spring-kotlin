package com.wolfcrm.application.controller

import com.wolfcrm.application.dto.UserAuth
import com.wolfcrm.application.service.user.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/users")
class UserController {
    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody userAuth: UserAuth): ResponseEntity<*> = authService.authenticate(userAuth)

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody userAuth: UserAuth): ResponseEntity<*> = authService.register(userAuth)

}