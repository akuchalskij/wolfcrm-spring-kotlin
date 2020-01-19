package com.wolfcrm.application.controller.user

import com.wolfcrm.application.dto.user.Account
import com.wolfcrm.application.dto.user.ManagedAccount
import com.wolfcrm.application.service.user.UserService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun currentUser(authentication: Authentication): Account = userService.getCurrentUser(authentication)

    @PutMapping("/current")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun updateProfile(authentication: Authentication, @Valid @RequestBody managedAccount: ManagedAccount): Account =
            userService.updateUser(authentication, managedAccount)

    @PostMapping("/current/change-password")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun changePassword(
            authentication: Authentication,
            @RequestParam password: String,
            @RequestParam newPassword: String,
            @RequestParam confirmPassword: String
    ) : ResponseEntity<*> = userService.changePassword(authentication, password, newPassword, confirmPassword)
}
