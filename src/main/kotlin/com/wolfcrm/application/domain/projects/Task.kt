package com.wolfcrm.application.domain.projects

import com.wolfcrm.application.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "tasks")
data class Task (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,

        @Column(name = "title")
        var title: String? = null,

        @Column(name = "description")
        var description: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "reporter_id", nullable = false)
        var reporter: User? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "executor_id", nullable = false)
        var executor: User? = null,

        @OneToMany(cascade = [(CascadeType.ALL)],
                fetch = FetchType.LAZY,
                mappedBy = "task")
        private val comments: MutableList<Comment> = mutableListOf()

 )