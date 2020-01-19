package com.wolfcrm.application.config.swagger

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration("swaggerConfigProperties")
class SwaggerProperties {
    @Value("\${api.version}")
    val apiVersion: String? = null

    @Value("\${swagger.enabled}")
    val enabled = "false"

    @Value("\${swagger.title}")
    val title: String? = null

    @Value("\${swagger.description}")
    val description: String? = null

    @Value("\${swagger.useDefaultResponseMessages}")
    val useDefaultResponseMessages: String? = null

    @Value("\${swagger.enableUrlTemplating}")
    val enableUrlTemplating: String? = null

    @Value("\${swagger.deepLinking}")
    val deepLinking: String? = null

    @Value("\${swagger.defaultModelsExpandDepth}")
    val defaultModelsExpandDepth: String? = null

    @Value("\${swagger.defaultModelExpandDepth}")
    val defaultModelExpandDepth: String? = null

    @Value("\${swagger.displayOperationId}")
    val displayOperationId: String? = null

    @Value("\${swagger.displayRequestDuration}")
    val displayRequestDuration: String? = null

    @Value("\${swagger.filter}")
    val filter: String? = null

    @Value("\${swagger.maxDisplayedTags}")
    val maxDisplayedTags: String? = null

    @Value("\${swagger.showExtensions}")
    val showExtensions: String? = null
}