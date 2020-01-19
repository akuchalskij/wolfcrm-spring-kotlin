package com.wolfcrm.application.controller.projects

import com.wolfcrm.application.domain.projects.Project
import com.wolfcrm.application.domain.user.User
import com.wolfcrm.application.http.response.Message
import com.wolfcrm.application.repository.projects.ProjectRepository
import com.wolfcrm.application.repository.user.UserRepository
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@Api(tags = ["Projects"])
@RestController
@CrossOrigin
@RequestMapping("/v1/projects")
class ProjectsController {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var projectRepository: ProjectRepository

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun create(
            authentication: Authentication,
            @RequestParam title: String,
            @RequestParam description: String
    ) : ResponseEntity<*> {
        val user: User = userRepository.findByEmail(authentication.name).get()

        val project = Project(0, title, description)

        project.users = listOf(user)

        projectRepository.save(project)

        return ResponseEntity(
                Message("Project ${project.title} created, owner ${user.firstName} ${user.middleName}"),
                HttpStatus.OK
        )
    }
}