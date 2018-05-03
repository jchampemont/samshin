/*
 * Copyright (C) 2018 Samshin
 *
 * This file is part of the Samshin project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jeanchampemont.samshin.graphql.error

import graphql.ErrorType
import graphql.ExceptionWhileDataFetching
import graphql.GraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.ExecutionPath
import graphql.language.SourceLocation
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component

@Component
class SamshinGraphQLExceptionHandler : DataFetcherExceptionHandler {
    override fun accept(handlerParameters: DataFetcherExceptionHandlerParameters) {
        val exception = handlerParameters.exception
        val sourceLocation = handlerParameters.field.sourceLocation
        val path = handlerParameters.path

        val error = filterException(exception, sourceLocation, path)
        handlerParameters.executionContext.addError(error, handlerParameters.path)
    }

    private fun filterException(exception: Throwable, sourceLocation: SourceLocation, path: ExecutionPath) = when (exception) {
        is AccessDeniedException -> AccessDeniedError(sourceLocation)
        else -> ExceptionWhileDataFetching(path, exception, sourceLocation)
    }
}

class AccessDeniedError(private val location: SourceLocation) : GraphQLError {
    override fun getMessage() = "Access was denied ; please check whether or not you are logged in and you have sufficient rights."

    override fun getLocations() = mutableListOf(location)

    override fun getErrorType() = ErrorType.ExecutionAborted
}