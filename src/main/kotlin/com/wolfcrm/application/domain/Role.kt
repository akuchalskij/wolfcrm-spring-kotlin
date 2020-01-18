package com.wolfcrm.application.domain

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = 0,

        @Column(name = "name")
        val name: String? = null
)