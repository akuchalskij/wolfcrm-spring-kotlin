package com.wolfcrm.application.domain.projects

import com.wolfcrm.application.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "projects")
data class Project (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,

        @Column(name = "title")
        var title: String? = null,

        @Column(name = "description")
        var description: String? = null,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "projects_users",
                joinColumns = [JoinColumn(name = "project_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
        )
        var users: Collection<User>? = null,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "projects_tasks",
                joinColumns = [JoinColumn(name = "project_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "task_id", referencedColumnName = "id")]
        )
        var tasks: Collection<Task>? = null
)
