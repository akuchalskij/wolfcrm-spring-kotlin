package com.wolfcrm.application.repository.projects

import com.wolfcrm.application.domain.projects.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, Long>