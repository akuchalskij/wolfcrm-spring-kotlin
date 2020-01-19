package com.wolfcrm.application.controller.projects

import com.wolfcrm.application.domain.projects.Project
import com.wolfcrm.application.domain.projects.Task
import com.wolfcrm.application.domain.user.User
import com.wolfcrm.application.http.response.Message
import com.wolfcrm.application.repository.projects.ProjectRepository
import com.wolfcrm.application.repository.projects.TaskRepository
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
class TaskController {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var projectRepository: ProjectRepository

    @Autowired
    lateinit var taskRepository: TaskRepository

    @PostMapping("/{id}/tasks")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun create(
            authentication: Authentication,
            @PathVariable id: Long,
            @RequestParam title: String,
            @RequestParam description: String,
            @RequestParam executor: Long
    ) : ResponseEntity<*> {
        val reporter: User = userRepository.findByEmail(authentication.name).get()
        val executor: User = userRepository.findById(executor).get()
        val project: Project = projectRepository.findById(id).get()

        val task = Task(0, title, description, reporter, executor)
        taskRepository.save(task)

        project.tasks = listOf(task)
        projectRepository.save(project)

        return ResponseEntity(
                Message("Task ${task.title} in ${project.title} saved, reporter ${reporter.firstName} ${reporter.lastName}, assigned by ${executor.firstName} ${executor.lastName}"),
                HttpStatus.OK
        )
    }
}