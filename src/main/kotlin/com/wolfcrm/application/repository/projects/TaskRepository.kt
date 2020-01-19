package com.wolfcrm.application.repository.projects

import com.wolfcrm.application.domain.projects.Task
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Long>