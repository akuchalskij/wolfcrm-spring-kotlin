package com.wolfcrm.application.controller.user

import com.wolfcrm.application.dto.user.Account
import com.wolfcrm.application.service.user.UserService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/v1/users")
class UserController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/current")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun currentUser(authentication: Authentication): Account = userService.getCurrentUser(authentication)

}
