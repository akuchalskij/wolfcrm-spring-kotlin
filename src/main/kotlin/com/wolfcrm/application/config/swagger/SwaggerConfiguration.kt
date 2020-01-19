package com.wolfcrm.application.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.*
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate


@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    fun eDesignApi(swaggerConfigProperties: SwaggerProperties): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swaggerConfigProperties))
                .enable(swaggerConfigProperties.enabled.toBoolean())
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate::class.java, String::class.java)
                .genericModelSubstitutes(ResponseEntity::class.java)
                .useDefaultResponseMessages(swaggerConfigProperties.useDefaultResponseMessages?.toBoolean() ?: false)
                .enableUrlTemplating(swaggerConfigProperties.enableUrlTemplating?.toBoolean() ?: false)
    }

    @Bean
    fun uiConfig(swaggerConfigProperties: SwaggerProperties): UiConfiguration? {
        return UiConfigurationBuilder
                .builder()
                .deepLinking(swaggerConfigProperties.deepLinking?.toBoolean() ?: false)
                .displayOperationId(swaggerConfigProperties.displayOperationId?.toBoolean() ?: false)
                .defaultModelsExpandDepth(Integer.valueOf(swaggerConfigProperties.defaultModelsExpandDepth))
                .defaultModelExpandDepth(Integer.valueOf(swaggerConfigProperties.defaultModelExpandDepth))
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(swaggerConfigProperties.displayRequestDuration?.toBoolean() ?: false)
                .docExpansion(DocExpansion.NONE)
                .filter(swaggerConfigProperties.filter?.toBoolean() ?: false)
                .maxDisplayedTags(Integer.valueOf(swaggerConfigProperties.maxDisplayedTags))
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(swaggerConfigProperties.showExtensions?.toBoolean() ?: false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build()
    }

    private fun apiInfo(swaggerConfigProperties: SwaggerProperties): ApiInfo? {
        return ApiInfoBuilder()
                .title(swaggerConfigProperties.title)
                .description(swaggerConfigProperties.description)
                .version(swaggerConfigProperties.apiVersion)
                .build()
    }
}