package com.wolfcrm.application.repository.projects

import com.wolfcrm.application.domain.projects.Project
import org.springframework.data.repository.CrudRepository

interface ProjectRepository : CrudRepository<Project, Long>