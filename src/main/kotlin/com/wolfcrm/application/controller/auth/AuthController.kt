package com.wolfcrm.application.controller.auth

import com.wolfcrm.application.dto.auth.UserAuth
import com.wolfcrm.application.service.auth.AuthService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(tags = ["Authentication"])
@RestController
@CrossOrigin
@RequestMapping("/v1/auth")
class AuthController {
    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody userAuth: UserAuth): ResponseEntity<*> = authService.authenticate(userAuth)

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody userAuth: UserAuth): ResponseEntity<*> = authService.register(userAuth)
}
