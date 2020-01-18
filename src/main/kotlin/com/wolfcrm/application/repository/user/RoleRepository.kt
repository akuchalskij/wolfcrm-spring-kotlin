package com.wolfcrm.application.repository.user

import com.wolfcrm.application.domain.user.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(@Param("name") name: String): Role
}