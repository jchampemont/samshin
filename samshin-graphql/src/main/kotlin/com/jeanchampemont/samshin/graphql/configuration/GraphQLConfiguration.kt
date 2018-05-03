package com.jeanchampemont.samshin.graphql.configuration

import com.jeanchampemont.samshin.graphql.error.SamshinGraphQLExceptionHandler
import graphql.execution.AsyncExecutionStrategy
import graphql.servlet.DefaultExecutionStrategyProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLConfiguration {
    @field:Autowired
    private lateinit var exceptionHandler: SamshinGraphQLExceptionHandler;

    @Bean
    fun executionStrategyProvider() = DefaultExecutionStrategyProvider(AsyncExecutionStrategy(exceptionHandler))
}