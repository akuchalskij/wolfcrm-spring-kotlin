package com.wolfcrm.application.domain.projects

import com.wolfcrm.application.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "comments")
data class Comment (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,

        @Column(name = "description")
        var description: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        var author: User? = null,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "task_id")
        val task: Task? = null
)